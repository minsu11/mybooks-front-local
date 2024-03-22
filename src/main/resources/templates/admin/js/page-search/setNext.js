document.addEventListener("DOMContentLoaded", function(){
    var page = Math.min(totalPages - 1, ((currentPage / 10) + 1) * 10);
    var query = document.querySelector("[name='query']").value;
    var order = document.querySelector("[name='order']").value;

    let linkElement = document.getElementById("nextLink");
    linkElement.href = url + "?query=" + query + "&order=" + order + "&page=" + page;
});
