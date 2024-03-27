var wrapCheck = "";
var couponCheck = "";
var addressCheck = "";

document.addEventListener('DOMContentLoaded', function (event
) {
    const urlParams = new URLSearchParams(window.location.search);
    const amount = (function () {
        const data = document.getElementById('quantity').value
        return {
            getAmount: function () {
                return data;
            }
        }
    })


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
        if (couponCost.value < 0 || wrapCost.value < 0) {
            alert("음수를 입력했습니다.")
            event.preventDefault()
            return false;
        }
    })


    date.addEventListener('change', function () {
        document.getElementById('delivery-date-label').textContent = date.value;
    })
});


function updateTotalCost(total, num) {
    return total - num;
}


