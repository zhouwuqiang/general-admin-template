$(function () {

    initTable();

});

/**
 * 初始化表格
 */
function initTable() {
    $.tableExpand({
        tableId: "main_table",
        url: "/user/table",
        searchFormId: "main_table_search_form",
        columns: [
            {
                //     checkbox: true
                // }, {
                field: 'userCode',
                title: '用户编号',
                align: 'center',
                valign: 'middle',
                width: '25%'
            }, {
                field: 'userName',
                title: '用户账号',
                align: 'center',
                valign: 'middle',
                width: '25%'
            }, {
                field: 'userLabel',
                title: '用户名称',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'storageServer',
                title: '操作',
                align: 'center',
                valign: 'middle',
                width: '20%',
                formatter: operateFormatter
            }
        ]
    });

}

function operateFormatter(value, row, index) {
    var result = [];
    result.push("<a href='javascript:void(0)' class='btn btn-info' onclick='editUser("+ JSON.stringify(row) +")'><i class='fa fa-edit'></i>&nbsp;&nbsp;修改</a>");
    result.push("<a href='javascript:void(0)' class='btn btn-info' onclick='detailUser("+ JSON.stringify(row) +")'><i class='fa fa-edit'></i>&nbsp;&nbsp;详情</a>");
    result.push("<a href='javascript:void(0)' class='btn btn-danger' onclick='deleteUser("+ JSON.stringify(row) +")'><i class='fa fa-trash-o'></i>&nbsp;&nbsp;删除</a>");
    return $.formatterOperateButton(result);
}

/**
 * 添加
 */
function addUser() {
    $('#main_mode_md').modal('show');
}

/**
 * 编辑
 */
function detailUser(user) {
    $('#main_mode_lg').modal('show');
}
/**
 * 编辑
 */
function editUser(user) {
    $('#main_mode_lg').modal('show');
}

/**
 * 删除
 */
function deleteUser(user) {

}
