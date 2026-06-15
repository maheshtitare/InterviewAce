import React, { useEffect, useState } from "react";
import './Resume.css';
import axios from "axios";

function Resume() {

const [resumeData, setResumeData] = useState(null);
const [file, setFile] = useState(null);

const token = localStorage.getItem("token");

useEffect(() => {
loadResume();
}, []);

const loadResume = async () => {
try {

  const response = await axios.get(
    "http://localhost:8080/api/resume",
    {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }
  );

  setResumeData(response.data);

} catch (error) {
  console.error(error);
}

};

const handleUpload = async () => {

if (!file) {
  alert("Select PDF first");
  return;
}

const formData = new FormData();

formData.append("file", file);

try {

  const response = await axios.post(
    "http://localhost:8080/api/resume/upload",
    formData,
    {
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "multipart/form-data"
      }
    }
  );

  alert(response.data.message);

} catch (error) {
  console.error(error);
}

};

return (
  <div className="resume-container">

    <div className="resume-card">

      <h2 className="resume-title">
        Resume Module
      </h2>

      <div className="upload-section">

        <h3>Upload Resume PDF</h3>

        <input
          type="file"
          accept=".pdf"
          onChange={(e) =>
            setFile(e.target.files[0])
          }
        />

        <br />

        <button
          className="upload-btn"
          onClick={handleUpload}
        >
          Upload Resume
        </button>

      </div>

      {resumeData && (

        <div className="resume-preview">

          <h3>Resume Preview</h3>

          <div className="info-row">
            <strong>Name:</strong>
            {resumeData.fullName}
          </div>

          <div className="info-row">
            <strong>Email:</strong>
            {resumeData.email}
          </div>

          <div className="info-row">
            <strong>Mobile:</strong>
            {resumeData.mobileNumber}
          </div>

          <h4>Skills</h4>

          <ul>
            {resumeData.skills?.map((skill) => (
              <li key={skill.id}>
                {skill.skillName}
              </li>
            ))}
          </ul>

          <h4>Education</h4>

          <ul>
            {resumeData.educations?.map((edu) => (
              <li key={edu.id}>
                {edu.degree}
              </li>
            ))}
          </ul>

          <h4>Projects</h4>

          <ul>
            {resumeData.projects?.map((project) => (
              <li key={project.id}>
                {project.title}
              </li>
            ))}
          </ul>

          <h4>Experience</h4>

          <ul>
            {resumeData.experiences?.map((exp) => (
              <li key={exp.id}>
                {exp.designation}
                {" - "}
                {exp.companyName}
              </li>
            ))}
          </ul>

        </div>

      )}

    </div>

  </div>
);
}

export default Resume;
