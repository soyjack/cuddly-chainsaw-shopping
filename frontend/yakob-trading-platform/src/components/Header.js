import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { useCart } from './CartContext';
import { AuthContext } from './AuthContext';
import './Header.css';
import logo from './logo.png';

/**
 * The Header component provides navigation links and a logout button.
 * It displays the application logo and dynamically updates the cart item count.
 */
const Header = () => {
  const navigate = useNavigate();
  const { cartItemCount } = useCart();
  const { logout } = useContext(AuthContext);

  /**
   * Handles the logout process by calling the logout function from AuthContext
   * and navigating the user to the login page.
   */
  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <header className="App-header">
      <div className="header-content">
        {/* Application logo */}
        <img src={logo} alt="Logo" className="dashboard-logo" />

        {/* Navigation buttons */}
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
