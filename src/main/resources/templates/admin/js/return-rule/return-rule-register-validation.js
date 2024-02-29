const forms = document.querySelectorAll(".form");


forms.forEach((form) => {
    form.addEventListener("submit", (e) => {
        const returnName = form.querySelector(".returnName").value;
        const deliveryFee = form.querySelector(".deliveryFee").value;
        const term = form.querySelector(".term").value;

        if (returnName === "") {
            alert("이름 공백입니다.");
            returnName.focus();
            e.preventDefault();
        }

        if (deliveryFee === "") {
            alert("배송 비용 공백입니다.");
            returnName.focus();
            e.preventDefault();
        } else if (deliveryFee < 0) {
            alert("양수로 입력하세요");
            e.preventDefault();
        } else if (deliveryFee.size() > 50) {
            alert("50글자 미만으로 입력하세요");
            e.preventDefault()
        }

        if (term === "") {
            alert("이름 공백입니다.");
            // returnName.focus();
            e.preventDefault();
        } else if (term.size() < 4) {
            alert("4글자 이상 입력하세요");
            e.preventDefault();
        } else if (term.size() > 50) {
            alert("50글자 미만으로 입력하세요");
            e.preventDefault()
        }
    })
})
