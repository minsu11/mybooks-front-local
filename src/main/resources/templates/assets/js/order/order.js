document.addEventListener('DOMContentLoaded', function () {
    const radioInputs = document.querySelectorAll('input[type="radio"][name="wrapRadio"]');
    const wrapItem = document.querySelectorAll('input[type=radio][name="wrap-item"]')
    radioInputs.forEach(function (input) {
        input.addEventListener('click', function () {
            const chkValue = document.getElementById('wrap-used').checked;
            const wrapViewDiv = document.getElementById('wrap-view');
            if (chkValue) {
                wrapViewDiv.style.display = 'block';
            } else {
                wrapViewDiv.style.display = 'none';
            }
        });
    });
    wrapItem.forEach(function (input) {
        input.addEventListener('click', function () {
            // 클릭 시 포장지의 가격을 total에 합쳐지는 역할
        })
    })

});
