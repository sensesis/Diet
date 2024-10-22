import React from 'react';
import './Slider.css';
import loginIcon from '../../../assets/images/mypage.png';

class Slider extends React.Component {
    render() {
        return (
            <section id="sliderType" className="slider__wrap nexon">
                <h2 className="blind">슬라이드 유형</h2>
                <div className="slider__inner">
                    <div className="slider">
                        <div className="slider__img">
                            <div className="desc">
                                <h3>최악의 상황에서 최선을 다해라</h3>
                                <p>
                                    외식은 다이어트에 도전이 될 수 있지만, 포기하지 마세요. 저희는 신선한 재료와 건강한 선택지를 추천하여 외식에서도 목표를 지킬 수<br/>
                                    있도록 도와드립니다. 현명한 선택으로 건강한 삶을 이어가세요
                                </p>
                            </div>
                        </div>
                        <div className="slider__arrow">
                            <a href="/frontend/public" className="left"><span className="ir">이전 이미지</span></a>
                            <a href="/frontend/public" className="right"><span className="ir">다음 이미지</span></a>
                        </div>
                        <div className="slider__dot">
                            <a href="/frontend/public" className="dot active"><span className="ir">1</span></a>
                            <a href="/frontend/public" className="dot"><span className="ir">2</span></a>
                            <a href="/frontend/public" className="dot"><span className="ir">3</span></a>
                            <a href="/frontend/public" className="play"><span className="ir">플레이</span></a>
                            <a href="/frontend/public" className="stop"><span className="ir">정지</span></a>
                        </div>
                    </div>
                </div>
            </section>
        );
    }
}

export default Slider;
