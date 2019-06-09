$(function () {

    initTable();

    $.initSelect("menu_type", "menu_type", true);

    $("#menu_icon").initIconSelect();
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
        id: 'menuCode',                                                   // 选取记录返回的值,用于设置父子关系
        parentId: 'parentMenuCode',                                       // 用于设置父子关系
        rootIdValue: null,                                          // 设置根节点id值----可指定根节点，默认为null,"",0,"0"
        data: null,                                                 // 构造table的数据集合
        type: "POST",                                              // 请求数据的ajax类型
        contentType: "application/json",
        url: "/menu/table",                                         // 请求数据的ajax的url
        ajaxParams: JSON.stringify(params),                          // 请求数据的ajax的data属性
        expandColumn: 1,                                            // 在哪一列上面显示展开按钮
        expandAll: false,                                           // 是否全部展开
        expandFirst: false,                                          // 是否默认第一级展开--expandAll为false时生效
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
                field: 'menuCode',
                title: '菜单编号',
                align: 'center',
                valign: 'middle',
                // visible: false
            }, {
                field: 'menuName',
                title: '菜单名称',
                align: 'left',
                valign: 'middle',
                fixed: 'left'
            }, {
                field: 'menuIcon',
                title: '菜单图标',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'menuAction',
                title: '访问地址',
                align: 'left',
                valign: 'middle'
            }, {
                field: 'parentMenuCode',
                title: '上级菜单编号',
                align: 'center',
                valign: 'middle',
                // visible: false
            }, {
                field: 'menuType',
                title: '菜单类型',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'displayIndex',
                title: '显示序号',
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
    result.push("<a href='javascript:void(0)' class='' onclick='addSubMenu(" + JSON.stringify(row) + ")'>添加</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='editMenu(" + JSON.stringify(row) + ")'>修改</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='deleteMenu(" + JSON.stringify(row) + ")'>删除</a>");
    return $.formatterOperateButton(result);
}


/**
 * 展开/折叠
 */
expandAll = true;

function exchangeMenu() {
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
function addMenu() {
    $.initModel("main_mode", "添加菜单", "main_form", "add-show");
    $('#main_mode').modal('show');
}

/**
 * 添加子菜单
 * @param menu
 */
function addSubMenu(menu) {
    $.initModel("main_mode", "添加子菜单", "main_form", "add-show");
    $("#parent_menu_code").val(menu.menuCode);
    $("#parent_menu_code").attr("readonly","readonly");
    $('#main_mode').modal('show');
}

/**
 * 编辑
 */
function editMenu(menu) {
    $.initModel("main_mode", "编辑菜单", "main_form", "edit-show");
    $.formReview("main_form", menu);
    $("#parent_menu_code").attr("readonly","readonly");
    $('#main_mode').modal('show');
}

/**
 * 显示
 */
function detailMenu(menu) {
    $.initModel("main_mode", "菜单详情", "main_form", "detail-show");
    $.formReview("main_form", menu);
    $.formReadOnly("main_form");
    $('#main_mode').modal('show');
}

/**
 * 保存
 */
function saveMenu() {
    let param = $.formSerializeObject("main_form");
    $.ajax({
        url: "/menu/save",
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
function deleteMenu(menu) {
    $.ajax({
        url: "/menu/delete",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(menu),
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
