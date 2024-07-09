package com.rishav.controller;

import com.rishav.config.JwtConstant;
import com.rishav.dto.IssueDTO;
import com.rishav.model.Issue;
import com.rishav.model.User;
import com.rishav.request.IssueRequest;
import com.rishav.response.MessageResponse;
import com.rishav.service.IssueService;
import com.rishav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issue,
                                                @RequestHeader(JwtConstant.JWT_HEADER) String token) throws Exception {
        User tokenUser = userService.findByProfileByJwt(token);

        Issue createdIssue = issueService.createIssue(issue, tokenUser);
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setDescription(createdIssue.getDescription());
        issueDTO.setId(createdIssue.getId());
        issueDTO.setTitle(createdIssue.getTitle());
        issueDTO.setDescription(createdIssue.getDescription());
        issueDTO.setStatus(createdIssue.getStatus());
        issueDTO.setProjectId(createdIssue.getProject().getId());
        issueDTO.setPriority(createdIssue.getPriority());
        issueDTO.setDueDate(createdIssue.getDueDate()); // Assuming dueDates is of type LocalDate or Date
        issueDTO.setTags(createdIssue.getTags());
        issueDTO.setProject(createdIssue.getProject());
        issueDTO.setAssignee(createdIssue.getAssignee());

        return new ResponseEntity<>(issueDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,
                                                    @RequestHeader(JwtConstant.JWT_HEADER) String token) throws Exception {
        User user = userService.findByProfileByJwt(token);
        issueService.deleteIssue(issueId, user.getId());

        MessageResponse response = new MessageResponse();
        response.setMessage("Issue Deleted");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId,
                                                @PathVariable Long userId) throws Exception {
        Issue issue = issueService.addUserToIssue(issueId, userId);
        return ResponseEntity.ok(issue);
    }

    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(
            @PathVariable String status,
            @PathVariable Long issueId
    ) throws Exception {
        Issue issue = issueService.updateStatus(issueId, status);
        return ResponseEntity.ok(issue);
    }
}
