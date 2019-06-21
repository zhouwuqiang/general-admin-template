$(function () {

    initTable();

});

/**
 * 初始化表格
 */
function initTable() {
    $.tableExpand({
        tableId: "main_table",
        url: "/online/table",
        searchFormId: "main_table_search_form",
        uniqueId: "",
        pagination: true,
        columns: [
            {
                field: 'loginSession',
                title: 'session',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'userCode',
                title: '用户编号',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'username',
                title: '用户账号',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'userLabel',
                title: '用户名称',
                align: 'center',
                valign: 'middle'
            // }, {
            //     field: 'loginDate',
            //     title: '登录时间',
            //     align: 'center',
            //     valign: 'middle'
            // }, {
            //     field: 'loginIp',
            //     title: '登录IP',
            //     align: 'center',
            //     valign: 'middle'
            }, {
                field: 'storageServer',
                title: '操作',
                align: 'center',
                valign: 'middle',
                formatter: operateFormatter
            }
        ]
    });

}

function operateFormatter(value, row, index) {
    let result = [];
    result.push("<a href='javascript:void(0)' class='' onclick='kickOut(" + JSON.stringify(row) + ")'>踢出</a>");
    return $.formatterOperateButton(result);
}

function kickOut(row) {
    let param = {};
    param.sessionId = row.loginSession;

    $.ajax({
        url: "/online/kick",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function (responseDto) {
            $.ajaxMassage(responseDto);
            if (responseDto.success) {
                initTable();
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });
}
