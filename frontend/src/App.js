import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import AdminNavbar from './layout/AdminNavbar';
import Home from './pages/home';
import Login from './pages/login';
import Register from './pages/register';
import AllAccounts from './pages/allAccounts';
import { BrowserRouter as Router, Route, Routes, UNSAFE_RouteContext } from 'react-router-dom';
import AddAccount from './Account/AddAccount';

function App() {
  return (
    <div className="App">
      <Router>
        <AdminNavbar/>
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
