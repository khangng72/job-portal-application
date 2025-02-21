package com.telusko.jobapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class JobPost {
    private Long id;
    private String title;
    private String description;
    private List<String> tech_stack;
    private String location;
    private String level;
    private Long max_salary;
    private Long min_salary;
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate deadline_date;
    private String job_type;

    public static JobPost generateRandomJobPost() {
        List<String> titles = List.of("Software Engineer", "Backend Developer", "Frontend Developer", "Data Scientist");
        List<String> descriptions = List.of("Build scalable applications", "Develop APIs", "Analyze data and AI models");
        List<List<String>> techStacks = List.of(
                List.of("Java", "Spring Boot", "MySQL"),
                List.of("Python", "Django", "PostgreSQL"),
                List.of("JavaScript", "React", "Node.js")
        );
        List<String> locations = List.of("New York", "San Francisco", "Remote", "London");
        List<String> levels = List.of("Entry Level", "Junior", "Middle", "Senior");
        List<String> jobTypes = List.of("Full Time", "Part Time", "Contract");

        Long id = ThreadLocalRandom.current().nextLong(1, 1000);
        String title = getRandomElement(titles);
        String description = getRandomElement(descriptions);
        List<String> tech_stack = getRandomElement(techStacks);
        String location = getRandomElement(locations);
        String level = getRandomElement(levels);
        long min_salary = ThreadLocalRandom.current().nextLong(50000, 100000);
        Long max_salary = min_salary + ThreadLocalRandom.current().nextLong(10000, 50000);
        LocalDate deadline_date = LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(30, 180));
        String job_type = getRandomElement(jobTypes);

        return new JobPost(id, title, description, tech_stack, location, level, max_salary, min_salary, deadline_date, job_type);
    }

    private static <T> T getRandomElement(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }
}
