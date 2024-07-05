package com.rishav.service;

import com.rishav.model.Issue;
import com.rishav.repository.IssueRepository;
import com.rishav.request.IssueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public Optional<Issue> getIssueById(Long issueId) throws Exception {
        return Optional.empty();
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        return null;
    }

    @Override
    public Issue createIssue(IssueRequest issue, Long userId) {
        return null;
    }

    @Override
    public String deleteIssue(Long issueId, Long userId) {
        return null;
    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) {
        return null;
    }

    @Override
    public Issue updateStatus(Long issueId, String status) {
        return null;
    }
}
