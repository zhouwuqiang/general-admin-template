$(function () {

    initTable();

});

/**
 * 初始化表格
 */
function initTable() {
    $.tableExpand({
        tableId: "main_table",
        url: "/log/login/table",
        searchFormId: "main_table_search_form",
        columns: [
            {
                field: 'userName',
                title: '用户账号',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'ipAddress',
                title: '登录ip',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'systemType',
                title: '系统类型',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'browserType',
                title: '浏览器类型',
                align: 'center',
                valign: 'middle'
            }, {
                field: 'loginTime',
                title: '登录时间',
                align: 'center',
                valign: 'middle'
            }
        ]
    });

}
