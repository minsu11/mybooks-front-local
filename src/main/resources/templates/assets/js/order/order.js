$(document).ready(function () {
    $('input[type="radio"][id="wrap-using"]').on('click', function () {
        var chkValue = $('input[type=radio][id="wrap-using"]:checked').val();
        console.log("log 확인")
        if (chkValue) {
            $('#wrap_view').css('display', 'none');
        } else {
            $('#wrap_view').css('display', 'block');
        }

    });

});