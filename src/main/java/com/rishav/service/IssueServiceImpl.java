package com.rishav.service;

import com.rishav.model.Issue;
import com.rishav.model.Project;
import com.rishav.model.User;
import com.rishav.repository.IssueRepository;
import com.rishav.repository.ProjectRepository;
import com.rishav.request.IssueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectService projectService;

    @Override
    public Optional<Issue> getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue = issueRepository.findById(issueId);
        if (issue.isPresent()) {
            return issue;
        }
        throw new Exception("No issues found with issueId: " + issueId);
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        return issueRepository.findByProjectID(projectId);
    }

    @Override
    public Issue createIssue(IssueRequest issueRequest, User user) throws Exception {
        Project project = projectService.getProjectById(issueRequest.getProjectId());

        Issue issue = new Issue();
        issue.setTitle(issueRequest.getTitle());
        issue.setDescription(issueRequest.getDescription());
        issue.setStatus(issueRequest.getStatus());
        issue.setProjectId(issueRequest.getProjectId());
        issue.setPriority(issueRequest.getPriority());
        issue.setDueData(issueRequest.getDueData());

        issue.setProject(project);

        return issueRepository.save(issue);

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
