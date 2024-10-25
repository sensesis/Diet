import React from 'react';
import './Header.css';
import loginIcon from '../../../assets/images/slider/mypage.png';
import { useNavigate, Link } from 'react-router-dom';

function Header() {
    const navigate = useNavigate();

    const handleLogoClick = () => {
        navigate('/');
    };

    const handleIconClick = () => {
        navigate('/login');
    };

    return (
        <header id="headerType" className="header__wrap noto">
            <div className="header__inner">
                <div className="header__logo" onClick={handleLogoClick}>
                    SALPPAEYO
                </div>
                <nav className="header__menu">
                    <ul>
                        <li><Link to="/search">브랜드/음식검색</Link></li>
                        <li><Link to="/compare">메뉴비교</Link></li>
                        <li><Link to="/ratings">외식등급표</Link></li>
                        <li><Link to="/board">게시판</Link></li>
                    </ul>
                </nav>
                <div className="header__member">
                    <a onClick={() => navigate('/login')}>Login</a>
                    <img
                        src={loginIcon}
                        alt="Login Icon"
                        className="login-icon"
                        onClick={handleIconClick}
                    />
                </div>
            </div>
        </header>
    );
}

export default Header;
