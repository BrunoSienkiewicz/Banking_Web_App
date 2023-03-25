import React from "react";

export default function register() {
    return (
        <div className="container">
        <h1>Register</h1>
        <div className="py-4">
            <h1 className="display-4">Register for an account</h1>
            <hr className="my-4" />
        </div>
        <form>
        <div class="form-group">
            <label for="inputName" class="form-label">Username</label>
            <input type="name" id="inputName" class="form-control"></input>
        </div>
        <div class="form-group">
            <label for="inputName" class="form-label">First Name</label>
            <input type="name" id="inputName" class="form-control"></input>
        </div>
        <div>
            <label for="inputName" class="form-label">Last Name</label>
            <input type="name" id="inputName" class="form-control"></input>
        </div>
        <div class="from-group">
            <label for="exampleInputEmail1" class="form-label">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
            </input>
            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1"></input>
        </div>
        <div class="mb-3">
            <label for="inputPassword6" class="form-label">Confirm Password</label>
            <input type="password" id="inputPassword6" class="form-control" aria-describedby="passwordHelpInline"></input>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        </div>
    );
    }