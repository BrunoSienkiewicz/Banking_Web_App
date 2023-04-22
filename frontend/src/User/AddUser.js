import React from 'react'
import axios from 'axios'
import {useState} from 'react'
import { useNavigate } from 'react-router-dom';
import { InputGroup } from 'react-bootstrap';

export default function AddUser() {

    let navigate = useNavigate();

    const [user,setUser] = useState({
        username: "",
        password: "",
        first_name: "",
        last_name: "",
        email: "",
        role: ""
    });

    const {username, password, first_name, last_name, email, role} = user;

    const onInputChange = (e) => {
        setAccount({...user, [e.target.name]: e.target.value});
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.post('http://localhost:8080/api/v1/users/add', user);
        navigate('/');
    }

    return (
    <div className="container">
      <form onSubmit={onSubmit}>
        <div className="form-group">
          <label htmlFor="username" className="form-label">
            Username
          </label>
          <input
            type="text"
            id="username"
            name="username"
            className="form-control"
            onChange={onInputChange}
            value={username}
            required
          />
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
                onChange={onInputChange}
                value={password}
                required
            />
        </div>
        <div className="form-group">
            <label htmlFor="first_name" className="form-label">
                First Name
            </label>
            <input
                type="text"
                id="first_name"
                name="first_name"
                className="form-control"
                onChange={onInputChange}
                value={first_name}
                required
            />
        </div>
        <div className="form-group">
            <label htmlFor="last_name" className="form-label">
                Last Name
            </label>
            <input
                type="text"
                id="last_name"
                name="last_name"
                className="form-control"
                onChange={onInputChange}
                value={last_name}
                required
            />
        </div>
        <div className="form-group">
            <label htmlFor="email" className="form-label">
                Email
            </label>
            <input
                type="email"
                id="email"
                name="email"
                className="form-control"
                onChange={onInputChange}
                value={email}
                required
            />
        </div>
        <div className="form-group">
            <label htmlFor="role" className="form-label">
                Role
            </label>
            <input
                type="text"
                id="role"
                name="role"
                className="form-control"
                onChange={onInputChange}
                value={role}
                required
            />
        </div>
        <button className="btn btn-primary btn-block">Add User</button>
       </form>
    </div>
    );
}
