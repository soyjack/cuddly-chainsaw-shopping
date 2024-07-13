import React from 'react';
import { useCart } from './CartContext';
import './Dashboard.css'; // Reuse the same styles

const Cart = () => {
  const { cart, removeFromCart, clearCart } = useCart();

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
