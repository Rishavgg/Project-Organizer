package com.rishav.repository;

import com.rishav.model.Invitation;
import com.rishav.service.InvitationService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    Invitation findByToken(String token);

    Invitation findByEmail(String userEmail);
}
