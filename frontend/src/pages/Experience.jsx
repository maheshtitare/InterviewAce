import { useEffect, useState } from "react";
import axios from "axios";

function Experience() {
  const [experiences, setExperiences] = useState([]);

  const [formData, setFormData] = useState({
    companyName: "",
    designation: "",
    description: "",
    startDate: "",
    endDate: "",
    currentlyWorking: false
  });

  const token = localStorage.getItem("token");

  const fetchExperiences = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/experience",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      setExperiences(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchExperiences();
  }, []);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;

    setFormData({
      ...formData,
      [name]: type === "checkbox" ? checked : value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await axios.post(
        "http://localhost:8080/api/experience",
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      setFormData({
        companyName: "",
        designation: "",
        description: "",
        startDate: "",
        endDate: "",
        currentlyWorking: false
      });

      fetchExperiences();
    } catch (error) {
      console.error(error);
    }
  };

  const deleteExperience = async (id) => {
    try {
      await axios.delete(
        `http://localhost:8080/api/experience/${id}`,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      fetchExperiences();
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="container mt-4">
      <h2>Experience</h2>

      <form onSubmit={handleSubmit}>

        <input
          type="text"
          name="companyName"
          placeholder="Company Name"
          value={formData.companyName}
          onChange={handleChange}
          className="form-control mb-2"
          required
        />

        <input
          type="text"
          name="designation"
          placeholder="Designation"
          value={formData.designation}
          onChange={handleChange}
          className="form-control mb-2"
          required
        />

        <textarea
          name="description"
          placeholder="Description"
          value={formData.description}
          onChange={handleChange}
          className="form-control mb-2"
        />

        <input
          type="date"
          name="startDate"
          value={formData.startDate}
          onChange={handleChange}
          className="form-control mb-2"
          required
        />

        <input
          type="date"
          name="endDate"
          value={formData.endDate}
          onChange={handleChange}
          className="form-control mb-2"
          disabled={formData.currentlyWorking}
        />

        <div className="form-check mb-3">
          <input
            type="checkbox"
            name="currentlyWorking"
            checked={formData.currentlyWorking}
            onChange={handleChange}
            className="form-check-input"
          />

          <label className="form-check-label">
            Currently Working
          </label>
        </div>

        <button
          type="submit"
          className="btn btn-primary"
        >
          Add Experience
        </button>
      </form>

      <hr />

      <h4>Experience List</h4>

      {experiences.map((exp) => (
        <div
          key={exp.id}
          className="card mb-3"
        >
          <div className="card-body">

            <h5>
              {exp.designation}
            </h5>

            <p>
              <strong>Company:</strong> {exp.companyName}
            </p>

            <p>
              <strong>Description:</strong> {exp.description}
            </p>

            <p>
              <strong>Start:</strong> {exp.startDate}
            </p>

            <p>
              <strong>End:</strong>{" "}
              {exp.currentlyWorking
                ? "Present"
                : exp.endDate}
            </p>

            <button
              className="btn btn-danger"
              onClick={() =>
                deleteExperience(exp.id)
              }
            >
              Delete
            </button>

          </div>
        </div>
      ))}
    </div>
  );
}

export default Experience;