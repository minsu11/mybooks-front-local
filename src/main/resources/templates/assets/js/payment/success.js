// 쿼리 파라미터 값이 결제 요청할 때 보낸 데이터와 동일한지 반드시 확인하세요.
// 클라이언트에서 결제 금액을 조작하는 행위를 방지할 수 있습니다.
var status;
document.addEventListener('DOMContentLoaded', async function () {
    const urlParams = new URLSearchParams(window.location.search);
    console.log(new Date())
    // 서버로 결제 승인에 필요한 결제 정보를 보내세요.
    const pay = await confirm(urlParams)
    const orderIdElement = document.getElementById("orderId");
    const amountElement = document.getElementById("amount");


    console.log(pay)
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
        removeCart();
    }
    console.log(payInfo)
    console.log(test);
    orderIdElement.textContent = "주문번호: " + payInfo.paymentKey;
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
    console.log(json)
    if (!response.ok) {
        console.log(json);
        window.location.href = `/pay/fail?message=${json.message}&code=${json.code}`;
    }
    return json;
}


async function payTest(payInfo) {
    console.log("함수 안: " + payInfo)

    const response = await fetch("/pay/info/success", {
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
    const response = fetch("/pay/info/cart", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    });
}