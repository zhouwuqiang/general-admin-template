$(function () {
    initPage();
});

/**
 * 初始化页面
 * @param userCode
 */
function initPage() {
    $.ajax({
        url: "/user/center",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (responseDto) {
            if (responseDto.success) {
                $.formReview("main_form", responseDto.data.basicInfo);
                $.formReview("power_form", responseDto.data.powerInfo);
                $.formReadOnly("main_form");
                $.formReadOnly("power_form");
            } else {
                $.ajaxMassage(responseDto);
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });
}

