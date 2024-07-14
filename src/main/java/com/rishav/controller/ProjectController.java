package com.rishav.controller;

import com.rishav.config.JwtConstant;
import com.rishav.model.Chat;
import com.rishav.model.Invitation;
import com.rishav.model.Project;
import com.rishav.model.User;
import com.rishav.request.InvitationRequest;
import com.rishav.response.MessageResponse;
import com.rishav.service.InvitationService;
import com.rishav.service.ProjectService;
import com.rishav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private InvitationService invitationService;

    @GetMapping
    public ResponseEntity<List<Project>> getProject(
            @RequestParam(required = false)String category,
            @RequestParam(required = false)String tag,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findByProfileByJwt(jwt);
        List<Project> projectList = projectService.getProjectByTeam(user, category, tag);
        return new ResponseEntity<>(projectList , HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(
            @PathVariable Long projectId,
            @RequestHeader(JwtConstant.JWT_HEADER) String jwt
    ) throws Exception {
        User user = userService.findByProfileByJwt(jwt);
        Project project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(
            @RequestHeader(JwtConstant.JWT_HEADER) String jwt,
            @RequestBody Project project
    ) throws Exception {
        User user = userService.findByProfileByJwt(jwt);
        Project CreatedProject = projectService.createProject(project, user);
        return new ResponseEntity<>(CreatedProject , HttpStatus.CREATED);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(
            @RequestParam Long projectId,
//            @RequestHeader(JwtConstant.JWT_HEADER) String jwt,
            @RequestBody Project project
    ) throws Exception {
//        User user = userService.findByProfileByJwt(jwt);
        Project updateProject = projectService.updateProject(project, projectId);
        return new ResponseEntity<>(updateProject , HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse> deleteProject(
            @PathVariable Long projectId
//            @RequestHeader(JwtConstant.JWT_HEADER) String jwt,
//            @RequestBody Project project
    ) throws Exception {
//        User user = userService.findByProfileByJwt(jwt);
        projectService.deleteProject(projectId);
        MessageResponse response = new MessageResponse("Project deleted Successfully");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProject(
            @RequestParam(required = false) String keyword,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findByProfileByJwt(jwt);
        List<Project> projectList = projectService.searchProject(keyword, user );
        return new ResponseEntity<>(projectList , HttpStatus.OK);
    }

    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat> getChatByProjectId(
            @PathVariable Long projectId
    ) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat , HttpStatus.OK);
    }

    @PostMapping("/invite")
    public ResponseEntity<MessageResponse> inviteProject(
            @RequestBody InvitationRequest invitationRequest,
            @RequestHeader(JwtConstant.JWT_HEADER) String jwt,
            @RequestBody Project project
    ) throws Exception {
        User user = userService.findByProfileByJwt(jwt);
        invitationService.sendInvitation(invitationRequest.getEmail(), invitationRequest.getProjectId());
        MessageResponse response = new MessageResponse("User Invitation sent");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/accept_invitation")
    public ResponseEntity<Invitation> acceptInviteProject(
            @RequestParam String token,
            @RequestHeader(JwtConstant.JWT_HEADER) String jwt,
            @RequestBody Project project
    ) throws Exception {
        User user = userService.findByProfileByJwt(jwt);
        Invitation invitation = invitationService.acceptInvitation(token, user.getId());
        projectService.addUserToProject(invitation.getProjectId(), user.getId());
        return new ResponseEntity<>(invitation , HttpStatus.ACCEPTED);
    }


}
