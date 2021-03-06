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
    result.push("<a href='javascript:void(0)' class='' onclick='executeOnce(" + JSON.stringify(row) + ")'>执行一次</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='initFormMode(" + JSON.stringify(row) + ")'>配置表单</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='detailTask(" + JSON.stringify(row) + ")'>详情</a>");

    if (row.taskStatus === "01") {
        result.push("<a href='javascript:void(0)' class='' onclick='changeStatus(" + JSON.stringify(row) + ",\"01\")'>启用</a>");
    } else {
        result.push("<a href='javascript:void(0)' class='' onclick='changeStatus(" + JSON.stringify(row) + ",\"02\")'>禁用</a>");
    }
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
    if ($("#task_type").val() === "01") {
        $(".remote-show").show();
        $(".clazz-show").hide();
    } else {
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
 * 调整状态
 */
function changeStatus(row, taskStatus) {
    let param = {};
    param.taskCode = row.taskCode;
    param.taskStatus = taskStatus;
    $.ajax({
        url: "/schedule/change/status",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function (responseDto) {
            $.ajaxMassage(responseDto);
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });
}

/**
 * 显示
 */
function detailTask(row) {
    $.initModel("main_mode", "任务详情", "main_form", "detail-show");
    $.formReview("main_form", row);
    $.formReadOnly("main_form");

    $('#attribute_table').bootstrapTable('hideColumn', 'operation');

    $('#main_mode').modal('show');

}

/**
 * 执行一次
 */
function executeOnce(row) {
    $("#param_task_code").val(row.taskCode);
    $('#param_mode').modal('show');
}

function doExecute() {
    let param = $.formSerializeObject("param_form");
    $.ajax({
        url: "/schedule/execute/once",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function (responseDto) {
            $.ajaxMassage(responseDto);
            if (responseDto.success) {
                $('#param_mode').modal('hide');
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


/******************************************************* 任务参数 ******************************************/

function initFormMode(row) {
    initFormTable(row.taskCode);
    $('#form_mode').modal('show');
}

function initFormTable(taskCode) {
    let path = "";
    if ($.isNotNull(taskCode)) {
        path = "/schedule/form/table";
    }

    $.tableExpand({
        tableId: "form_table",
        url: path,
        params: {"taskCode": taskCode},
        toolbar: "",
        uniqueId: "id",
        pagination: false,
        columns: [
            {
                field: 'index',//可不加
                title: 'index',//标题  可不加
                formatter: function (value, row, index) {
                    row.index = index;
                    return "<span class='form-control'>" + index + "</span>";
                },
                width: '5%'
            }, {
                field: 'id',
                title: '序号',
                align: 'center',
                valign: 'middle',
                // formatter: inputFormatter,
                visible: false
            }, {
                field: 'taskCode',
                title: '任务编号',
                align: 'center',
                valign: 'middle',
                // formatter: inputFormatter
                visible: false
            }, {
                field: 'inputName',
                title: '任务表单名称',
                align: 'center',
                valign: 'middle',
                // formatter: inputFormatter,
                visible: false
            }, {
                field: 'inputLabel',
                title: '任务表单label',
                align: 'center',
                valign: 'middle',
                width: '25%',
                formatter: inputFormatter
            }, {
                field: 'inputMemo',
                title: '任务表单备注',
                align: 'center',
                valign: 'middle',
                width: '25%',
                formatter: inputFormatter
            }, {
                field: 'inputNotNull',
                title: '是否必填',
                align: 'center',
                valign: 'middle',
                width: '25%',
                formatter: selectFormatter
            }, {
                field: 'operation',
                title: "操作 <a class='pointer' onclick='addForm(\"" + taskCode + "\")'>添加</a>",
                align: 'center',
                valign: 'middle',
                width: '20%',
                formatter: attributeOperateFormatter
            }
        ]
    });

}

function attributeOperateFormatter(value, row, index) {
    let result = [];
    if (row.editable) {
        result.push("<a href='javascript:void(0)' class='' onclick='saveForm(" + JSON.stringify(row) + "," + index + ",this)'>保存</a>");
    } else {
        result.push("<a href='javascript:void(0)' class='' onclick='editForm(" + JSON.stringify(row) + "," + index + ")'>修改</a>");
    }
    result.push("<a href='javascript:void(0)' class='' onclick='deleteRow(" + JSON.stringify(row) + "," + index + ")'>删除</a>");
    return $.formatterOperateButton(result);
}


/**
 * 添加属性
 */
function addForm(taskCode) {
    $("#form_table").bootstrapTable('append', {"editable": true, "taskCode": taskCode});
}

/**
 * 添加属性
 */
function editForm(row, index) {
    row.editable = true;
    $("#form_table").bootstrapTable('updateRow', {index: index, row: row});
}

/**
 * 添加属性
 */
function saveForm(row, index, self) {
    debugger;

    let tdList = $(self).parents("tr").find("input,select");

    for (let index = 0; index < tdList.length; index++) {
        let name = tdList[index].name;
        let value = $(tdList[index]).val();
        row[name] = value;
    }


    $.ajax({
        url: "/schedule/form/save",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(row),
        success: function (responseDto) {
            $.ajaxMassage(responseDto);
            row = responseDto.data
            row.editable = false;
            $("#form_table").bootstrapTable('updateRow', {index: index, row: row});
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
function deleteRow(row, index) {

    if ($.isNotNull(row.id)) {
        let param = {};
        param.id = row.id;
        $.ajax({
            url: "/schedule/form/delete",
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(param),
            success: function (responseDto) {
                $.ajaxMassage(responseDto);
            },
            error: function () {
                console.log("请求处理失败!");
                $.errorMassage("请求处理失败!");
            }
        });
    }

    $("#form_table").bootstrapTable('remove', {
        field: 'index',
        values: [index]
    })
}

/**
 * 初始化input输入框
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function inputFormatter(value, row, index) {

    let self = this;

    if ($.isNull(value)) {
        value = "";
    }

    if (row.editable) {
        return "<input type='text' value='" + value + "' class='form-control' id='" + index + self.field + "' name='" + self.field + "' onblur='inputEdit(this)'>";
    }

    return "<input type='text' readonly='readonly' value='" + value + "' class='form-control' id='" + index + self.field + "' name='" + self.field + "'>";
}


/**
 * 初始化select输入框
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function selectFormatter(value, row, index) {
    let self = this;

    let selectOption = $.selectOption("is_or_not", value, true);

    if (row.editable) {
        return "<select class='form-control' id='" + index + self.field + "' name='" + self.field + "' onblur='inputEdit(this)'>" +
            selectOption +
            "</select>";
    }

    return "<select disabled='disabled' class='form-control' id='" + index + self.field + "' name='" + self.field + "' onblur='inputEdit(this)'>" +
        selectOption +
        "</select>";
}


function inputEdit(self) {
    console.log($(self).val());
}
