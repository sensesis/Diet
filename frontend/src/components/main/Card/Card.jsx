// Card.jsx
import React from 'react';
import './Card.css';
import star from '../../../assets/images/Card/star.png';

const Card = ({ name, category, rating, calories, protein, sugar, image }) => {
    return (
        <div className="card">
            <img src={image} alt={name} className="card-image" />
            <div className="card-content">
                <h3 className="card-title">{name}</h3>
                <div className="card-rating">
                    <img src={star} alt="star" className="card-star" />
                    <span>{rating}</span>
                    <span className="card-category">{category}</span>
                </div>
                <div className="card-info">
                    <p>칼로리: {calories} kcal</p>
                    <p>단백질: {protein} g</p>
                    <p>당: {sugar} g</p>
                </div>
                <button className="card-button">자세히 보기</button>
            </div>
        </div>
    );
};

export default Card;