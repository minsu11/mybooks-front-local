// document.addEventListener("DOMContentLoaded", function(){
//     page = Math.min((currentPage / 10 + 1) * 10 - 1, totalPages - 1)
//
//     return page;
// });
document.addEventListener("DOMContentLoaded", function(){
    var page = currentPage;

    console.log(((page / 10) >> 0));

    var pageNumContainer = document.getElementById("pageNumContainer");
    for (let i = ((page / 10) >> 0) * 10; i <= Math.min((((page / 10) >> 0) + 1) * 10 - 1, totalPages - 1); i++) {
        var pageNumElement = document.createElement("li");
        pageNumElement.className = "page-item";
        if (i === currentPage) {
            pageNumElement.className += " active";
        }
        var pageNumLink = document.createElement("a");
        pageNumLink.className = "page-link";
        pageNumLink.textContent = i + 1;
        pageNumLink.href = url + "?page=" + i;
        pageNumElement.appendChild(pageNumLink);
        pageNumContainer.appendChild(pageNumElement);
    }
});