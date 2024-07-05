package com.rishav.repository;

import com.rishav.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    public List<Issue> findByProjectID(Long id);
}


//future error will be written projectId in many places instead of ProjectID