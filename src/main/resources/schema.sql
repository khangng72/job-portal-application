CREATE TABLE job_post
(
    post_id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_profile   VARCHAR(255),
    post_desc      VARCHAR(255),
    req_experience INT
);

CREATE TABLE tech_stack
(
    tech_stack_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT,
    tech_stack_name VARCHAR(255),
    CONSTRAINT tech_stack_fk  FOREIGN KEY (post_id) REFERENCES job_post(post_id) ON DELETE CASCADE
);

