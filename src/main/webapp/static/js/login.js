$(function () {

    var error = $.getUrlParam("error");

    if($.isNotNull(error)){


        if ("2"===error){
            swal("登录超时,请重新登录!");
        }
    }

});

$(document).keydown(function (event) {
	if (event.keyCode === 13) {
		userLogin();
	}
});

/**
 * 用户登录
 */
function userLogin() {


    var $form = $('#user_login_form');

    if ($.isNull($form.data("formValidation"))) {
        userLoginFormValidate();
        $form.data("formValidation").validate();
    }

    if (!$form.data("formValidation").isValid()) {
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
            }
        },
        error: function (res, status) {
            console.log("网络异常!")
        }
    });

}
