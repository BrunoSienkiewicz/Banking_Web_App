import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table } from 'react-bootstrap';
import { Link, Router, Routes, Route } from 'react-router-dom';

function TransactionsPage() {
    const [transactions, setTransactions] = useState([]);
    
    useEffect(() => {
        loadTransactions();
    }, []);
    
    const loadTransactions = async ()=> {
        const result = await axios.get('http://localhost:8080/api/v1/transactions/all');
        setTransactions(result.data);
        console.log(result.data);
    }
    
    return (
        <div className="container mt-4">
        <h1>Transactions</h1>
        <Table striped bordered hover>
            <thead>
            <tr>
                <th>Date</th>
                <th>Account Number</th>
                <th>Transaction Type</th>
                <th>Amount</th>
            </tr>
            </thead>
            <tbody>
            {transactions.map(transaction => (
                <tr key={transaction.id}>
                <td>{transaction.date}</td>
                <td>{transaction.account_number}</td>
                <td>{transaction.type}</td>
                <td>{transaction.amount}</td>
                </tr>
            ))}
            </tbody>
        </Table>
        </div>
    );
    }

export default TransactionsPage;