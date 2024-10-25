// MenuGrid.jsx
import React from 'react';
import './MenuGrid.css';
import FoodData from './FoodData';
import Card from '../Card/Card';

const MenuGrid = () => {
    return (
        <div className="menu-grid">
            {FoodData.map((food) => (
                <Card key={food.id} {...food} />
            ))}
        </div>
    );
};

export default MenuGrid;
