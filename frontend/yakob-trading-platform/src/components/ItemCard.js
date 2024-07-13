import React, { useState } from 'react';
import './ItemCard.css';

const ItemCard = ({ item, onAddToCart }) => {
  const [isHovered, setIsHovered] = useState(false);

  return (
    <div
      className="item-card"
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      {isHovered ? (
        <p className="item-description">{item.itemDescription}</p>
      ) : (
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
