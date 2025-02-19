package com.telusko.jobapp.controller;

import com.telusko.jobapp.model.JobPost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JobController {

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        String message = "This is a simple job portal application where you can view all the jobs available and create a new job.";
        model.addAttribute("message", message);
        return "home";
    }

    @RequestMapping(value = "/create-new-job", method = RequestMethod.GET)
    public String addJob() {
        return "create-new-job";
    }

    @RequestMapping(value = "/handleForm", method = RequestMethod.POST
    )
    public String handleForm(JobPost jobPost) {
        return "handleForm";
    }
}
