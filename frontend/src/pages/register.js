import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

export default function Register() {
  const [registerData, setRegisterData] = useState({
    username: "",
    first_name: "",
    last_name: "",
    email: "",
    password: "",
    role: "USER"
  });

  let navigate = useNavigate();

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setRegisterData({ ...registerData, [name]: value });
  };

  const handleSubmit = async (event) => {
  event.preventDefault();
  const response = await axios.post("http://localhost:8080/api/v1/users/register",registerData);
  navigate('/login');
  };

  return (
    <div className="container">
      <h1>Register</h1>
      <div className="py-4">
        <h1 className="display-4">Register for an account</h1>
        <hr className="my-4" />
      </div>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="username" className="form-label">
            Username
          </label>
          <input
            type="text"
            id="username"
            name="username"
            className="form-control"
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="firstName" className="form-label">
            First Name
          </label>
          <input
            type="text"
            id="firstName"
            name="firstName"
            className="form-control"
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="lastName" className="form-label">
            Last Name
          </label>
          <input
            type="text"
            id="lastName"
            name="lastName"
            className="form-control"
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="from-group">
          <label htmlFor="email" className="form-label">
            Email address
          </label>
          <input
            type="email"
            id="email"
            name="email"
            className="form-control"
            aria-describedby="emailHelp"
            onChange={handleInputChange}
            required
          />
          <div id="emailHelp" className="form-text">
            We'll never share your email with anyone else.
          </div>
        </div>
        <div className="form-group">
          <label htmlFor="password" className="form-label">
            Password
          </label>
          <input
            type="password"
            id="password"
            name="password"
            className="form-control"
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="confirmPassword" className="form-label">
            Confirm Password
          </label>
          <input
            type="password"
            id="confirmPassword"
            name="confirmPassword"
            className="form-control"
            aria-describedby="passwordHelpInline"
            onChange={handleInputChange}
            required
          />
        </div>
       </form>
    </div>
    );
}
