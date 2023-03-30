import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import axios from 'axios';
import AdminNavbar from './layout/AdminNavbar';
import UserNavbar from './layout/UserNavbar';
import DefaultNavbar from './layout/DefaultNavbar';
import Home from './pages/home';
import Login from './pages/login';
import Register from './pages/register';
import AllAccounts from './pages/allAccounts';
import { BrowserRouter as Router, Route, Routes, } from 'react-router-dom';
import AddAccount from './Account/AddAccount';
import { useEffect, useState } from 'react';

function App() {
  const [userRole, setUserRole] = useState('');

  useEffect(() => {
    async function fetchUserRole() {
      const token = localStorage.getItem('token');
      if (token) {
        const response = await axios.post('http://localhost:8080/api/v1/users/token-username', token);
        setUserRole(response.data);
      }
    }
    fetchUserRole();
  }, []);

  function renderNavbar() {
    if (userRole === 'admin') {
      return <AdminNavbar />;
    } else if (userRole === 'user') {
      return <UserNavbar />;
    } else {
      return <DefaultNavbar/>;
    }
  }

  return (
    <div className="App">
      <Router>
        {renderNavbar()}
        <Routes>
          <Route path='/' element={<Home/>}/>
          <Route path='/login' element={<Login/>}/>
          <Route path='/register' element={<Register/>}/>
          <Route path='/allaccounts' element={<AllAccounts/>}/>
          {/* <Route path='/allusers' element={<AllUsers/>}/>
          <Route path='/alltransactions' element={<AllTransactions/>}/> */}
          <Route path='/accounts/add' element={<AddAccount/>}/>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
