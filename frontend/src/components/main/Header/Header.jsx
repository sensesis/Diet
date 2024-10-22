import React from 'react';
import './Header.css';
import loginIcon from '../../../assets/images/mypage.png';

function Header() {
    return (
        // <header className="header">
        //     <div className="header-nav">
        //         <div className="logo">SALPAPEYO</div>
        //         <nav>
        //             <ul className="nav-menu">
        //                 <li className="nav-item">브랜드/음식검색</li>
        //                 <li className="nav-item">메뉴 비교</li>
        //                 <li className="nav-item">외식등급표</li>
        //                 <li className="nav-item">게시판</li>
        //             </ul>
        //         </nav>
        //         <div className="login-section">
        //             <span>Login</span>
        //             <img src={loginIcon} alt="Login Icon" className="login-icon"/>
        //         </div>
        //     </div>
        // </header>
        <header id="headerType" class="header__wrap noto">
            <div className="header__inner">
                <div className="header__logo">SALPPAEYO</div>
                <nev class="header__menu">
                    <ul>
                        <li><a href="#" class="hover">브랜드/음식검색</a></li>
                        <li><a href="#">메뉴비교</a></li>
                        <li><a href="#">외식등급표</a></li>
                        <li><a href="#">게시판</a></li>
                    </ul>
                </nev>
                <div className="header__member">
                    <a>Login</a>
                    <img src={loginIcon} alt="Login Icon" className="login-icon"/>
                </div>
            </div>
        </header>
);
}

export default Header;
