// src/components/main/SearchBar/SearchBar.jsx

import React, { useState } from 'react';
import './SearchBar.css';
import { handleSearch } from './SearchBar'; // 동일한 디렉토리에 있는 SearchBar.js를 import
// import loginIcon from '../../../assets/images/mypage.png'; // 사용하지 않으면 제거

const SearchBar = () => {
    // const [query, setQuery] = useState('');
    // const [iconClass, setIconClass] = useState('fa fa-search');
    //
    // const onSearch = (event) => {
    //     event.preventDefault();
    //     handleSearch(query, setIconClass);
    // };
    //
    // const onKeyDown = (event) => {
    //     if (event.key === 'Enter') {
    //         handleSearch(query, setIconClass);
    //     }
    // };

    return (
        // <>
        //     <div id="search">
        //         <input
        //             id="input"
        //             placeholder="Search..."
        //             value={query}
        //             onChange={(e) => setQuery(e.target.value)}
        //             onKeyDown={onKeyDown}
        //             aria-label="Search"
        //         />
        //         <button id="button" onClick={onSearch} aria-label="Search Button">
        //             <i className={iconClass}></i>
        //         </button>
        //     </div>
        //     <div className="note">Type & hit enter</div>
        // </>
        <div className=" bg-gray-200">

            <div className="container h-screen flex justify-center items-center px-4 sm:px-6 lg:px-8">


                <div className="relative">

                    <input type="text" className="h-14 w-96 pr-8 pl-5 rounded z-0 focus:shadow focus:outline-none"
                           placeholder="Search anything..."/>

                    <div className="absolute top-4 right-3">
                        <i className="fa fa-search text-gray-400 z-20 hover:text-gray-500"></i>
                    </div>

                </div>


            </div>
        </div>
    );
};

export default SearchBar;
