const forms = document.querySelectorAll(".form");


forms.forEach((form) => {
    form.addEventListener("submit", (e) => {
        const pointRuleName = form.querySelector(".point-rule-name").value;

        console.log(pointRuleName)
        if (pointRuleName === "") {
            pointRuleName.focus();
            e.preventDefault();
            return false
        } else if (pointRuleName.size() < 5) {
            pointRuleName.focus();
            e.preventDefault();
            return false
        }

        const message = document.getElementById("message").dataset.info;
        alert(message)
        return true
    })
})
