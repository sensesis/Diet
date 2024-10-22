// src/components/main/Card.jsx
import React from 'react';

const Card = () => {
    return (
        <div className="bg-white shadow-md rounded-lg overflow-hidden">
            <img
                src="https://via.placeholder.com/400x300" // 임시 이미지 URL
                alt="메뉴 이미지"
                className="w-full h-64 object-cover"
            />
            <div className="p-4">
                <h3 className="text-xl font-semibold text-gray-900">
                    싸이버거
                </h3>
                <p className="text-gray-600">맘스터치</p>
                {/* 별점 */}
                <div className="flex items-center mt-2">
                    <span className="text-yellow-500">★ ★ ★ ★ ☆</span>
                    <span className="ml-2 text-gray-600">(4.0)</span>
                </div>
                {/* 영양 정보 */}
                <div className="flex justify-between mt-4 text-center text-gray-600">
                    <div>
                        <p className="text-sm">칼로리</p>
                        <p className="text-base">574</p>
                    </div>
                    <div>
                        <p className="text-sm">단백질</p>
                        <p className="text-base">12g</p>
                    </div>
                    <div>
                        <p className="text-sm">당</p>
                        <p className="text-base">12g</p>
                    </div>
                </div>
                {/* 자세히 알아보기 */}
                <button className="mt-4 w-full py-2 text-gray-800 border border-gray-300 rounded-md">
                    자세히 알아보기
                </button>
            </div>
        </div>
    );
};

export default Card;
