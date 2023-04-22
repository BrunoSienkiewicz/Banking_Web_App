import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table } from 'react-bootstrap';
import { Link, Router, Routes, Route } from 'react-router-dom';

function AccountsPage() {
    const [accounts, setAccounts] = useState([]);
  
    useEffect(() => {
      loadAccounts();
    }, []);
  
    const loadAccounts = async ()=> {
      const username = localStorage.getItem('username');
      const result = await axios.get('http://localhost:8080/api/v1/accounts/username/' + username);
      setAccounts(result.data);
      console.log(result.data);
    }
  
    return (
      <div className="container mt-4">
        <h1>Accounts</h1>
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>Account Number</th>
              <th>Account Type</th>
              <th>Balance</th>
            </tr>
          </thead>
          <tbody>
            {accounts.map(account => (
              <tr key={account.id}>
                <td>{account.account_number}</td>
                <td>{account.account_type}</td>
                <td>{account.balance}</td>
                <td>
                  <Link className="btn btn-primary mr-2" to={`/accounts/${account.id}`}>View</Link>
                  <Link className="btn btn-outline-primary mr-2" to={`/accounts/edit/${account.id}`}>Edit</Link>
                  <Link className="btn btn-danger" to={`/accounts/delete/${account.id}`}>Delete</Link>
                </td>
              </tr>
            ))}
          </tbody>
          <Link className="btn btn-primary mr-2" to={`/accounts/add`}>Add Account</Link>
        </Table>
      </div>
    );
  }
  
  export default AccountsPage;