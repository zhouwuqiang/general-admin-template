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
            {
                field: 'wordbookCode',
                title: '字典编码',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'wordbookName',
                title: '字典名称',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'wordbookStatus',
                title: '字典状态',
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
    result.push("<a href='javascript:void(0)' class='' onclick='kickOut(" + JSON.stringify(row) + ")'>踢出</a>");
    return $.formatterOperateButton(result);
}

function kickOut() {
    alert("踢出用户")
}
