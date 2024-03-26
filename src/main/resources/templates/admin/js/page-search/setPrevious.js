document.addEventListener("DOMContentLoaded", function() {
    var previous = Math.max(0, Math.floor((currentPage - 1) / 10) * 10);
    var query = document.querySelector("[name='query']").value;

    let linkElement = document.getElementById("prevLink");
    linkElement.href = url + "?query=" + query + "&order=" + order + "&page=" + previous;
});
