package com.bta.repository;

import com.bta.model.ActivationLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ActivationLinkRepository extends JpaRepository<ActivationLink, Long> {
    ActivationLink findByCode(String code);

    List<ActivationLink> findByCreatedBefore(ZonedDateTime checkDate);
}
