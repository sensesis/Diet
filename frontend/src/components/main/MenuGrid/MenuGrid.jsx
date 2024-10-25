import React, { useState, useEffect } from 'react';
import './MenuGrid.css';
import FoodData from './FoodData';
import Card from '../Card/Card';
import allowIcon from '../../../assets/images/Card/allow.png';

const getInitialVisibleCount = (width) => {
    if (width <= 480) return 4;
    if (width <= 768) return 6;
    return 6;
};

const MenuGrid = () => {
    // 기본 visibleCount 설정
    const [visibleCount, setVisibleCount] = useState(() => getInitialVisibleCount(window.innerWidth));
    const [windowWidth, setWindowWidth] = useState(window.innerWidth);

    // 반응형 화면 크기 감지
    useEffect(() => {
        const handleResize = () => {
            setWindowWidth(window.innerWidth);
        };

        window.addEventListener('resize', handleResize);
        handleResize(); // 초기 실행 시 현재 화면 크기에 맞게 설정

        return () => {
            window.removeEventListener('resize', handleResize);
        };
    }, []);

    useEffect(() => {
        setVisibleCount(getInitialVisibleCount(windowWidth));
    }, [windowWidth]);

    const handleShowMore = () => {
        if (windowWidth <= 480) {
            setVisibleCount((prevCount) => prevCount + 4); // 작은 화면에서는 4개씩 추가
        } else {
            setVisibleCount((prevCount) => prevCount + 6); // 큰 화면에서는 6개씩 추가
        }
    };

    return (
        <div className="menu-grid-wrapper">
            <div className="menu-grid">
                {Array.isArray(FoodData) && FoodData.slice(0, visibleCount).map((food) => (
                    <Card key={food.id} {...food} />
                ))}
            </div>
            {visibleCount < FoodData.length && (
                <div className="show-more-container">
                    <button className="show-more-button" onClick={handleShowMore}>
                        메뉴 전체보기 <img src={allowIcon} alt="Arrow" className="allow-icon" />
                    </button>
                </div>
            )}
        </div>
    );
};

export default MenuGrid;