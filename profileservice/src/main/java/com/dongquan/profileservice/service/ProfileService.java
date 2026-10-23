package com.dongquan.profileservice.service;

import com.dongquan.commonservice.common.CommonException;
import com.dongquan.commonservice.utils.Constant;
import com.dongquan.profileservice.event.EventProducer;
import com.dongquan.profileservice.model.ProfileDTO;
import com.dongquan.profileservice.repository.ProfileRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Slf4j
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    EventProducer eventProducer;


    Gson gson = new Gson();

    public Flux<ProfileDTO> getAllProfile() {
        return profileRepository.findAll()
                .map(ProfileDTO::entityToDto);
    }

    public  Mono<Boolean> checkDuplicate(String email){
        return profileRepository.findByEmail(email)
                .flatMap(profile -> Mono.just(true))
                .switchIfEmpty(Mono.just(false));
    }

    public Mono<ProfileDTO> createNewProfile(ProfileDTO profileDTO){
        return  checkDuplicate(profileDTO.getEmail())
                .flatMap(aBoolean ->{
                    if(Boolean.TRUE.equals(aBoolean)){
                            return Mono.error(new CommonException("PF02","Duplicate profile!", HttpStatus.BAD_REQUEST));
                    }else{
                        profileDTO.setStatus(Constant.STATUS_PROFILE_PENDING);
                        return createProfile(profileDTO);
                    }
                });
    }
    public Mono<ProfileDTO> createProfile(ProfileDTO profileDTO){
        return Mono.just(profileDTO)
                .map(ProfileDTO::dtoToEntity)
                .flatMap(profileRepository::save)
                .map(ProfileDTO::entityToDto)
                .doOnError(throwable -> log.error(throwable.getMessage()))
                .doOnSuccess(profileDTO1 ->{
                    if(Objects.equals(profileDTO1.getStatus(),Constant.STATUS_PROFILE_PENDING)){
                        profileDTO1.setInitialBalance(profileDTO.getInitialBalance());
                        eventProducer.send(Constant.PROFILE_ONBOAEDING_TOPIC,gson.toJson(profileDTO1)).subscribe();
                    }
                });
    }
}
