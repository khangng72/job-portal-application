package com.telusko.jobapp.service;

import com.telusko.jobapp.model.JobPost;
import com.telusko.jobapp.repository.JobPostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPostService {

    private final JobPostRepository jobPostRepository;

    public JobPostService(JobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
    }

    public List<String> parseTechStack(String techStack) {
        List<String> result = new ArrayList<>();
        if (techStack == null) return result;

        result = Arrays.stream(techStack.split(",")).map(String::trim).collect(Collectors.toList());

        return result;
    }

    public Long createJobPost(JobPost jobPost, String techStack) {
        jobPost.setTech_stack(parseTechStack(techStack));
        return jobPostRepository.insertJobPostWithTech(jobPost);
    }

    public JobPost findJobPostWithId(Long id) {
        JobPost jobPost = jobPostRepository.findJobPostWithId(id);
        List<String> techStack = jobPostRepository.findTechStackWithJobPostId(id);
        jobPost.setTech_stack(techStack);
        return jobPost;
    }

    public void updateJobPost(JobPost jobPost) {
        jobPostRepository.updateJobPost(jobPost);
    }
}
