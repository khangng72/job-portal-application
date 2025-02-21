package com.telusko.jobapp.repository;

import com.telusko.jobapp.model.JobPost;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobPostRepository {

    private final JdbcTemplate jdbcTemplate;

    public JobPostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insertTech(String techName) {
        String sql = "INSERT INTO tech (name) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM tech WHERE name = ?)";
        jdbcTemplate.update(sql, techName, techName);

        return jdbcTemplate.queryForObject("SELECT id FROM tech WHERE name = ?", Long.class, techName);
    }

    public Long insertJobPost(JobPost jobPost) {
        String query = "INSERT INTO JOB_POST (TITLE, DESCRIPTION, LOCATION, LEVEL, MIN_SALARY, MAX_SALARY, DEADLINE_DATE, JOB_TYPE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                query,
                jobPost.getTitle(),
                jobPost.getDescription(),
                jobPost.getLocation(),
                jobPost.getLevel(),
                jobPost.getMin_salary(),
                jobPost.getMax_salary(),
                jobPost.getDeadline_date(),
                jobPost.getJob_type());

        return jdbcTemplate.queryForObject("SELECT TOP 1 id FROM JOB_POST ORDER BY post_date DESC", Long.class);
    }

    public void insertTechStack(Long jobPostId, Long techId) {
        String query = "INSERT INTO TECH_STACK (JOB_POST_ID, TECH_ID) VALUES (?, ?)";
        jdbcTemplate.update(query, jobPostId, techId);
    }

    public Long insertJobPostWithTech(JobPost jobPost) {
        Long jobPostId = insertJobPost(jobPost);

        System.out.println("Inserted job post with id: " + jobPostId);

        for (String tech : jobPost.getTech_stack()) {
            Long techId = insertTech(tech);
            insertTechStack(jobPostId, techId);
        }

        return jobPostId;
    }

    public JobPost findJobPostWithId(Long id) {
        String query = "SELECT * FROM JOB_POST WHERE id = ?";

        // Use lambda expression for RowMapper
        RowMapper<JobPost> rowMapper = (rs, rowNum) -> {
            JobPost jobPost = new JobPost();
            jobPost.setId(rs.getLong("id"));
            jobPost.setTitle(rs.getString("title"));
            jobPost.setDescription(rs.getString("description"));
            jobPost.setLocation(rs.getString("location"));
            jobPost.setLevel(rs.getString("level"));
            jobPost.setMax_salary(rs.getLong("max_salary"));
            jobPost.setMin_salary(rs.getLong("min_salary"));
            jobPost.setDeadline_date(rs.getDate("deadline_date").toLocalDate());
            jobPost.setJob_type(rs.getString("job_type"));
            return jobPost;
        };

        // Execute the query and return the jobPost object
        return jdbcTemplate.queryForObject(query, rowMapper, id);
    }

    public List<String> findTechStackWithJobPostId(Long jobPostId) {
        String query = "SELECT * FROM TECH INNER JOIN TECH_STACK ON TECH.id = TECH_STACK.tech_id WHERE TECH_STACK.job_post_id = ?";
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("name");

        return jdbcTemplate.query(query, rowMapper, jobPostId);
    }


    public void updateJobPost(JobPost jobPost) {
        String query = "UPDATE JOB_POST SET title = ?, description = ?, location = ?, level = ?, max_salary = ?, min_salary = ?, deadline_date = ?, job_type = ? WHERE id = ?";
        String tech_delete_query = "DELETE FROM TECH_STACK WHERE job_post_id = ?";
        jdbcTemplate.update(tech_delete_query, jobPost.getId());
        for (String tech : jobPost.getTech_stack()) {
            Long techId = insertTech(tech);
            insertTechStack(jobPost.getId(), techId);
        }
        jdbcTemplate.update(
                query,
                jobPost.getTitle(),
                jobPost.getDescription(),
                jobPost.getLocation(),
                jobPost.getLevel(),
                jobPost.getMax_salary(),
                jobPost.getMin_salary(),
                jobPost.getDeadline_date(),
                jobPost.getJob_type(),
                jobPost.getId()
        );
    }
}
