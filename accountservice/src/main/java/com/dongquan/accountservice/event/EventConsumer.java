package com.dongquan.accountservice.event;


import com.dongquan.accountservice.model.AccountDTO;
import com.dongquan.accountservice.service.AccountService;
import com.dongquan.commonservice.model.ProfileDTO;
import com.dongquan.commonservice.utils.Constant;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;
import java.util.Collections;

@Service
@Slf4j
public class EventConsumer {
    @Autowired
    AccountService accountService;
    Gson gson = new Gson();

    public EventConsumer(ReceiverOptions<String,String> receiverOptions){
        KafkaReceiver.create(receiverOptions.subscription(Collections.singleton(Constant.PROFILE_ONBOAEDING_TOPIC)))
                .receive().subscribe(this::profileOnboarding);
    }

    public void profileOnboarding(ReceiverRecord<String,String> receiverRecord){
        log.info("Profile Onboarding event");
        ProfileDTO profileDTO = gson.fromJson(receiverRecord.value(),ProfileDTO.class);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(profileDTO.getEmail());
        accountDTO.setReserved(0);
        accountDTO.setBalance(profileDTO.getInitialBalance());
        accountDTO.setCurrency("VND");
        accountService.createNewAccount(accountDTO).subscribe();
    }
}
