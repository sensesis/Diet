// Card.jsx
import React from 'react';
import './Card.css';

const Card = ({ name, category, rating, calories, protein, sugar, image }) => {
    // 별점 렌더링 함수
    const renderStars = (rating) => {
        const stars = [];
        const fullStars = Math.floor(rating); // 채워진 별의 개수
        const halfStar = rating % 1 >= 0.5; // 반 별 여부
        const emptyStars = 5 - fullStars - (halfStar ? 1 : 0);

        // 채워진 별 추가
        for (let i = 0; i < fullStars; i++) {
            stars.push(
                <img
                    key={`full-${i}`}
                    src="/assets/images/star_full.png"
                    alt="Full Star"
                    className="star"
                />
            );
        }

        // 반 별 추가
        if (halfStar) {
            stars.push(
                <img
                    key="half"
                    src="/assets/images/star_half.png"
                    alt="Half Star"
                    className="star"
                />
            );
        }

        // 빈 별 추가
        for (let i = 0; i < emptyStars; i++) {
            stars.push(
                <img
                    key={`empty-${i}`}
                    src="/assets/images/star_empty.png"
                    alt="Empty Star"
                    className="star"
                />
            );
        }

        return stars;
    };

    return (
        <div className="card">
            <img src={image} alt={name} className="card-image" />
            <div className="card-content">
                <div className="title">{name}</div>
                <div className="stars-rating">
                    <div className="stars">{renderStars(rating)}</div>
                    <div className="rating">({rating})</div>
                    <div className="brand">{category}</div>
                </div>
                <div className="nutrition">
                    <div>
                        <div>칼로리</div>
                        <div className="value">{calories}kcal</div>
                    </div>
                    <div>
                        <div>단백질</div>
                        <div className="value">{protein}g</div>
                    </div>
                    <div>
                        <div>당</div>
                        <div className="value">{sugar}g</div>
                    </div>
                </div>
                <div className="details">자세히 알아보기</div>
            </div>
        </div>
    );
};

export default Card;
