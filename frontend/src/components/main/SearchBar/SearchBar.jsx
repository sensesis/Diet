import React, { useState } from 'react';
import './SearchBar.css';
import searchIcon from '../../../assets/images/search/search_bar.png';

function SearchBar() {
    const [inputValue, setInputValue] = useState('');

    const handleInputChange = (e) => {
        setInputValue(e.target.value);
    };

    const handleSearchClick = () => {
        if (inputValue.trim()) {
            console.log('검색 내용:', inputValue);
        }
    };

    return (
        <div className="search-bar">
            <input
                type="text"
                value={inputValue}
                onChange={handleInputChange}
                placeholder="원하는 브랜드나 음식을 검색하세요!"
            />
            <button onClick={handleSearchClick} className="search-button">
                <img src={searchIcon} alt="search icon" />
            </button>
        </div>
    );
}

export default SearchBar;
