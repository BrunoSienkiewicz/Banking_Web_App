import React from "react"
import axios from 'axios'
import {useState} from 'react'
import { useNavigate } from 'react-router-dom';

export default function Login() {

    let navigate = useNavigate();

    const [login,setLogin] = useState({
        email: "",
        password: ""
    });

    const {email, password} = login;

    const onInputChange = (e) => {
        setLogin({...login, [e.target.name]: e.target.value});
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/v1/users/login', login);
            localStorage.setItem('token', response.data.token);
            navigate('/');
        }
        catch (error) {
            console.log(error);
        }
    }

    return (
        <div className="container">
        <h1>Login</h1>
        <div className="py-4">
            <h1 className="display-4">Login to your account</h1>
            <p className="lead">
            Please enter your email and password to login to your account.
            </p>
            <hr className="my-4" />
        </div>
        <form onSubmit={onSubmit}>
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name="email" value={email} onChange={onInputChange} />
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password" value={password} onChange={onInputChange} />
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        </div>
    );
    }