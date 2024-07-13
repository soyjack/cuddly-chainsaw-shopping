// Header.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useCart } from './CartContext';
import { useContext } from 'react';
import { AuthContext } from './AuthContext';
import './Header.css';
import logo from './logo.png';

const Header = () => {
  const navigate = useNavigate();
  const { cartItemCount } = useCart();
  const { logout } = useContext(AuthContext);

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <header className="App-header">
      <div className="header-content">
        <img src={logo} alt="Logo" className="dashboard-logo" />
        <nav>
          <button className="nav-button" onClick={() => navigate('/dashboard')}>Home</button>
          <button className="nav-button" onClick={() => navigate('/profile')}>Profile</button>
          <button className="nav-button" onClick={() => navigate('/settings')}>Settings</button>
          <button className="nav-button" onClick={() => navigate('/cart')}>Cart ({cartItemCount})</button>
          <button className="nav-button" onClick={handleLogout}>Logout</button>
        </nav>
      </div>
    </header>
  );
};

export default Header;
