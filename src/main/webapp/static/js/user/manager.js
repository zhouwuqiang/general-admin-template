$(function () {

    initTable();

    initTree();
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
    result.push("<a href='javascript:void(0)' class='' onclick='editUser(" + JSON.stringify(row) + ")'>修改</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='detailUser(" + JSON.stringify(row) + ")'>详情</a>");
    if ("00" === row.isLock) {
        result.push("<a href='javascript:void(0)' class='' onclick='disableUser(" + JSON.stringify(row) + ",\"01\")'>锁定</a>");
    } else {
        result.push("<a href='javascript:void(0)' class='' onclick='disableUser(" + JSON.stringify(row) + ",\"00\")'>解锁</a>");
    }
    result.push("<a href='javascript:void(0)' class='' onclick='deleteUser(" + JSON.stringify(row) + ")'>删除</a>");
    return $.formatterOperateButton(result);
}


function userSearchFormRest() {
    $.formRest('main_table_search_form');
    let $_tree = $('#organization_tree');
    let selectNode = $_tree.treeview('getSelected');
    $_tree.treeview('unselectNode', [selectNode, {silent: true}]);
    initTable();
}

/**
 * 编辑
 */
function editUser(user) {
    openTab(user.userCode, "/view/user/add", user.userLabel, {"userCode": user.userCode});
}

/**
 * 显示
 */
function detailUser(user) {
    openTab(user.userCode, "/view/user/add", user.userLabel, {"userCode": user.userCode, "readonly": "readonly"});

}

/**
 * 禁用
 */
function disableUser(user, flag) {
    let params = {};
    params.userCode = user.userCode;
    params.isLock = flag;
    $.ajax({
        url: "/user/Lock",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(params),
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
function deleteUser(user, flag) {
    let params = {};
    params.userCode = user.userCode;
    params.isLock = flag;
    $.ajax({
        url: "/user/Lock",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(params),
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


/****************************************** 初始化组织树 ***************************************/

function initTree() {
    $('#organization_tree').treeview({
        data: getData({}),
        onNodeSelected: function (event, node) {
            $("#search_organization_code").val(node.organizationCode);
            initTable()
        },
        onNodeUnselected: function (event, node) {
            $("#search_organization_code").val("");
            initTable()
        }
    });
}

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
