CREATE TABLE job_post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    location VARCHAR(255),
    level VARCHAR(100),
    min_salary BIGINT,
    max_salary BIGINT,
    deadline_date DATE,
    job_type VARCHAR(100),
    post_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tech (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE tech_stack (
    job_post_id BIGINT,
    tech_id BIGINT,
    PRIMARY KEY (job_post_id, tech_id),
    FOREIGN KEY (job_post_id) REFERENCES job_post(id) ON DELETE CASCADE,
    FOREIGN KEY (tech_id) REFERENCES tech(id) ON DELETE CASCADE
);


-- Sample data for job_post table with detailed descriptions
INSERT INTO job_post (title, description, location, level, min_salary, max_salary, deadline_date, job_type) VALUES
('Software Engineer', 'Develop and maintain high-quality web applications using modern technologies. Collaborate with a team of talented engineers to design, implement, and test new features.  Write clean, efficient, and well-documented code.  Troubleshoot and debug issues to ensure a smooth user experience. Contribute to the continuous improvement of our development processes.', 'Ho Chi Minh City', 'Senior', 15000000, 30000000, '2024-03-15', 'Contract'),
('Data Scientist', 'Analyze large datasets to extract meaningful insights and trends. Develop and implement machine learning models to solve business problems.  Work with stakeholders to understand their data needs and communicate findings effectively.  Stay up-to-date with the latest advancements in data science and machine learning.  Contribute to a data-driven culture within the organization.', 'Hanoi', 'Entry Level', 12000000, 25000000, '2024-03-22', 'Full Time'),
('Frontend Developer', 'Create engaging and user-friendly interfaces for our web applications.  Translate designs and wireframes into clean, efficient, and responsive code.  Work closely with designers and backend developers to ensure a seamless user experience.  Optimize website performance and accessibility.  Stay current with the latest trends and technologies in frontend development.', 'Da Nang', 'Junior', 8000000, 18000000, '2024-03-29', 'Full Time'),
('Backend Developer', 'Develop and maintain the server-side logic for our web applications.  Design and implement RESTful APIs to connect with frontend applications and other services.  Ensure the security, scalability, and performance of our backend systems.  Work with databases to store and retrieve data efficiently.  Collaborate with other developers to integrate different parts of the system.', 'Ho Chi Minh City', 'Middle', 10000000, 22000000, '2024-04-05', 'Full Time'),
('Mobile Developer', 'Build high-quality mobile applications for iOS and/or Android platforms.  Collaborate with designers and product managers to bring innovative app ideas to life.  Write clean, efficient, and maintainable code.  Ensure app performance, quality, and security.  Stay up-to-date with the latest mobile development trends and technologies.', 'Hanoi', 'Senior', 14000000, 28000000, '2024-04-12', 'Contract'),
('DevOps Engineer', 'Manage and automate the deployment and scaling of our applications and infrastructure.  Work with cloud platforms (e.g., AWS, Azure, GCP) to provision and manage resources.  Implement and maintain CI/CD pipelines to streamline the software development lifecycle.  Monitor system performance and troubleshoot issues.  Collaborate with developers to ensure smooth and reliable deployments.', 'Da Nang', 'Senior', 11000000, 24000000, '2024-04-19', 'Full Time'),
('QA Engineer', 'Develop and execute test plans to ensure the quality of our software products.  Identify and report bugs and defects.  Work closely with developers to reproduce and resolve issues.  Contribute to the improvement of our testing processes and tools.  Ensure that our software meets quality standards and user expectations.', 'Ho Chi Minh City', 'Junior', 7000000, 16000000, '2024-04-26', 'Full Time'),
('Project Manager', 'Lead and manage software development projects from initiation to completion.  Define project scope, goals, and deliverables.  Create and manage project plans, budgets, and timelines.  Communicate effectively with stakeholders and team members.  Mitigate risks and resolve issues to ensure project success.', 'Hanoi', 'Senior', 16000000, 32000000, '2024-05-03', 'Full Time'),
('UI/UX Designer', 'Design user-centered interfaces and experiences for our web and mobile applications.  Conduct user research to understand user needs and behaviors.  Create wireframes, mockups, and prototypes to visualize design solutions.  Collaborate with developers to ensure designs are implemented effectively.  Stay up-to-date with the latest design trends and best practices.', 'Da Nang', 'Senior', 9000000, 20000000, '2024-05-10', 'Full Time'),
('Cybersecurity Analyst', 'Monitor and protect our computer systems and networks from security threats.  Analyze security logs and identify potential vulnerabilities.  Respond to security incidents and investigate breaches.  Implement security measures and controls to mitigate risks.  Stay informed about the latest cybersecurity threats and vulnerabilities.', 'Ho Chi Minh City', 'Junior', 8500000, 19000000, '2024-05-17', 'Full Time');


-- Sample data for tech table
INSERT INTO tech (name) VALUES
('Java'),
('Python'),
('JavaScript'),
('React'),
('Angular'),
('Node.js'),
('SQL'),
('NoSQL'),
('AWS'),
('Docker');

-- Sample data for tech_stack table (linking job_post and tech)
INSERT INTO tech_stack (job_post_id, tech_id) VALUES
                                                  (1, 1), -- Software Engineer uses Java
                                                  (1, 7), -- Software Engineer uses SQL
                                                  (2, 2), -- Data Scientist uses Python
                                                  (2, 8), -- Data Scientist uses NoSQL
                                                  (3, 3), -- Frontend Developer uses JavaScript
                                                  (3, 4), -- Frontend Developer uses React
                                                  (4, 3), -- Backend Developer uses JavaScript
                                                  (4, 6), -- Backend Developer uses Node.js
                                                  (5, 1), -- Mobile Developer uses Java (Android)
                                                  (5, 7), -- Mobile Developer uses SQL
                                                  (6, 9), -- DevOps Engineer uses AWS
                                                  (6, 10), -- DevOps Engineer uses Docker
                                                  (7, 7), -- QA Engineer uses SQL
                                                  (8, 7), -- Project Manager might use SQL for reporting
                                                  (9, 3), -- UI/UX Designer might use JavaScript for prototyping
                                                  (10, 2); -- Cybersecurity Analyst might use Python for scripting.

-- Added more relationships for a richer dataset
INSERT INTO tech_stack (job_post_id, tech_id) VALUES
                                                  (1, 3), -- Software Engineer also uses Javascript
                                                  (2, 3), -- Data Scientist also uses Javascript
                                                  (3, 5), -- Frontend Developer also uses Angular
                                                  (4, 2), -- Backend Developer also uses Python
                                                  (5, 3), -- Mobile Developer also uses Javascript
                                                  (6, 2), -- DevOps Engineer also uses Python
                                                  (7, 3), -- QA Engineer also uses Javascript
                                                  (8, 1), -- Project Manager also uses Java for some tools
                                                  (9, 4), -- UI/UX Designer also uses React
                                                  (10, 9); -- Cybersecurity Analyst also uses AWS