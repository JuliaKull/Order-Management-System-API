package com.bta.service.impl;

import com.bta.model.ActivationLink;
import com.bta.repository.ActivationLinkRepository;
import com.bta.service.ActivationLinkCleanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ActivationLinkCleanServiceImpl implements ActivationLinkCleanService {
    @Autowired
    private ActivationLinkRepository activationLinkRepository;


    @Scheduled(initialDelay = 5000l, fixedRate = 10000l)
    @Transactional
    @Override
    public void clean() {
        final ZonedDateTime checkDate = ZonedDateTime.now().minusDays(1l);
        final List<ActivationLink> expiredLinks = activationLinkRepository.findByCreatedBefore(checkDate);
        if (expiredLinks==null ||expiredLinks.isEmpty()) {
            log.info("No expired Activation Links in System");
            return;
        }
        log.info("Detected " + expiredLinks.size() + " Activation Links in System");
        activationLinkRepository.deleteAll(expiredLinks);
        log.info("Successfully cleaned up.");
    }
}
