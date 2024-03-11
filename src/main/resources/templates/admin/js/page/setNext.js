document.addEventListener("DOMContentLoaded", function(){
    page = Math.min(totalPages - 1, ((currentPage / 10) + 1) * 10)

    let linkElement = document.getElementById("nextLink");
    linkElement.href = url + "?page=" + page;
});
