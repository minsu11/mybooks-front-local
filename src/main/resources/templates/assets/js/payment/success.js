document.addEventListener('DOMContentLoaded', async function () {
    const urlParams = new URLSearchParams(window.location.search);
    const pay = await confirm(urlParams)
    const successTitleElement = document.getElementById("success-title");
    const orderIdElement = document.getElementById("orderId");
    const amountElement = document.getElementById("amount");
    const orderNameElement = document.getElementById("order-name");

    const payInfo = {
        "paymentKey": pay.paymentKey,
        "orderNumber": pay.orderId,
        "status": pay.status,
        "totalAmount": pay.totalAmount,
        "method": pay.method,
        "requestedAt": pay.requestedAt
    }
    const test = await payTest(payInfo);
    if (pay.status === "DONE") {
        console.log("삭제 호출 ")
        const cart = removeCart();
    }
    successTitleElement.textContent = "결제 성공"
    orderIdElement.textContent = "주문 번호: " + payInfo.paymentKey;
    amountElement.textContent = "결제 금액: " + payInfo.totalAmount;

})

async function confirm(urlParams) {
    const requestData = {
        paymentKey: urlParams.get("paymentKey"),
        orderId: urlParams.get("orderId"),
        amount: urlParams.get("amount"),
    };

    const response = await fetch("/pay/confirm", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(requestData),
    });

    const json = await response.json();
    console.log("결과값: " + json.status)
    console.log("결과값: " + json)
    if (!response.ok) {
        console.log(json);
        window.location.href = `/pay/fail?message=${json.message}&code=${json.code}`;
    }
    return json;
}


async function payTest(payInfo) {
    console.log("pay test");
    const response = await fetch("/cart/info/success", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(payInfo),
    });

    const json = await response.json();
    console.log(json)
    if (!response.ok) {
        console.log(json);
    }
    return json;
}


function removeCart() {
    console.log("장바구니 삭제 전")
    const response = fetch("/cart/info", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });
    console.log("장바구니 ")
    return response
}
