// // src/components/main/SearchBar/SearchBar.js
//
// export const handleSearch = (query, setIconClass) => {
//     if (query.trim() === "") {
//         // 빈 검색어 처리 (필요 시 추가)
//         return;
//     }
//
//     // 아이콘을 로딩 상태로 변경
//     setIconClass("fa fa-circle-o-notch fa-spin");
//
//     // 1.5초 후 아이콘을 다시 검색 아이콘으로 변경
//     setTimeout(() => {
//         setIconClass("fa fa-search");
//     }, 1500);
//
//     // 여기에 검색 로직을 추가하세요 (예: API 요청)
//     console.log("검색어:", query);
// };
