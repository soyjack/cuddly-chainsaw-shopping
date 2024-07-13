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

const PrivateRoute = ({ children }) => {
  const { isAuthenticated } = React.useContext(AuthContext);
  return isAuthenticated ? children : <Navigate to="/login" />;
};

const App = () => {
  return (
    <AuthProvider>
      <CartProvider>
        <Router>
          <div className="App">
            <AuthContext.Consumer>
              {({ isAuthenticated }) => isAuthenticated && <Header />}
            </AuthContext.Consumer>
            <main>
              <Routes>
                <Route path="/dashboard" element={<PrivateRoute><Dashboard /></PrivateRoute>} />
                <Route path="/cart" element={<PrivateRoute><Cart /></PrivateRoute>} />
                <Route path="/profile" element={<PrivateRoute><Profile /></PrivateRoute>} />
                <Route path="/settings" element={<PrivateRoute><Settings /></PrivateRoute>} />
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
