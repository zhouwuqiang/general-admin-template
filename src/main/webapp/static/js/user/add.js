$(function () {


    if ($.isNull($("#page_readonly").val())) {
        $(".detail-show").hide();
    } else {
        $(".add-show").hide();
    }

    let $userCode = $("#page_user_code").val();
    if ($.isNotNull($userCode)) {
        initPage($userCode);
    }


});

/**
 * 初始化页面
 * @param userCode
 */
function initPage(userCode) {
    $.ajax({
        url: "/user/detail",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({"userCode":userCode}),
        success: function (responseDto) {
            $.ajaxMassage(responseDto);
            if (responseDto.success) {
                selfCloseTab();
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });
}


/**
 * 保存
 */
function saveUser() {
    let param = {};
    let mainParam = $.formSerializeObject("main_form");
    let powerParam = $.formSerializeObject("power_form");

    $.deleteEmptyKey(mainParam);
    $.deleteEmptyKey(powerParam);

    param.basicInfo = mainParam;
    param.powerInfo = powerParam;

    console.log(JSON.stringify(param));

    $.ajax({
        url: "/user/save",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function (responseDto) {
            $.ajaxMassage(responseDto);
            if (responseDto.success) {
                selfCloseTab();
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });
}

/****************************************** 初始化组织树 ***************************************/

function selectOrganization() {
    initTree();
    $("#organization_mode").modal("show");
}

/**
 * 初始化组织结构
 */
function initTree() {
    $('#organization_tree').treeview({
        data: getData({}),
        onNodeSelected: function (event, node) {
            $("#organization_code").val(node.organizationCode);
            $("#organization_name").val(getOrganizationName(node));
        },
        onNodeUnselected: function (event, node) {
            $("#organization_code").val("");
            $("#organization_name").val("");
        }
    });
}

/**
 * 获取组织名称
 */
function getOrganizationName(node) {
    let name = node.organizationName;
    let parentNode = $("#organization_tree").treeview("getNode", node.parentId);
    if ($.isNotNull(parentNode) && $.isNotNull(parentNode.parentId)) {
        name = getOrganizationName(parentNode) + "\\" + name;
    }
    return name;
}

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

/****************************************** 初始化角色查询 ***************************************/

function selectRole() {
    initTable();
    $("#role_mode").modal("show");
}


/**
 * 初始化表格
 */
function initTable() {
    $.tableExpand({
        tableId: "role_table",
        url: "/role/table",
        clickToSelect: true,
        maintainSelected: true,
        columns: [
            {
                checkbox: true
            }, {
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
            }
        ]
    });

}

function getSelectRole() {
    let rows = $("#role_table").bootstrapTable('getSelections');

    if (rows.length === 1) {

        console.log(JSON.stringify(rows[0]));
        $("#role_code").val(rows[0].roleCode);
        $("#user_role_name").val(rows[0].roleName);


        $("#role_mode").modal("hide");

    } else {
        $.toast({
            text: "请选择一行数据",
            allowToastClose: true,
            hideAfter: 1000,
            position: 'top-center',
            bgColor: '#5bc0de'
        });
    }
}
