package com.rishav.service;

import com.rishav.model.Issue;
import com.rishav.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {

    Optional<Issue> getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, Long userId);

//    Optional<Issue> updateIssue(Long issueId, IssueRequest updateIssue, Long userId);

    String deleteIssue(Long issueId, Long userId);

//    List<Issue> getIssueByAssigneeId(Long assigneeId);

//    List<Issue> searchIssue(String title, String status, String priority, Long AssigneeId);

    Issue addUserToIssue(Long issueId, Long userId);

    Issue updateStatus(Long issueId, String status);




}
