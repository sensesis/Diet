import React from 'react';
import Header from '../components/main/Header/Header';
import Slider from '../components/main/Slider/Slider';
import SearchBar from '../components/main/SearchBar/SearchBar';
import Footer from '../components/main/Footer/Footer';
import MenuGrid from '../components/main/MenuGrid/MenuGrid';

import './MainPage.css';

const MainPage = () => {
    return (
        <div className="main-container">
            <Header />
            <div className="content">
                <Slider />
                <SearchBar />
                <MenuGrid />
            </div>
            <Footer />
        </div>
    );
};

export default MainPage;
