import React from "react";
import {Button} from "react-bootstrap";
import {Link} from "react-router-dom";

export default function Navbar() {
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light bg-primary">
            <div className="container-fluid">
                <a className="navbar-brand" href="#">Banking Application</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                    <li className="nav-item">
                    <Link to="/">
                    <a className="nav-link active" aria-current="page" href="/">Home</a>
                    </Link>
                    </li>
                    <li className="nav-item">
                    <Link to="/accounts">
                    <a className="nav-link active" aria-current="page" href="/">Accounts</a>
                    </Link>
                    </li>
                    <li className="nav-item">
                    <Link to="/transactions">
                    <a className="nav-link active" aria-current="page" href="/">Transactions</a>
                    </Link>
                    </li>
                    <li className="nav-item">
                    <Link to="/make-transaction">
                    <a className="nav-link active" aria-current="page" href="/">Make Transaction</a>
                    </Link>
                    </li>
                </ul>
                </div>

                <Link to="/logout">
                <Button className="btn btn-outline-light mx-2">
                    <a href="/logout">Logout</a>
                </Button>
                </Link>

                <Link to="/profile">
                <Button className="btn btn-outline-light mx-2">
                    <a href="/profile">Profile</a>
                </Button>
                </Link>
            </div>
            </nav>
        </div>
    );
    }