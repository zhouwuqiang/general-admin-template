$(function () {

    $(document).keydown(function (event) {
        if (event.keyCode === 13) {
            userLogin();
        }
    });

});


/**
 * 用户登录
 */
function userLogin() {

    $('#login_but').attr("disabled", true);

    $('#user_login_form').validationEngine("attach",{
        promptPosition: 'topRight',
        addFailureCssClassToField:'validationFailure',
        onFieldFailure: function () {
            $('#login_but').attr("disabled", false);
        }
    });

    if (!$('#user_login_form').validationEngine('validate')) {
        return 0;
    }

    var param = $.formSerializeObject("user_login_form");
    param = $.deleteEmptyKey(param);
    $.ajax({
        url: '/login',
        type: 'POST',
        data: param,
        success: function (responseDto) {
            if (responseDto.success) {
                window.location.href = '/view/index'
            } else {
                swal({
                    text: responseDto.data,
                    type: 'error',
                    confirmButtonText: '确定'
                });
                $('#login_but').attr("disabled", false);
            }
        },
        error: function (res, status) {
            console.log("网络异常!")
        }
    });

}
