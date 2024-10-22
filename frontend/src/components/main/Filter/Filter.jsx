import React from 'react';

const Filter = () => {
    return (
        <div className="filter-container">
            <button className="filter-button">추천순</button>
            <button className="filter-button">평점순</button>
            <button className="filter-button">칼로리</button>
            <button className="filter-button">단백질</button>
            <button className="filter-button">당</button>
            <select className="filter-select">
                <option>정렬하기</option>
                <option>높은 가격순</option>
                <option>낮은 가격순</option>
            </select>
        </div>
    );
};

export default Filter;
