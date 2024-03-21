var wrapCheck = "";
var couponCheck = "";
var addressCheck = "";

document.addEventListener('DOMContentLoaded', function (event
) {
    const radioInputs = document.querySelectorAll('input[type="radio"][name="wrapRadio"]');
    const point = document.querySelector('input[id="user-point"][type="number"]')
    const wrapCost = document.querySelector('input[id="wrap-cost"]')
    // const input = document.querySelectorAll(".select-wrap")
    const date = document.getElementById('delivery-date-id');
    const submitForm = document.getElementById('order-pay-form')
    let data = 0;
    const payBtn = document.getElementById('pay-btn');
    payBtn.addEventListener('submit', function () {
        const couponCost = document.getElementById('coupon-cost')
        console.log(wrapCost.value);
        alert(wrapCost.value);
        if (couponCost.value < 0 || wrapCost.value < 0) {
            alert("음수를 입력했습니다.")
            event.preventDefault()
            return false;
        }
    })


    submitForm.addEventListener("keydown", function (event) {
        if (event.keyCode === 13) {
            event.preventDefault()
        }
    })

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


