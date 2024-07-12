// ItemCard.js
import React, { useState } from 'react';
import './ItemCard.css'; // Ensure you have styles for ItemCard

const ItemCard = ({ item, onAddToCart }) => {
  const [isHovered, setIsHovered] = useState(false);

  return (
    <div
      className="item-card"
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      {isHovered ? (
        <p className="item-description">{item.description}</p>
      ) : (
        <img src={item.image} alt={item.name} className="item-image" />
      )}
      <h3>{item.name}</h3>
      <p>{item.price}</p> {/* Display price */}
      <button onClick={() => onAddToCart(item)}>Add to Cart</button>
    </div>
  );
};

export default ItemCard;
