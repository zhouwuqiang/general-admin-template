$(function () {

    initTable();

});

/**
 * 初始化表格
 */
function initTable() {
    $.tableExpand({
        tableId: "main_table",
        url: "/role/table",
        searchFormId: "main_table_search_form",
        columns: [
            {
                field: 'roleCode',
                title: '角色编号',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'roleName',
                title: '角色名称',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'roleMemo',
                title: '角色备注',
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
    result.push("<a href='javascript:void(0)' class='' onclick='editRole(" + JSON.stringify(row) + ")'>修改</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='roleAuthorization(" + JSON.stringify(row) + ")'>授权</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='detailRole(" + JSON.stringify(row) + ")'>详情</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='deleteRole(" + JSON.stringify(row) + ")'>删除</a>");
    return $.formatterOperateButton(result);
}

/**
 * 添加
 */
function addRole() {
    $.initModel("main_mode", "添加角色", "main_form", "add-show");
    $('#main_mode').modal('show');
}

/**
 * 编辑
 */
function editRole(row) {
    $.initModel("main_mode", "编辑角色", "main_form", "edit-show");
    $.formReview("main_form", row);
    $('#main_mode').modal('show');
}

/**
 * 显示
 */
function detailRole(row) {
    $.initModel("main_mode", "角色信息", "main_form", "detail-show");
    $.formReview("main_form", row);
    $.formReadOnly("main_form");
    $('#main_mode').modal('show');
}

/**
 * 保存
 */
function saveUser() {
    let param = $.formSerializeObject("main_form");
    $.ajax({
        url: "/role/save",
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
function deleteRole(user) {
    user.deleteFlag = "01";
    $.ajax({
        url: "/role/delete",
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


/**************************************************授权**************************************/

/**
 * 角色授权菜单
 */
function roleAuthorization(row) {

    initAuthorizationTree("menu_tree",{"roleCode": row.roleCode});

    $("#role_relation_code").val(row.roleCode);

    $('#role_relation_mode').modal('show');
}

/**
 * 保存授权
 */
function saveAuthorization() {
    let selected = $('#menu_tree').treeview('getChecked');
    let selectCodeList = [];
    for (let i in selected) {
        selectCodeList.push(selected[i].code);
    }
    console.log(JSON.stringify(selectCodeList));


    let params = {};
    params.roleCode = $("#role_relation_code").val();
    params.menuCodeList = selectCodeList;

    $.ajax({
        url: "/role/relation/save",
        type: 'post',
        dataType: 'json',
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(params),
        success: function (responseDto) {
            if (responseDto.success) {
                $('#role_relation_mode').modal('hide');
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });


}
