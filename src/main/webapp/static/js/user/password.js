/**
 * 更新密码
 */
function updateLoginPassword() {
    let params = $.formSerializeObject("main_form");

    $.deleteEmptyKey(params);

    $.ajax({
        url: "/user/update/login/password",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(params),
        success: function (responseDto) {
            $.ajaxMassage(responseDto,'top-center');
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });
}
