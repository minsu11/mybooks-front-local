document.addEventListener("DOMContentLoaded", function () {
    const orderNumber = document.getElementById("order-number").value;
    const button = document.getElementById("payment-button");
    const coupon = document.getElementById("all-book-coupon-button");
    const generateRandomString = orderNumber.toString()
    var couponCheck = "";
    var amount = parseInt(document.getElementById("order-total-cost").value);

// ------  결제위젯 초기화 ------

    const clientKey = document.querySelector(".toss").value;
    const customerKey = generateRandomString;
    const paymentWidget = PaymentWidget(clientKey, customerKey); // 회원 결제

    const payValue = payInfo(customerKey);
    // const paymentWidget = PaymentWidget(clientKey, PaymentWidget.ANONYMOUS); // 비회원 결제
    console.log(coupon)

// ------  결제 UI 렌더링 ------
// @docs https://docs.tosspayments.com/reference/widget-sdk#renderpaymentmethods선택자-결제-금액-옵션
    paymentMethodWidget = paymentWidget.renderPaymentMethods(
        "#payment-method",
        {value: amount},
        // 렌더링하고 싶은 결제 UI의 variantKey
        // 결제 수단 및 스타일이 다른 멀티 UI를 직접 만들고 싶다면 계약이 필요해요.
        // @docs https://docs.tosspayments.com/guides/payment-widget/admin#멀티-결제-ui
        {variantKey: "DEFAULT"}
    );
// ------  이용약관 UI 렌더링 ------
// @docs https://docs.tosspayments.com/reference/widget-sdk#renderagreement선택자-옵션
    paymentWidget.renderAgreement("#agreement", {variantKey: "AGREEMENT"});

// ------  결제 금액 업데이트 ------
// @docs https://docs.tosspayments.com/reference/widget-sdk#updateamount결제-금액
// ------ '결제하기' 버튼 누르면 결제창 띄우기 ------
// @docs https://docs.tosspayments.com/reference/widget-sdk#requestpayment결제-정보
    button.addEventListener("click", function () {
        // 결제를 요청하기 전에 orderId, amount를 서버에 저장하세요.
        // 결제 과정에서 악의적으로 결제 금액이 바뀌는 것을 확인하는 용도입니다.

        payValue.then(value => {
            alert(value.orderName)
            alert(value.email)
            alert(value.name)
            alert(value.phoneNumber)
            paymentWidget.requestPayment({
                orderId: customerKey,
                orderName: value.orderName,
                successUrl: window.location.origin + "/pay/success",
                failUrl: window.location.origin + "/pay/fail",
                customerEmail: value.email,
                customerName: value.name,
                customerMobilePhone: value.phoneNumber,
            });
        })


    });


})

async function payInfo(customerKey) {

    const response = await fetch("/pay/info/" + customerKey, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    });
    const json = await response.json()
    if (!response.ok) {

        console.log(json)
    }
    return json;
}





