package com.dongquan.profileservice.model;

import com.dongquan.profileservice.data.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDTO{
    private long id;

    private String email;

    private String status;

    private String name;

    private double initialBalance;

    private String role;

    public static Profile dtoToEntity(ProfileDTO profileDTO){
        Profile profile = new Profile();
        profile.setId(profileDTO.getId());
        profile.setEmail(profileDTO.getEmail());
        profile.setName(profileDTO.getName());
        profile.setRole(profileDTO.getRole());
        profile.setStatus(profileDTO.getStatus());
        return profile;
    }

    public static ProfileDTO entityToDto(Profile profile){
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setEmail(profile.getEmail());
        profileDTO.setName(profile.getName());
        profileDTO.setRole(profile.getRole());
        profileDTO.setStatus(profileDTO.getStatus());
        return profileDTO;
    }

}
