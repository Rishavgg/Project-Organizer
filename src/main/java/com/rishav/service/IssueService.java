package com.rishav.service;

import com.rishav.model.Issue;
import com.rishav.model.User;
import com.rishav.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {

    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

//    Optional<Issue> updateIssue(Long issueId, IssueRequest updateIssue, Long userId);

    void deleteIssue(Long issueId, Long userId) throws Exception;

//    List<Issue> getIssueByAssigneeId(Long assigneeId);

//    List<Issue> searchIssue(String title, String status, String priority, Long AssigneeId);

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;




}
