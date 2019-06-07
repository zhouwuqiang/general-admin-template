$(function () {

    initTable();
    $.initSelect("search_task_type", "task_type", false);
    $.initSelect("task_type", "task_type", true);

});

/**
 * 初始化表格
 */
function initTable() {
    $.tableExpand({
        tableId: "main_table",
        url: "/schedule/table",
        searchFormId: "main_table_search_form",
        uniqueId: "taskCode",
        pagination: true,
        columns: [
            {
                field: 'id',
                title: 'ID',
                align: 'center',
                valign: 'middle',
                visible: false
            }, {
                field: 'taskCode',
                title: '任务编号',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'taskName',
                title: '任务名称',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'taskType',
                title: '任务类型',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'taskUrl',
                title: '远程任务地址',
                align: 'center',
                valign: 'middle',
                visible: false
            }, {
                field: 'taskServiceName',
                title: '任务类名',
                align: 'center',
                valign: 'middle',
                visible: false
            }, {
                field: 'taskServiceMethod',
                title: '任务方法',
                align: 'center',
                valign: 'middle',
                visible: false
            }, {
                field: 'taskDesc',
                title: '任务描述',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'taskCron',
                title: '任务表达式',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'taskStatus',
                title: '任务状态',
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
    result.push("<a href='javascript:void(0)' class='' onclick='editTask(" + JSON.stringify(row) + ")'>修改</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='editWordbook(" + JSON.stringify(row) + ")'>执行一次</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='editWordbook(" + JSON.stringify(row) + ")'>添加表单</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='detailWordbook(" + JSON.stringify(row) + ")'>详情</a>");
    return $.formatterOperateButton(result);
}

/**
 * 添加
 */
function addTask() {
    $.initModel("main_mode", "添加任务", "main_form", "add-show");
    $(".remote-show").hide();
    $(".clazz-show").hide();
    $('#main_mode').modal('show');
}

/**
 * 任务类型变化
 */
function changeTaskType() {
    if ($("#task_type").val() === "01"){
        $(".remote-show").show();
        $(".clazz-show").hide();
    }else{
        $(".remote-show").hide();
        $(".clazz-show").show();
    }
}

/**
 * 编辑
 */
function editTask(row) {
    $.initModel("main_mode", "编辑任务", "main_form", "edit-show");
    $.formReview("main_form", row);

    $('#main_mode').modal('show');
}

/**
 * 显示
 */
function detailWordbook(row) {
    $.initModel("main_mode", "任务详情", "main_form", "detail-show");
    $.formReview("main_form", row);
    $.formReadOnly("main_form");

    $('#attribute_table').bootstrapTable('hideColumn', 'operation');

    $('#main_mode').modal('show');

}

/**
 * 保存
 */
function saveTask() {
    let param = $.formSerializeObject("main_form");
    $.ajax({
        url: "/schedule/save",
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
