const forms = document.querySelectorAll(".form");

const data = document.getElementById("")
forms.forEach((form) => {
    form.addEventListener("submit", (e) => {
        e.preventDefault(); // 폼의 기본 동작 방지

        const invoiceNumbers = document.querySelectorAll(".invoiceNumber"); // 모든 invoiceNumber 엘리먼트 가져오기

        invoiceNumbers.forEach((invoiceNumber) => {
            const value = invoiceNumber.textContent.trim(); // invoiceNumber의 텍스트 내용 가져오기
            const button = invoiceNumber.parentElement.querySelector("#button-status-modify"); // 해당 invoiceNumber의 부모 요소에서 버튼 찾기

            button.disabled = (value === "");
        });
    });
});

