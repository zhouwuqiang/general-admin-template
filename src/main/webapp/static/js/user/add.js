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
    alert(userCode);
}


/**
 * 保存
 */
function saveUser() {
    let param = $.formSerializeObject("main_form");
    $.ajax({
        url: "/user/save",
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
