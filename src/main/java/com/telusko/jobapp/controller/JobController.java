package com.telusko.jobapp.controller;


import com.telusko.jobapp.model.JobPost;
import com.telusko.jobapp.service.JobPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class JobController {

    private final JobPostService jobPostService;
    private final List<String> jobTypesList;
    private final List<String> levelsList;

    public JobController(JobPostService jobPostService) {
        this.jobPostService = jobPostService;
        jobTypesList = Arrays.asList("Full Time", "Part Time", "Contract");
        levelsList = Arrays.asList("Entry Level", "Junior", "Middle", "Senior");
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        String message = "This is a simple job portal application where you can view all the jobs available and create a new job.";
        model.addAttribute("message", message);
        return "home";
    }

    @RequestMapping(value = "/create-new-job", method = RequestMethod.GET)
    public String showJobForm(Model model) {

        model.addAttribute("jobTypesList", jobTypesList);
        model.addAttribute("levelsList", levelsList);
        model.addAttribute("jobPost", new JobPost());
        return "create-new-job";
    }

    @RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
    public String showJobFormSuccess(Model model, @PathVariable Long id) {
        System.out.println("Enter Job Edit Page");
        JobPost jobPost = jobPostService.findJobPostWithId(id);
        if (jobPost == null) {
            return "redirect:/not-found";
        }
        model.addAttribute("jobTypesList", jobTypesList);
        model.addAttribute("levelsList", levelsList);
        model.addAttribute("jobPost", jobPost);
        return "job-edit";
    }

    @RequestMapping(value = "/create-new-job", method = RequestMethod.POST
    )
    public String handleForm(@ModelAttribute("jobPost") JobPost jobPost, @RequestParam(name = "techStack", required = false) String techStack) {

        Long result_id = jobPostService.createJobPost(jobPost, techStack);

        return "redirect:/job/" + result_id.toString();
    }

    @RequestMapping(value = "/save-edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> handleSaveEditJob(@RequestBody JobPost jobPost) {
        jobPostService.updateJobPost(jobPost);
        String result = "/job/" + jobPost.getId().toString();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
