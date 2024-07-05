package com.rishav.service;

import com.rishav.model.Invitation;
import com.rishav.repository.InvitationRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void sendInvitation(String email, Long projectId) throws MessagingException {

        String invitationToken = UUID.randomUUID().toString();

        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);

        invitationRepository.save(invitation);

        String invitationLink = "http://localhost:8000/accept_invitations?token=" + invitationToken;
        emailService.sendEmailWithToken(email, invitationToken);
    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {
        Invitation invitation = invitationRepository.findByToken(token);
        if (invitation == null) {
            throw new Exception("Invalid Invitation token");
        }
        return null;
    }

    @Override
    public String getTokenByUserMail(String userEmail) {
        Invitation invitation = invitationRepository.findByEmail(userEmail);
        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) {
        Invitation invitation = invitationRepository.findByToken(token);
        invitationRepository.delete(invitation);
    }
}
