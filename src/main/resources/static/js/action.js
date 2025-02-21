const handleOnClickEditButton_CreateNewJobSuccess = () => {
    const titleInput = document.getElementById("jobTitle");
    const techStackInput = document.getElementById("techStack");
    const jobDescriptionInput = document.getElementById("jobDescription");
    const experienceLevelInput = document.getElementById("experienceLevel");
    const locationInput = document.getElementById("location");
    const salaryMaxInput = document.getElementById("salaryMax");
    const salaryMinInput = document.getElementById("salaryMin");
    const deadlineInput = document.getElementById("deadline");
    const jobTypeCheck = document.querySelector(".job-type-check");
    const jobType = document.querySelector(".job-type");
    const btnEdit = document.querySelector(".btn-edit");
    const btnSave = document.querySelector(".btn-save");

    titleInput.disabled = false;
    techStackInput.disabled = false;
    jobDescriptionInput.disabled = false;
    experienceLevelInput.disabled = false;
    locationInput.disabled = false;
    salaryMaxInput.disabled = false;
    salaryMinInput.disabled = false
    deadlineInput.disabled = false;
    jobTypeCheck.style.display = 'block';
    jobType.style.display = 'none';
    btnEdit.style.display = 'none';
    btnSave.style.display = 'inline-block';
}

const handleOnSaveEditJob = async () => {
    const id = window.location.href.split('/').pop();
    const title = document.getElementById("jobTitle").value;
    const techStack = document.getElementById("techStack").value;
    const description = document.getElementById("jobDescription").value;
    const level = document.getElementById("experienceLevel").value;
    const location = document.getElementById("location").value;
    const deadline_date = document.getElementById("deadline").value;
    const max_salary = document.getElementById("salaryMax").value;
    const min_salary = document.getElementById("salaryMin").value;
    const job_type = document.querySelector('input[name="job_type"]:checked')?.value;
    const techStackList = techStack.split(",").map(item => item.trim());

    const jobPostUpdate = {
        id: id,
        title: title,
        description: description,
        tech_stack: techStackList,
        location: location,
        level: level,
        max_salary: max_salary,
        min_salary: min_salary,
        deadline_date: deadline_date,
        job_type: job_type,
    };

    const response = await fetch('/save-edit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jobPostUpdate)
    });

    if (response.ok) {
        window.location.href = 'http://localhost:8080/job/' + id;
    }

}