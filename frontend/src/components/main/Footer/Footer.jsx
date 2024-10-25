// Footer.jsx 또는 Footer.js
import React from 'react';
import './Footer.css'; // CSS 파일 임포트
import { useNavigate } from 'react-router-dom';

function Footer() {
    const navigate = useNavigate();

    const handleInquiryClick = () => {
        // 문의하기 페이지로 이동
        navigate('/inquiry');
    };

    return (
        <footer className="footer">
            <div className="footer-content">
                <span className="footer-brand">SALPPAEYO</span>
                <span className="footer-text">무엇을 도와드릴까요?</span>
                <button className="footer-inquiry" onClick={handleInquiryClick}>문의하기</button>
            </div>
        </footer>
    );
}

export default Footer;
