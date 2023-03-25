import React from "react";
import axios from 'axios';

export default function login() {

  //   const [email, setEmail] = useState('');
  //   const [password, setPassword] = useState('');

  //   const handleSubmit = async (event) => {
  //   event.preventDefault();
  //       const response = await axios.post('http://localhost:8080/api/v1/login', {
  //       email,
  //       password,
  //   });
  //   if (response.ok) {
  //     // handle successful login
  //   } else {
  //     // handle failed login
  //   }
  // };

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
        <form>
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email"></input>
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"></input>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        </div>
    );
    }