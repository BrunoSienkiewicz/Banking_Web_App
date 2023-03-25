import React, { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import axios from "axios";
import jwt_decode from "jwt-decode";

function TransactionPage() {
  const [accounts, setAccounts] = useState([]);
  const [selectedFromAccount, setSelectedFromAccount] = useState("");
  const [selectedToAccount, setSelectedToAccount] = useState("");
  const [amount, setAmount] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const history = useHistory();

  const token = localStorage.getItem("jwtToken");

  // Decode the token to extract the payload
  const decodedToken = jwt_decode(token);

  // Extract the username from the decoded payload
  const username = decodedToken.sub;

  useEffect(() => {
    // Retrieve the user's accounts from the API
    axios
      .get("/api/v1/accounts/getbyusername/" + username)
      .then((response) => {
        setAccounts(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();

    // Send the transaction data to the API
    axios
      .post("/api/transactions", {
        fromAccount: selectedFromAccount,
        toAccount: selectedToAccount,
        amount: amount,
      })
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
                {account.accountNumber} ({account.balance})
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
