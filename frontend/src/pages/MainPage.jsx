import React from 'react';
import Header from '../components/main/Header/Header';
import Slider from '../components/main/Slider/Slider';
import SearchBar from '../components/main/SearchBar/SearchBar.jsx';

const MainPage = () => {
    return (
        <div>
            <Header />
            <Slider />
            <SearchBar />
            {/*<Filter />*/}
            {/*<MenuGrid />*/}
        </div>
    );
};

export default MainPage;
