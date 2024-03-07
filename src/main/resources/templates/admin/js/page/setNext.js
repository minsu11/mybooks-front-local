document.addEventListener("DOMContentLoaded", function(){
    page = Math.min(totalPages - 1, ((currentPage / 10) + 1) * 10)

    // href 속성을 설정
    let linkElement = document.getElementById("nextLink");
    linkElement.href = url + "?page=" + page;
    console.log(url + "?page=" + page)
});
