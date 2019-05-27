$(function () {

    initTable();

});

/**
 * 初始化表格
 */
function initTable() {
    $.tableExpand({
        tableId: "main_table",
        url: "/wordbook/table",
        searchFormId: "main_table_search_form",
        columns: [
            {
                field: 'userCode',
                title: '用户编号',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'userName',
                title: '用户账号',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'userLabel',
                title: '用户名称',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'isLock',
                title: '是否锁定',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'isLock',
                title: '是否需要重置密码',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'createUser',
                title: '创建用户',
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
    let result = [];
    result.push("<a href='javascript:void(0)' class='btn btn-info' onclick='editUser(" + JSON.stringify(row) + ")'><i class='fa fa-edit fa-icon'></i>修改</a>");
    result.push("<a href='javascript:void(0)' class='btn btn-info' onclick='detailUser(" + JSON.stringify(row) + ")'><i class='fa fa-th-list fa-icon'></i>详情</a>");
    result.push("<a href='javascript:void(0)' class='btn btn-danger' onclick='deleteUser(" + JSON.stringify(row) + ")'><i class='fa fa-trash-o fa-icon'></i>删除</a>");
    return $.formatterOperateButton(result);
}

/**
 * 添加
 */
function addUser() {
    $.initModel("main_mode", "添加字典", "main_form", "add-show");
    $('#main_mode').modal('show');
}

/**
 * 编辑
 */
function editUser(user) {
    $.initModel("main_mode", "编辑字典", "main_form", "edit-show");
    $.formReview("main_form", user);
    $('#main_mode').modal('show');
}

/**
 * 显示
 */
function detailUser(user) {
    $.initModel("main_mode", "字典信息", "main_form", "detail-show");
    $.formReview("main_form", user);
    $.formReadOnly("main_form");
    $('#main_mode').modal('show');
}

/**
 * 保存
 */
function saveUser() {
    let param = $.formSerializeObject("main_form");
    $.ajax({
        url: "/wordbook/save",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function (responseDto) {
            $.ajaxMassage(responseDto);
            if (responseDto.success) {
                $('#main_mode').modal('hide');
                initTable();
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });
}

/**
 * 删除
 */
function deleteUser(user) {
    user.deleteFlag = "01";
    $.ajax({
        url: "/wordbook/delete",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(user),
        success: function (responseDto) {
            $.ajaxMassage(responseDto);
            if (responseDto.success) {
                $('#main_mode').modal('hide');
                initTable();
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });
}
