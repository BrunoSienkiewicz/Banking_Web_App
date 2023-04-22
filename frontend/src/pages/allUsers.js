import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table } from 'react-bootstrap';
import { Link, Router, Routes, Route } from 'react-router-dom';

function UsersPage() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = async ()=> {
    const result = await axios.get('http://localhost:8080/api/v1/users/all');
    setUsers(result.data);
    console.log(result.data);
  }

  return (
    <div className="container mt-4">
      <h1>Accounts</h1>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Username</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Role</th>
          </tr>
        </thead>
        <tbody>
            {users.map(user => (
                <tr key={user.id}>
                    <td>{user.username}</td>
                    <td>{user.first_name}</td>
                    <td>{user.last_name}</td>
                    <td>{user.role}</td>
                    <td>
                        <Link className="btn btn-primary mr-2" to={`/users/${user.id}`}>View</Link>
                        <Link className="btn btn-outline-primary mr-2" to={`/users/edit/${user.id}`}>Edit</Link>
                        <Link className="btn btn-danger" to={`/users/delete/${user.id}`}>Delete</Link>
                    </td>
                </tr>
            ))}
        </tbody>
        <Link className="btn btn-primary mr-2" to={`/users/add`}>Add User</Link>
      </Table>
    </div>
  );
}

export default UsersPage;