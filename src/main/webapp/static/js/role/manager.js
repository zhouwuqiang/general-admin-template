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


/**
 * 初始化权限菜单
 */
function initAuthorizationTree() {

    $('#menu_tree').treeview({
        data: getTree({}),
        showCheckbox: true,
        hierarchicalCheck: true,
        showTags: true,
        onNodeChecked: function (event, node) { //选中节点
            let selectNodes = getChildNodeIdArr(node); //获取所有子节点
            if (selectNodes) { //子节点不为空，则选中所有子节点
                $('#menu_tree').treeview('checkNode', [selectNodes, {silent: true}]);
            }
            setParentNodeCheckOnly(node);
        },
        onNodeUnchecked: function (event, node) { //取消选中节点
            let selectNodes = getChildNodeIdArr(node); //获取所有子节点
            if (selectNodes) { //子节点不为空，则取消选中所有子节点
                $('#menu_tree').treeview('uncheckNode', [selectNodes, {silent: true}]);
            }
        },
    });
}

/**
 * 角色授权菜单
 */
function roleAuthorization(row) {

    initAuthorizationTree({"roleCode": row.roleCode});

    $('#menu_mode').modal('show');
}

/**
 * 获取角色数据
 * @returns {Array}
 */
function getTree(param) {

    let menuList = [];

    $.ajax({
        url: "/menu/list/tree",
        type: 'post',
        dataType: 'json',
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function (responseDto) {
            if (responseDto.success) {
                menuList = responseDto.data;
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });

    return menuList;
}

/**
 * 保存授权
 */
function saveAuthorization() {
    let selected = $('#menu_tree').treeview('getChecked');
    let selectCodeList = [];
    for(let i in selected){
        selectCodeList.push(selected[i].code);
    }
    console.log(JSON.stringify(selectCodeList));


}

/**
 * 获取所有字节点
 * @param node
 * @returns {Array}
 */
function getChildNodeIdArr(node) {
    let ts = [];
    if (node.nodes) {
        for (let x in node.nodes) {
            ts.push(node.nodes[x].nodeId);
            if (node.nodes[x].nodes) {
                let getNodeDieDai = getChildNodeIdArr(node.nodes[x]);
                for (j in getNodeDieDai) {
                    ts.push(getNodeDieDai[j]);
                }
            }
        }
    } else {
        ts.push(node.nodeId);
    }
    return ts;
}

/**
 * 设置父节点选中 子节点都选中,父节点自动选中
 * @param node
 */
function setParentNodeCheck(node) {
    let parentNode = $("#menu_tree").treeview("getNode", node.parentId);
    if (parentNode.nodes) {
        let checkedCount = 0;
        for (let x in parentNode.nodes) {
            if (parentNode.nodes[x].state.checked) {
                checkedCount++;
            } else {
                break;
            }
        }
        if (checkedCount === parentNode.nodes.length) {
            $("#menu_tree").treeview("checkNode", parentNode.nodeId);
            setParentNodeCheck(parentNode);
        }
    }
}

/**
 * 设置父节点选中 子节点选中一个 ,父节点选中
 * @param node
 */
function setParentNodeCheckOnly(node) {
    let parentNode = $("#menu_tree").treeview("getNode", node.parentId);
    if (parentNode.nodes) {
        let checkedCount = 0;
        for (let x in parentNode.nodes) {
            if (parentNode.nodes[x].state.checked) {
                checkedCount++;
            } else {
                break;
            }
        }
        if (checkedCount > 0) {
            $("#menu_tree").treeview("checkNode", [parentNode.nodeId, {silent: true}]);
            setParentNodeCheck(parentNode);
        }
    }
}
