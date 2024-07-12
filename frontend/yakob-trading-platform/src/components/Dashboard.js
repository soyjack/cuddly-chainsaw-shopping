// Dashboard.js
import React from 'react';
import { useCart } from './CartContext';
import ItemCard from './ItemCard';
import './Dashboard.css';

const itemList = [
  { id: 1, name: 'Item 1', image: 'https://via.placeholder.com/150', description: 'Description 1' },
  { id: 2, name: 'Item 2', image: 'https://via.placeholder.com/150', description: 'Description 2' },
  { id: 3, name: 'Item 3', image: 'https://via.placeholder.com/150', description: 'Description 3' },
  // Add more items as needed
];

const Dashboard = () => {
  const { addToCart } = useCart();

  const handleAddToCart = (item) => {
    addToCart(item);
  };

  return (
    <div className="dashboard">
      <div className="item-list">
        {itemList.map(item => (
          <ItemCard key={item.id} item={item} onAddToCart={handleAddToCart} />
        ))}
      </div>
    </div>
  );
};

export default Dashboard;
