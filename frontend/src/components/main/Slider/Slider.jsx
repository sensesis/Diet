import React, { useState, useEffect, useRef } from 'react';
import './Slider.css';
import slides from './SliderData';

import leftArrow from '../../../assets/images/slider/left_arrow.png'; // 경로 수정
import rightArrow from '../../../assets/images/slider/right_arrow.png'; // 경로 수정

function Slider() {
    const [currentIndex, setCurrentIndex] = useState(0);
    const [isAnimating, setIsAnimating] = useState(false);
    const autoSlideTimeout = useRef(null);

    const slideLength = slides.length;

    const nextSlide = () => {
        if (isAnimating) return;
        setIsAnimating(true);
        setCurrentIndex((prevIndex) => (prevIndex + 1) % slideLength);
    };

    const prevSlide = () => {
        if (isAnimating) return;
        setIsAnimating(true);
        setCurrentIndex((prevIndex) =>
            prevIndex === 0 ? slideLength - 1 : prevIndex - 1
        );
    };

    // 자동 슬라이드 타이머 재설정 함수
    const resetAutoSlide = () => {
        if (autoSlideTimeout.current) {
            clearTimeout(autoSlideTimeout.current);
        }
        autoSlideTimeout.current = setTimeout(() => {
            nextSlide();
        }, 5000);
    };

    // currentIndex가 변경될 때마다 실행
    useEffect(() => {
        setIsAnimating(true);
        resetAutoSlide();

        const timer = setTimeout(() => {
            setIsAnimating(false);
        }, 500); // 애니메이션 시간과 동일하게 설정

        return () => {
            clearTimeout(timer);
        };
    }, [currentIndex]);

    // 컴포넌트가 언마운트될 때 타이머 정리
    useEffect(() => {
        resetAutoSlide();

        return () => {
            if (autoSlideTimeout.current) {
                clearTimeout(autoSlideTimeout.current);
            }
        };
    }, []);

    return (
        <section id="sliderType" className="slider__wrap nexon">
            <h2 className="blind">슬라이드 유형</h2>
            <div className="slider__inner">
                <div className="slider">
                    <div className="slider__img-wrapper">
                        {slides.map((slide, index) => {
                            let className = 'slider__img';
                            if (index === currentIndex) {
                                className += ' active';
                            } else if (
                                index === (currentIndex - 1 + slideLength) % slideLength
                            ) {
                                className += ' prevSlide';
                            } else if (
                                index === (currentIndex + 1) % slideLength
                            ) {
                                className += ' nextSlide';
                            }

                            return (
                                <div
                                    key={slide.id}
                                    className={className}
                                    style={{
                                        backgroundImage: `url(${slide.image})`,
                                    }}
                                >
                                    <div className="desc">
                                        <h3>{slide.title}</h3>
                                        <p>{slide.description}</p>
                                    </div>
                                </div>
                            );
                        })}

                        {/* 화살표 버튼 */}
                        <div className="slider__arrow">
                            <button onClick={() => { prevSlide(); resetAutoSlide(); }} className="slider__arrow-button left">
                                <img src={leftArrow} alt="이전 이미지" />
                            </button>
                            <button onClick={() => { nextSlide(); resetAutoSlide(); }} className="slider__arrow-button right">
                                <img src={rightArrow} alt="다음 이미지" />
                            </button>
                        </div>

                        {/* 도트 버튼 */}
                        <div className="slider__dot">
                            {slides.map((slide, index) => (
                                <button
                                    key={slide.id}
                                    className={`dot ${
                                        currentIndex === index ? 'active' : ''
                                    }`}
                                    onClick={() => {
                                        if (isAnimating || index === currentIndex) return;
                                        setCurrentIndex(index);
                                        resetAutoSlide(); // 타이머 재설정
                                    }}
                                />
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default Slider;
