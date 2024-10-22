import React from 'react';

const SearchBar = () => {
    return (
        <div className="search-container">
            <input type="text" className="search-input" placeholder="원하는 브랜드나 음식을 검색하세요!" />
            <button className="search-button">
                <img src="/images/돋보기.jpg" alt="search" className="search-icon" />
            </button>
        </div>
    );
};

export default SearchBar;