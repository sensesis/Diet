// src/components/main/Card/Card.jsx
import React from 'react';
import './Card.css';

// 별 이미지 불러오기
import starFull from '../../../assets/images/Card/star_full.png';
import starHalf from '../../../assets/images/Card/star_half.png';
import starEmpty from '../../../assets/images/Card/star_empty.png';
import defaultImage from '../../../assets/images/Card/image.png'; // 기본 이미지 예시

// 추가 이미지 불러오기
import stick from '../../../assets/images/Card/stick.png';
import allow from '../../../assets/images/Card/allow.png';

const Card = ({ name, category, rating, calories, protein, sugar, image }) => {
    // 별점 렌더링 함수
    const renderStars = (rating) => {
        const stars = [];
        const fullStars = Math.floor(rating);
        const halfStar = rating % 1 >= 0.5;
        const emptyStars = 5 - fullStars - (halfStar ? 1 : 0);

        for (let i = 0; i < fullStars; i++) {
            stars.push(
                <img
                    key={`full-${i}`}
                    src={starFull}
                    alt="Full Star"
                    className="star"
                />
            );
        }

        if (halfStar) {
            stars.push(
                <img
                    key="half"
                    src={starHalf}
                    alt="Half Star"
                    className="star"
                />
            );
        }

        for (let i = 0; i < emptyStars; i++) {
            stars.push(
                <img
                    key={`empty-${i}`}
                    src={starEmpty}
                    alt="Empty Star"
                    className="star"
                />
            );
        }

        return stars;
    };

    return (
        <div className="card">
            <img src={image || defaultImage} alt={name} className="card-image" />
            <div className="card-content">
                <div className="title">{name}</div>
                <div className="stars-rating">
                    <div className="stars">{renderStars(rating)}</div>
                    <div className="rating">({rating})</div>
                    <div className="brand">{category}</div>
                </div>
                <div className="nutrition">
                    <div className="nutrition-item">
                        <div>칼로리</div>
                        <div className="value">{calories}kcal</div>
                    </div>
                    <div className="nutrition-item">
                        <div>단백질</div>
                        <div className="value">{protein}g</div>
                    </div>
                    <div className="nutrition-item">
                        <div>당</div>
                        <div className="value">{sugar}g</div>
                    </div>
                </div>
                <div className="details">
                    자세히 알아보기
                    <img src={allow} alt="Arrow" className="allow" />
                </div>
            </div>
        </div>
    );
};

export default Card;
