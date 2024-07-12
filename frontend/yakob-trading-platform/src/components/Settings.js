// Settings.js
import React, { useState } from 'react';
import './Settings.css';

const Settings = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');

  const handleUpdate = async (event) => {
    event.preventDefault();
    // Perform the update logic, e.g., call an API to update user info
    // For now, we just show a success message
    setMessage('Profile updated successfully');
  };

  const handleDeleteAccount = async () => {
    // Perform the account deletion logic, e.g., call an API to delete the account
    // For now, we just show a success message
    setMessage('Account deleted successfully');
    // Redirect to login or homepage after account deletion
    localStorage.removeItem('token');
    window.location.href = '/login';
  };

  return (
    <div className="settings">
      <h2>Settings</h2>
      <form onSubmit={handleUpdate} className="settings-form">
        <div className="form-group">
          <label htmlFor="username">Username</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="update-button">Update Profile</button>
      </form>
      <button onClick={handleDeleteAccount} className="delete-button">Delete Account</button>
      {message && <p className="message">{message}</p>}
    </div>
  );
};

export default Settings;
