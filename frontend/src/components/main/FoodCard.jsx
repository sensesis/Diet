import React from 'react';

const FoodCard = ({ food }) => {
    return (
        <div className="food-card">
            <img src={food.imageUrl} alt={food.name} className="food-image" />
            <div className="food-info">
                <h3>{food.name}</h3>
                <p>평점: {food.rating} / 5.0</p>
                <p>칼로리: {food.calories} kcal</p>
                <p>단백질: {food.protein}g</p>
            </div>
            <button className="details-button">자세히 알아보기</button>
        </div>
    );
};

export default FoodCard;
