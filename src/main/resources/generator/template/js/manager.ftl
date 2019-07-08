$(function () {

    initTable();

});

/**
 * 初始化表格
 */
function initTable() {
    $.tableExpand({
        tableId: "main_table",
        url: "/online/table",
        searchFormId: "main_table_search_form",
        uniqueId: "",
        pagination: true,
        columns: [

<#if columnSet??>
    <#list columnSet as field>
            {
                field: '${field.columnName}',
                <#if field.remarks??>
                    title: '${field.remarks}',
                </#if>
                align: 'center',
                valign: 'middle'
            },
    </#list>
</#if>
            {
                field: 'storageServer',
                title: '操作',
                align: 'center',
                valign: 'middle',
                formatter: operateFormatter
            }
        ]
    });

}

function operateFormatter(value, row, index) {
    let result = [];
    result.push("<a href='javascript:void(0)' class='' onclick='editRow(" + JSON.stringify(row) + ")'>编辑</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='showRow(" + JSON.stringify(row) + ")'>查看</a>");
    result.push("<a href='javascript:void(0)' class='' onclick='deleteRow(" + JSON.stringify(row) + ")'>删除</a>");
    return $.formatterOperateButton(result);
}

/**
 * 添加
 */
function addRole() {
    $.initModel("main_mode", "添加", "main_form", "add-show");
    $('#main_mode').modal('show');
}

/**
 * 编辑
 */
function editRow(row) {
    $.initModel("main_mode", "编辑", "main_form", "edit-show");
    $.formReview("main_form", row);
    $('#main_mode').modal('show');
}

/**
 * 显示
 */
function showRow(row) {
    $.initModel("main_mode", "信息", "main_form", "detail-show");
    $.formReview("main_form", row);
    $.formReadOnly("main_form");
    $('#main_mode').modal('show');
}

/**
 * 保存
 */
function saveEntity() {
    let param = $.formSerializeObject("main_form");
    $.ajax({
        url: "/${module}}/save",
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
function deleteRow(entity) {
    entity.deleteFlag = "01";
    $.ajax({
        url: "/${module}/delete",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(entity),
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
