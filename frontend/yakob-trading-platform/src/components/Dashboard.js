import React, { useEffect, useState } from 'react';
import ItemCard from './ItemCard';
import { useCart } from './CartContext';
import './Dashboard.css';

/**
 * The Dashboard component is responsible for displaying a list of items fetched from the server.
 * It also allows users to add items to their cart.
 */
const Dashboard = () => {
  const [items, setItems] = useState([]);
  const { addToCart } = useCart();

  /**
   * useEffect hook to fetch items from the server when the component mounts.
   * It retrieves the authentication token from local storage and includes it in the request headers.
   */
  useEffect(() => {
    const fetchItems = async () => {
      const token = localStorage.getItem('token'); // Get the token from local storage

      if (!token) {
        console.error('No token found');
        return;
      }

      try {
        const response = await fetch('http://localhost:8080/api/itemposts/all', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`, // Include the bearer token
          },
        });

        if (response.status === 401) {
          console.error('Unauthorized');
          return;
        }

        const data = await response.json();
        setItems(data);
      } catch (error) {
        console.error('Error fetching items:', error);
      }
    };

    fetchItems();
  }, []);

  return (
    <div className="dashboard">
      <div className="item-list">
        {items.length > 0 ? (
          items.map((item) => (
            <ItemCard key={item.id} item={item} onAddToCart={addToCart} />
          ))
        ) : (
          <p className="no-items-found">No items found</p>
        )}
      </div>
    </div>
  );
};

export default Dashboard;
