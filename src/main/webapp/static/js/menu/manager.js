$(function () {

    initTable();

});

/**
 * 初始化表格
 */
function initTable() {
    $('#main_table').bootstrapTable('destroy').bootstrapTable({
        url: "/menu/table",
        method: 'post',
        contentType: "application/json",       //修改为json请求
        uniqueId: 'id',                        // 绑定ID，不显示
        striped: false,                         //是否显示行间隔色
        cache: false,                          //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: false,                        //是否启用排序
        sortOrder: "asc",                      //排序方式
        sidePagination: "server",              //分页方式：client客户端分页，server服务端分页（*）
        undefinedText: '--',
        singleSelect: true,                  // 单选checkbox，默认为复选
        showRefresh: false,                  // 显示刷新按钮
        showColumns: false,                  // 选择显示的列
        toolbar: '#toolbar',                // 搜索框位置
        search: false,                      // 搜索开启,
        strictSearch: false,
        clickToSelect: true,                   // 点击选中行
        pagination: false,                      //是否显示分页
        smartDisplay:false,
        // pageNumber: 1,                          //初始化加载第一页，默认第一页,并记录
        // pageSize: 10,                           //默认每页显示的数量
        // pageList: [5, 10, 20, 50, 100],         //设置每页显示的数量
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        paginationLoop: false,              //分页条无限循环
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        showPaginationSwitch: false,        //是否显示切换分页按钮
        columns: [
            {
                field: 'menuCode',
                title: '菜单编号',
                align: 'center',
                valign: 'left',
                visible: false
            }, {
                field: 'menuName',
                title: '菜单名称',
                align: 'left',
                valign: 'middle'
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
                visible: false
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
        ],
        queryParams: function (params) {

            let param = $.formSerializeObject("main_table_search_form");

            return param;
        },
        responseHandler: function (res) {

            if (res.success) {
                return  res.data.list
            }

            $.ajaxMassage(res);

            return {};
        },
        onLoadSuccess: function (data) {
        },
        onLoadError: function () {
            alert("网络异常!请稍候再试!");
        }
    });
}


function operateFormatter(value, row, index) {
    let result = [];
    result.push("<a href='javascript:void(0)' class='btn btn-info' onclick='deleteUser(" + JSON.stringify(row) + ")'><i class='fa fa-trash-o fa-icon'></i>添加</a>");
    result.push("<a href='javascript:void(0)' class='btn btn-warning' onclick='editUser(" + JSON.stringify(row) + ")'><i class='fa fa-edit fa-icon'></i>修改</a>");
    result.push("<a href='javascript:void(0)' class='btn btn-danger' onclick='deleteUser(" + JSON.stringify(row) + ")'><i class='fa fa-trash-o fa-icon'></i>删除</a>");
    return $.formatterOperateButton(result);
}

/**
 * 添加
 */
function addUser() {
    $.initModel("main_mode", "添加用户", "main_form", "add-show");
    $('#main_mode').modal('show');
}

/**
 * 编辑
 */
function editUser(user) {
    $.initModel("main_mode", "编辑用户", "main_form", "edit-show");
    $.formReview("main_form", user);
    $('#main_mode').modal('show');
}

/**
 * 显示
 */
function detailUser(user) {
    $.initModel("main_mode", "用户信息", "main_form", "detail-show");
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

/**
 * 删除
 */
function deleteUser(user) {
    user.deleteFlag = "01";
    $.ajax({
        url: "/user/save",
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
