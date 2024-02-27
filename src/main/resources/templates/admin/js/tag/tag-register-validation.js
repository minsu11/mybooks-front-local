var form = document.forms["form"];

form.addEventListener("submit", function (event) {
    var name = form.name.value.trim();

    if (!validationForm(name)) {
        event.preventDefault();
    }
});