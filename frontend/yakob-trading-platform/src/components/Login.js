// Login.js
import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from './AuthContext';
import './Login.css';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();
  const { login } = useContext(AuthContext);

  const handleLogin = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch('http://localhost:8081/authenticate/signin', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        throw new Error('Login failed');
      }

      const data = await response.json();
      console.log("Response data:", data); // Log the entire response

      if (!data.jwt) {
        throw new Error('Token not received');
      }

      console.log("Token received:", data.jwt); // Log the token received
      localStorage.setItem('token', data.jwt);
      login(data.jwt);
      setMessage('Login successful');
      setError('');
      navigate('/dashboard');
    } catch (error) {
      console.error('Error during login:', error); // Log any errors
      setError(error.message);
      setMessage('');
    }
  };

  const handleRegisterRedirect = () => {
    navigate('/register');
  };

  return (
    <div className="login-container">
      <h2>TradeShop</h2>
      <form onSubmit={handleLogin} className="login-form">
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
        <button type="submit" className="login-button">Login</button>
      </form>
      <p className="register-redirect">
        Don't have an account? <button onClick={handleRegisterRedirect} className="register-button">Create an account</button>
      </p>
      {message && <p className="login-message">{message}</p>}
      {error && <p className="login-error">{error}</p>}
    </div>
  );
};

export default Login;
