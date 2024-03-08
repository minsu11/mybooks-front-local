const form = document.querySelectorAll(".form")

form.forEach((form) => {
    form.addEventListener("submit", (e) => {
        const name = form.querySelector(".name").value;
        const cost = form.querySelector(".cost").value;
        console.log(name)
        if (name === "" || name.length < 2 || name.length > 20) {
            alert("이름을 잘못 입력하셨습니다")
            e.preventDefault()
            return false;
        }
        if (cost === "" || cost < 0 || cost > 100000) {
            alert("비용을 잘못 입력하셨습니다.");
            e.preventDefault()
            return false;
        }
    })
})
