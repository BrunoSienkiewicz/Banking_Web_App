import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import axios from 'axios';
import AdminNavbar from './layout/AdminNavbar';
import UserNavbar from './layout/UserNavbar';
import DefaultNavbar from './layout/DefaultNavbar';
import Home from './pages/home';
import Login from './pages/login';
import Profile from './pages/profile';
import Register from './pages/register';
import AllAccounts from './pages/allAccounts';
import AllUsers from './pages/allUsers';
import AllTransactions from './pages/allTransactions';
import MakeTransaction from './pages/makeTransaction';
import UserTransactions from './pages/userTransactions';
import UserAccounts from './pages/userAccounts';
import { BrowserRouter as Router, Route, Routes, } from 'react-router-dom';
import AddAccount from './Account/AddAccount';

function App() {
  const userRole = localStorage.getItem('role');

  function renderNavbar() {
    console.log(userRole);
    if (userRole === 'ADMIN') {
      return <AdminNavbar />;
    } else if (userRole === 'USER') {
      return <UserNavbar />;
    } else {
      return <DefaultNavbar/>;
    }
  }

  function logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('username');
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
          <Route path='/allusers' element={<AllUsers/>}/>
          <Route path='/alltransactions' element={<AllTransactions/>}/>
          <Route path='/accounts/add' element={<AddAccount/>}/>
          <Route path='/logout' element={<Home/>} onEnter={logout}/>
          <Route path='/profile' element={<Profile/>}/>
          <Route path = '/make-transaction' element={<MakeTransaction/>}/>
          <Route path = '/transactions' element={<UserTransactions/>}/>
          <Route path = '/accounts' element={<UserAccounts/>}/>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
