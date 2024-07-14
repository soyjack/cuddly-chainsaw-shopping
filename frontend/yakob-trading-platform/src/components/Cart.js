import React from 'react';
import { useCart } from './CartContext';
import './Dashboard.css'; // Reuse the same styles

/**
 * The Cart component renders the items added to the cart.
 * It provides functionality to remove items and proceed to checkout.
 */
const Cart = () => {
  // Retrieve cart-related functions and state from CartContext
  const { cart, removeFromCart, clearCart } = useCart();

  /**
   * Handles the checkout process.
   * Currently, it displays an alert and clears the cart.
   */
  const handleCheckout = () => {
    alert('Checkout successful!');
    clearCart();
  };

  return (
    <div className="dashboard">
      <div className="item-list">
        {cart.length > 0 ? (
          cart.map(item => (
            <div key={item.id} className="cart-item">
              <h3>{item.itemName}</h3>
              <p>Price: ${item.price}</p>
              <p>Quantity: {item.quantity}</p>
              <button onClick={() => removeFromCart(item.id)}>Remove</button>
            </div>
          ))
        ) : (
          <p className="no-items-found">No items in cart</p>
        )}
      </div>
      {cart.length > 0 && (
        <button className="checkout-button" onClick={handleCheckout}>Check Out</button>
      )}
    </div>
  );
};

export default Cart;
