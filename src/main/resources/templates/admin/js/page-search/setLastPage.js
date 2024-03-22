document.addEventListener("DOMContentLoaded", function(){
    var page = currentPage;
    var query = document.querySelector("[name='query']").value;
    var order = document.querySelector("[name='order']").value;

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
        pageNumLink.href = url + "?query=" + query + "&order=" + order + "&page=" + i;
        pageNumElement.appendChild(pageNumLink);
        pageNumContainer.appendChild(pageNumElement);
    }
});