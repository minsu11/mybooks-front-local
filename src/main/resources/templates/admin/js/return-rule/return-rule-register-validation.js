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
        } else if (deliveryFee > 10000) {
            alert("10000원 이하로 입력하세요");

            e.preventDefault()
        }

        if (term === "") {
            alert("반품 기간이 공백입니다.");
            // returnName.focus();
            e.preventDefault();
        } else if (term < 1) {
            alert("양수로 입력하세요");
            e.preventDefault();
        } else if (term < 366) {
            alert("365일 이하로 입력하세요");
            e.preventDefault()
        }
    })
})
