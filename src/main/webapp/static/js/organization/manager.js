$(function () {

    initTable();

    $.initSelect("organization_type", "organization_type", true);
    $.initSelect("param_organization_type", "organization_type", true);

    let defaultConfig = {
        getData: getData({}),
        onNodeSelected: function (event, node) {
            $("#paren_code").val(node.organizationCode);
            $("#paren_name").val(node.organizationName);
        },
        onNodeUnselected: function (event, node) {
            $("#paren_code").val("");
            $("#paren_name").val("");
        }
    };
    $("#paren_name").initTreeSelect(defaultConfig);
});


/**
 * 刷新表格
 */
function refreshTable() {
    let params = $.formSerializeObject("main_table_search_form");
    $.deleteEmptyKey(params);
    $('#main_table').bootstrapTreeTable('refresh', JSON.stringify(params));
}

/**
 * 初始化表格
 */
function initTable() {

    let params = $.formSerializeObject("main_table_search_form");
    $.deleteEmptyKey(params);
    $("#main_table").bootstrapTreeTable({
        toolbar: "#toolbar",      //顶部工具条
        id: 'organizationCode',                                                   // 选取记录返回的值,用于设置父子关系
        parentId: 'parenCode',                                       // 用于设置父子关系
        rootIdValue: null,                                          // 设置根节点id值----可指定根节点，默认为null,"",0,"0"
        type: "POST",                                              // 请求数据的ajax类型
        contentType: "application/json",
        url: "/organization/table",                                         // 请求数据的ajax的url
        ajaxParams: JSON.stringify(params),                          // 请求数据的ajax的data属性
        expandColumn: 1,                                            // 在哪一列上面显示展开按钮
        expandAll: false,                                           // 是否全部展开
        expandFirst: true,                                          // 是否默认第一级展开--expandAll为false时生效
        striped: false,                                             // 是否各行渐变色
        bordered: true,                                             // 是否显示边框
        hover: true,                                                // 是否鼠标悬停
        condensed: false,                                           // 是否紧缩表格
        width: 0,                                                   // 表格宽度
        height: 0,                                                  // 表格高度
        showTitle: true,                                            // 是否采用title属性显示字段内容（被formatter格式化的字段不会显示）
        showColumns: false,                                          // 是否显示内容列下拉框
        showRefresh: false,                                          // 是否显示刷新按钮
        expanderExpandedClass: 'bstt-icon bstt-chevron-down',       // 展开的按钮的图标
        expanderCollapsedClass: 'bstt-icon bstt-chevron-right',     // 缩起的按钮的图标
        toolRefreshClass: 'bstt-icon bstt-refresh',                 // 工具栏刷新按钮
        toolColumnsClass: 'bstt-icon bstt-columns',                 // 工具栏列按钮
        columns: [
            {
                field: 'id',
                title: 'id',
                align: 'center',
                visible: false
            }, {
                field: 'organizationName',
                title: '组织名称',
                align: 'center',
                valign: 'middle',
                fixed: 'left'
            }, {
                field: 'organizationCode',
                title: '组织编号',
                align: 'left',
                valign: 'middle'
            }, {
                field: 'organizationType',
                title: '组织类型',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'parenCode',
                title: '上级编号',
                align: 'left',
                valign: 'middle'
            }, {
                field: 'organizationStatus',
                title: '状态',
                align: 'center',
                valign: 'middle',
                visible: false
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
    result.push("<a href='javascript:void(0)' class='' onclick='addSubOrganization(" + JSON.stringify(row) + ")'>添加</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='organizationAuthorization(" + JSON.stringify(row) + ")'>授权</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='editOrganization(" + JSON.stringify(row) + ")'>修改</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='deleteOrganization(" + JSON.stringify(row) + ")'>删除</a>");
    return $.formatterOperateButton(result);
}


/**
 * 展开/折叠
 */
expandAll = true;

function exchange() {
    if (expandAll) {
        $('#main_table').bootstrapTreeTable('expandAll');
    } else {
        $('#main_table').bootstrapTreeTable('collapseAll');
    }
    expandAll = !expandAll;
}


/**
 * 添加
 */
function addOrganization() {
    $.initModel("main_mode", "添加结构", "main_form", "add-show");
    $('#main_mode').modal('show');
}


/**
 * 编辑
 */
function editOrganization(row) {
    $.initModel("main_mode", "编辑结构", "main_form", "edit-show");
    $.formReview("main_form", row);
    $("#paren_code").val(row.parenCode);
    $("#paren_name").val(row.parentName);
    $('#main_mode').modal('show');
}

/**
 * 添加子结构
 */
function addSubOrganization(row) {
    $.initModel("main_mode", "添加子结构", "main_form", "add-show");
    $("#paren_code").val(row.organizationCode);
    let $paren = $("#paren_name");
    $paren.val(row.organizationName);
    $paren.attr("readonly", "readonly");

    $('#main_mode').modal('show');
}

/**
 * 保存
 */
function saveOrganization() {
    let param = $.formSerializeObject("main_form");
    $.ajax({
        url: "/organization/save",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function (responseDto) {
            $.ajaxMassage(responseDto);
            if (responseDto.success) {
                $('#main_mode').modal('hide');
                refreshTable();
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
function deleteOrganization(row) {
    $.ajax({
        url: "/organization/delete",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(row),
        success: function (responseDto) {
            $.ajaxMassage(responseDto);
            if (responseDto.success) {
                refreshTable();
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
function organizationAuthorization(row) {

    initAuthorizationTree("menu_tree", {"organizationCode": row.organizationCode});

    $("#organization_relation_code").val(row.organizationCode);

    $('#organization_relation_mode').modal('show');
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
    params.organizationCode = $("#organization_relation_code").val();
    params.menuCodeList = selectCodeList;

    $.ajax({
        url: "/organization/relation/save",
        type: 'post',
        dataType: 'json',
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(params),
        success: function (responseDto) {
            if (responseDto.success) {
                $('#organization_relation_mode').modal('hide');
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });


}
