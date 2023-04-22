import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table } from 'react-bootstrap';

export default function ProfilePage() {
    const username = localStorage.getItem('username');

    const [user, setUser] = useState({
        id: '',
        username: '',
        first_name: '',
        last_name: '',
        email: '',
        role: ''
    });

    useEffect(() => { 
        loadUser();
    }, []);

    const loadUser = async () => {
        const result = await axios.get(`http://localhost:8080/api/v1/users/username/${username}`);
        setUser(result.data);
    }

    return (
        <div className="container mt-4">
            <h1>Profile</h1>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{user.username}</td>
                        <td>{user.first_name}</td>
                        <td>{user.last_name}</td>
                        <td>{user.email}</td>
                    </tr>
                </tbody>
            </Table>
        </div>
    );
    }
