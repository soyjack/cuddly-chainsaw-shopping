import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import Dashboard from './components/Dashboard';
import Cart from './components/Cart';
import Profile from './components/Profile';
import Settings from './components/Settings';
import Header from './components/Header';
import { AuthProvider, AuthContext } from './components/AuthContext';
import { CartProvider } from './components/CartContext';
import './styles.css';

/**
 * PrivateRoute component restricts access to authenticated users only.
 * If the user is not authenticated, they are redirected to the login page.
 * 
 * @param {Object} children - The child components to render if authenticated.
 */
const PrivateRoute = ({ children }) => {
  const { isAuthenticated } = React.useContext(AuthContext);
  return isAuthenticated ? children : <Navigate to="/login" />;
};

/**
 * App component sets up the main structure and routing for the application.
 * It includes context providers for authentication and cart management,
 * and defines routes for various components.
 */
const App = () => {
  return (
    <AuthProvider>
      <CartProvider>
        <Router>
          <div className="App">
            {/* Conditionally render the Header component based on authentication status */}
            <AuthContext.Consumer>
              {({ isAuthenticated }) => isAuthenticated && <Header />}
            </AuthContext.Consumer>
            <main>
              <Routes>
                {/* Define private routes that require authentication */}
                <Route path="/dashboard" element={<PrivateRoute><Dashboard /></PrivateRoute>} />
                <Route path="/cart" element={<PrivateRoute><Cart /></PrivateRoute>} />
                <Route path="/profile" element={<PrivateRoute><Profile /></PrivateRoute>} />
                <Route path="/settings" element={<PrivateRoute><Settings /></PrivateRoute>} />

                {/* Define public routes */}
                <Route path="/" element={<Navigate to="/dashboard" />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
              </Routes>
            </main>
          </div>
        </Router>
      </CartProvider>
    </AuthProvider>
  );
};

export default App;
