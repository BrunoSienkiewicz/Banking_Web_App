import React from 'react'
import axios from 'axios'
import {useState} from 'react'
import { useNavigate } from 'react-router-dom';

export default function AddAccount() {

    function makeNumber(length) {
        let result = '';
        const characters = '0123456789';
        const charactersLength = characters.length;
        let counter = 0;
        while (counter < length) {
          result += characters.charAt(Math.floor(Math.random() * charactersLength));
          counter += 1;
        }
        return result;
    }

    let navigate = useNavigate();

    const [account,setAccount] = useState({
        account_number: makeNumber(6),
        account_type: "SAVINGS",
        balance: 0
    });

    const {account_number, account_type} = account;

    const onInputChange = (e) => {
        setAccount({...account, [e.target.name]: e.target.value});
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.post('http://localhost:8080/api/v1/accounts/add', account);
        navigate('/');
    }

    return (
        <div className='container'>
            <h1>Add Account</h1>
            <form onSubmit={(e)=>onSubmit(e)}> 
                <div className="form-group">
                    <label htmlFor="inputName" className="form-label">Account Number</label>
                    <input type="name" id="inputName" className="form-control" value={account_number} disabled />
                </div>
                <div className="form-group">
                    <label htmlFor="inputName" className="form-label">Account Type</label>
                    <select id="inputName" className="form-control" name="account_type" value={account_type} onChange={(e)=>onInputChange(e)}>
                        <option value="SAVINGS">SAVINGS</option>
                        <option value="CHECKING">CHECKING</option>
                    </select>
                </div>
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        </div>
    );
}
