// Header.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useCart } from './CartContext';
import './Header.css';
import logo from './logo.png'; // Import the logo image

const Header = () => {
  const navigate = useNavigate();
  const { cartItemCount } = useCart();

  const handleLogout = () => {
    localStorage.removeItem('token');
    window.location.href = '/login';
  };

  return (
    <header className="header">
      <div className="header-left">
        <img src={logo} alt="Logo" className="logo" />
        <input
          type="text"
          placeholder="Search..."
          className="search-box"
        />
      </div>
      <div className="header-right">
        <button className="header-button" onClick={() => navigate('/dashboard')}>Home</button>
        <button className="header-button" onClick={() => navigate('/profile')}>Profile</button>
        <button className="header-button" onClick={() => navigate('/settings')}>Settings</button>
        <button className="header-button" onClick={() => navigate('/cart')}>Cart ({cartItemCount})</button>
        <button className="header-button" onClick={handleLogout}>Logout</button>
      </div>
    </header>
  );
};

export default Header;
