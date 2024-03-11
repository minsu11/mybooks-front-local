document.addEventListener("DOMContentLoaded", function() {
    var previous = Math.max(0, (((currentPage / 10) - 1) * 10))

    // href 속성을 설정
    let linkElement = document.getElementById("prevLink");
    linkElement.href = url + "?page=" + previous;
});