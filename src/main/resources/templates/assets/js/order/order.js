var wrapCheck = "";
var couponCheck = "";
var addressCheck = "";

document.addEventListener('DOMContentLoaded', function (event
) {
    const radioInputs = document.querySelectorAll('input[type="radio"][name="wrapRadio"]');
    const point = document.querySelector('input[id="user-point"][type="number"]')
    const wrapCost = document.querySelector('input[id="wrap-cost"]')
    // const input = document.querySelectorAll(".select-wrap")
    const payBtn = document.querySelector('.pay-btn')
    const date = document.getElementById('delivery-date-id');
    let data = 0;

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
    point.addEventListener('keyup', function (event) {
        event.preventDefault()
        if (event.keyCode === 13) {
            alert("123")
            const total = document.querySelector('span[id="totalCost"]')
            alert("확인 ")
            if (point.value === "") {
                point.value = "0"
            }
            const inputValue = parseInt(point.value);
            const maxValue = parseInt(point.getAttribute('max'));
            if (inputValue > maxValue) {
                alert('최대값을 초과하였습니다.');
            } else if (inputValue < 0) {
                alert("0원 미만을 입력하셨습니다.")
            }

            total.textContent = updateTotalCost(parseInt(total.textContent) + data, inputValue);
            data = inputValue;
        }
    })
    payBtn.addEventListener('submit', function () {
        const form = document.getElementById('order-pay-form');
        const xhr = new XMLHttpRequest();
        xhr.open('GET', form.action)
        xhr.onload = function () {
            if (xhr.status === 200) {

            }
        }
    })


    date.addEventListener('change', function () {
        document.getElementById('delivery-date-label').textContent = date.value;
    })
});

function address() {
    if (!addressCheck.closed && addressCheck) {
        addressCheck.focus();
        return
    }
    const width = 800
    const height = 600
    const left = Math.ceil((window.screen.width - width) / 2);
    const top = Math.ceil((window.screen.height - height) / 2)
    addressCheck = window.open("/address", "_blank", "toolbar=yes,scrollbars=yes,top=" + top + ",left=" + left + ",width=" + width + ",height=" + height)
}

function clickCoupon(button) {
    if (!couponCheck.closed && couponCheck) {
        couponCheck.focus();
        return
    }
    const id = button.parentElement.parentElement.id
    alert("/checkout/" + button.value + "/coupon/" + id)
    const width = 800
    const height = 600
    const left = Math.ceil((window.screen.width - width) / 2);
    const top = Math.ceil((window.screen.height - height) / 2)
    couponCheck = window.open("/checkout/" + button.value + "/coupon/" + id, "_blank", "toolbar=yes,scrollbars=yes,top=" + top + ",left=" + left + ",width=" + width + ",height=" + height)
}

function wrap(button) {
    if (!wrapCheck.closed && wrapCheck) {
        wrapCheck.focus();
        return
    }
    const id = button.parentElement.parentElement.id
    const width = 800
    const height = 600
    const left = Math.ceil((window.screen.width - width) / 2);
    const top = Math.ceil((window.screen.height - height) / 2)
    wrapCheck = window.open("/checkout/wraps/" + id, "_blank", "toolbar=yes,scrollbars=yes,top=" + top + ",left=" + left + ",width=" + width + ",height=" + height)
}

function updateTotalCost(total, num) {
    return total - num;
}








