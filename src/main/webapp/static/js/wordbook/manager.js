$(function () {

    initTable();
    $.initSelect("wordbook_status", "000001", true)
    $.initSelect("attribute_status", "000001", true)
});

/**
 * 初始化表格
 */
function initTable() {
    $.tableExpand({
        tableId: "main_table",
        url: "/wordbook/table",
        searchFormId: "main_table_search_form",
        uniqueId: "wordbookCode",
        columns: [
            {
                field: 'wordbookCode',
                title: '属性编码',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'wordbookName',
                title: '属性名称',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'wordbookStatus',
                title: '属性状态',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'memo',
                title: '备注',
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
    result.push("<a href='javascript:void(0)' class='btn btn-info' onclick='editWordbook(" + JSON.stringify(row) + ")'><i class='fa fa-edit fa-icon'></i>修改</a>");
    result.push("<a href='javascript:void(0)' class='btn btn-info' onclick='detailWordbook(" + JSON.stringify(row) + ")'><i class='fa fa-th-list fa-icon'></i>详情</a>");
    return $.formatterOperateButton(result);
}

/**
 * 添加
 */
function addWordbook() {
    $.initModel("main_mode", "添加字典", "main_form", "add-show");
    initAttributeTable();
    $('#main_mode').modal('show');
}

/**
 * 编辑
 */
function editWordbook(wordbook) {
    $.initModel("main_mode", "编辑字典", "main_form", "edit-show");
    $.formReview("main_form", wordbook);

    initAttributeTable(wordbook.wordbookCode);

    $('#main_mode').modal('show');
}

/**
 * 显示
 */
function detailWordbook(wordbook) {
    $.initModel("main_mode", "字典信息", "main_form", "detail-show");
    $.formReview("main_form", wordbook);
    $.formReadOnly("main_form");

    initAttributeTable(wordbook.wordbookCode);
    $('#attribute_table').bootstrapTable('hideColumn', 'operation');

    $('#main_mode').modal('show');

}

/**
 * 保存
 */
function saveWordbook() {
    let param = $.formSerializeObject("main_form");
    let tableData = $('#attribute_table').bootstrapTable('getData');
    param.attributeList = tableData;
    $.ajax({
        url: "/wordbook/save",
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

/******************************************************** 具体属性 ****************************************************/

function initAttributeTable(wordbookCode) {
    let path = "";
    if ($.isNotNull(wordbookCode)) {
        path = "/wordbook/attribute/table";
    }

    $.tableExpand({
        tableId: "attribute_table",
        url: path,
        params: {"wordbookCode": wordbookCode},
        toolbar: "",
        uniqueId: "number",
        pagination: false,
        columns: [
            {
                field: 'number',
                title: '序号',
                halign: "center",
                align: "center",
                formatter: function (value, row, index) {
                    row.number = index + 1;
                    return row.number;
                }
            }, {
                field: 'wordbookCode',
                title: '属性编码',
                align: 'center',
                valign: 'middle',
                visible: false
            }, {
                field: 'attributeValue',
                title: '枚举值',
                align: 'center',
                valign: 'middle',
                visible: false
            }, {
                field: 'attributeName',
                title: '名称',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'attributeMemo',
                title: '备注',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'attributeStatus',
                title: '状态',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'operation',
                title: '操作 <a class="pointer" onclick="addAttribute()">添加</a>',
                align: 'center',
                valign: 'middle',
                width: '30%',
                formatter: attributeOperateFormatter
            }
        ]
    });

}

function attributeOperateFormatter(value, row, index) {
    let result = [];
    result.push("<a href='javascript:void(0)' class='' onclick='editAttribute(" + JSON.stringify(row) + ")'>修改</a>");
    if ($.isNotNull(row.id)) {
        result.push("<a href='javascript:void(0)' class='' onclick='deleteAttribute(" + JSON.stringify(row) + ")'>禁用</a>");
    } else {
        result.push("<a href='javascript:void(0)' class='' onclick='deleteAttribute(" + JSON.stringify(row) + ")'>移除</a>");
    }
    return $.formatterOperateButton(result);
}

/**
 * 添加属性
 */
function addAttribute() {
    $.initModel("attribute_mode", "新增属性", "attribute_form", "edit-show");
    $('#attribute_mode').modal('show');
}

/**
 * 添加属性
 */
function editAttribute(attribute) {
    $.initModel("attribute_mode", "编辑属性", "attribute_form", "edit-show");
    $.formReview("attribute_form", attribute);

    $('#attribute_mode').modal('show');
}

/**
 * 添加属性
 */
function saveAttribute() {
    let params = $.formSerializeObject("attribute_form");
    $.tableSaveRow("attribute_table", params, params.number);
    $('#attribute_mode').modal('hide');
}

/**
 * 禁用属性
 */
function deleteAttribute() {
    let params = $.formSerializeObject("");
}
