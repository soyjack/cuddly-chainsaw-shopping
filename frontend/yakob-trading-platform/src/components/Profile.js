// Profile.js
import React, { useState, useEffect } from 'react';
import './Profile.css';

const Profile = () => {
  const [items, setItems] = useState([]);
  const [form, setForm] = useState({ name: '', description: '', price: '', image: '' });
  const [isEditing, setIsEditing] = useState(false);
  const [editId, setEditId] = useState(null);

  useEffect(() => {
    // Fetch user items/posts (replace with your API endpoint)
    fetch('/api/user-items')
      .then(response => response.json())
      .then(data => setItems(data))
      .catch(error => console.error('Error fetching items:', error));
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm(prevForm => ({ ...prevForm, [name]: value }));
  };

  const handleCreateOrUpdate = (e) => {
    e.preventDefault();
    const method = isEditing ? 'PUT' : 'POST';
    const url = isEditing ? `/api/items/${editId}` : '/api/items';

    fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form),
    })
      .then(response => response.json())
      .then(data => {
        if (isEditing) {
          setItems(prevItems => prevItems.map(item => (item.id === editId ? data : item)));
        } else {
          setItems(prevItems => [...prevItems, data]);
        }
        setForm({ name: '', description: '', price: '', image: '' });
        setIsEditing(false);
        setEditId(null);
      })
      .catch(error => console.error('Error saving item:', error));
  };

  const handleEdit = (item) => {
    setForm({ name: item.name, description: item.description, price: item.price, image: item.image });
    setIsEditing(true);
    setEditId(item.id);
  };

  const handleDelete = (id) => {
    fetch(`/api/items/${id}`, { method: 'DELETE' })
      .then(() => {
        setItems(prevItems => prevItems.filter(item => item.id !== id));
      })
      .catch(error => console.error('Error deleting item:', error));
  };

  return (
    <div className="profile">
      <h2>Your Items</h2>
      <form onSubmit={handleCreateOrUpdate} className="profile-form">
        <div className="form-group">
          <label htmlFor="name">Item Name</label>
          <input type="text" id="name" name="name" value={form.name} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label htmlFor="description">Description</label>
          <input type="text" id="description" name="description" value={form.description} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label htmlFor="price">Price</label>
          <input type="text" id="price" name="price" value={form.price} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label htmlFor="image">Image URL</label>
          <input type="text" id="image" name="image" value={form.image} onChange={handleChange} required />
        </div>
        <button type="submit" className="update-button">{isEditing ? 'Update Item' : 'Create Item'}</button>
      </form>
      <div className="items-list">
        {items.map(item => (
          <div key={item.id} className="item-card">
            <img src={item.image} alt={item.name} className="item-image" />
            <h3>{item.name}</h3>
            <p>{item.description}</p>
            <p>{item.price}</p>
            <button onClick={() => handleEdit(item)}>Edit</button>
            <button onClick={() => handleDelete(item.id)}>Delete</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Profile;
