import React from 'react';
import FoodCard from '../FoodCard';

const foodData = [
    { id: 1, name: '싸이버거', rating: 4.0, calories: 574, protein: 12, imageUrl: '/images/cyburger.jpg' },
    { id: 2, name: '싸이버거', rating: 4.0, calories: 574, protein: 12, imageUrl: '/images/cyburger.jpg' },
    // ... 다른 음식들
];

const MenuGrid = () => {
    return (
        <div className="menu-grid">
            {foodData.map((food) => (
                <FoodCard key={food.id} food={food} />
            ))}
        </div>
    );
};

export default MenuGrid;
