import React, { useState, useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import axios from "axios";

function TransactionPage() {
  let navigate = useNavigate();

  const [selectedFromAccount, setSelectedFromAccount] = useState("");
  const [toAccount, setToAccount] = useState("");
  const [amount, setAmount] = useState("");

  const username = localStorage.getItem("username");

  const [accounts, setAccounts] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/accounts/username/" + username)
      .then((response) => {
        setAccounts(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("/api/transactions", {
        fromAccount: selectedFromAccount,
        toAccount: toAccount,
        amount: amount,
      });
    navigate('/accounts');
  };

  const handleToAccountChange = (event) => {
    setToAccount(event.target.value);
  };

  return (
    <div className="container">
      <h1>Make a Transaction</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="fromAccount">From Account</label>
          <select
            className="form-control"
            id="fromAccount"
            value={selectedFromAccount}
            onChange={(event) => setSelectedFromAccount(event.target.value)}
          >
            <option value="">Select an account</option>
            {accounts.map((account) => (
              <option key={account.id} value={account.id}>
                {account.account_number} ({account.balance})
              </option>
            ))}
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="toAccount">To Account:</label>
          <input type="number" className="form-control" id="toAccount" name="toAccount" value={toAccount} onChange={handleToAccountChange} required />
        </div>
        <div className="form-group">
          <label htmlFor="amount">Amount</label>
          <input
            type="number"
            className="form-control"
            id="amount"
            value={amount}
            onChange={(event) => setAmount(event.target.value)}
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Submit
        </button>
      </form>
    </div>
  );
}

export default TransactionPage;
