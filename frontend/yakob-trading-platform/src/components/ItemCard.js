import React, { useState } from 'react';
import './ItemCard.css';

/**
 * The ItemCard component displays the details of an item.
 * It allows users to view the item image, description, price, and seller information,
 * and provides an option to add the item to the cart.
 * 
 * @param {Object} item - The item details to display.
 * @param {Function} onAddToCart - Function to add the item to the cart.
 */
const ItemCard = ({ item, onAddToCart }) => {
  const [isHovered, setIsHovered] = useState(false);

  return (
    <div
      className="item-card"
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      {isHovered ? (
        // Display item description when hovered
        <p className="item-description">{item.itemDescription}</p>
      ) : (
        // Display item image when not hovered
        <img src={item.imageName} alt={item.itemName} className="item-image" />
      )}
      <h3>{item.itemName}</h3>
      <p>{item.price}</p> {/* Display price */}
      <button onClick={() => onAddToCart(item)}>Add to Cart</button>
      <p>Seller: {item.seller?.username}</p> {/* Display seller information */}
    </div>
  );
};

export default ItemCard;
