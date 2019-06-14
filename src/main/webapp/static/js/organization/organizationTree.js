/**
 * 获取组织结构数据
 * @param param
 * @returns {Array}
 */
function getData(param) {

    let list = [];

    $.ajax({
        url: "/organization/list/tree",
        type: 'post',
        dataType: 'json',
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function (responseDto) {
            if (responseDto.success) {
                list = responseDto.data;
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });

    return list;
}
