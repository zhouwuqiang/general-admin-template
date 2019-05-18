/**
 * 需要顶级窗口处理的事件
 */
$(function () {

    $(document).ajaxStart(function () {
        if ($.isNotNull(window.top.Pace)) {
            window.top.Pace.restart();
        }
    });
    $(document).ajaxStop(function () {
        if ($.isNotNull(window.top.Pace)) {
            window.top.Pace.stop();
        }
    });

    window.document.addEventListener("click", function (event) {
        $(window.top.document).find("#jqContextMenu").hide();
        $(window.top.document).find("#jqContextMenuShadow").hide();
    });


    $("[data-toggle='tooltip']").tooltip();
});

/**
 * 表格刷 >> 新当前页
 */
function refreshTable(tableId) {
    $('#' + tableId).bootstrapTable('refresh');
}

/**
 * tableId 表格id
 * url 请求地址
 * searchFormId 查询表格
 * columns返回列
 */
$.extend({
    "tableExpand": function (data) {
        $('#' + data.tableId).bootstrapTable('destroy').bootstrapTable({
            url: data.url,
            method: 'post',
            contentType: "application/json",       //修改为json请求
            uniqueId: 'id',                        // 绑定ID，不显示
            striped: true,                         //是否显示行间隔色
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
            pagination: true,                      //是否显示分页
            pageNumber: 1,                          //初始化加载第一页，默认第一页,并记录
            pageSize: 10,                           //默认每页显示的数量
            pageList: [5, 10, 20, 50, 100],         //设置每页显示的数量
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            paginationLoop: false,              //分页条无限循环
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            showPaginationSwitch: false,        //是否显示切换分页按钮
            columns: data.columns,
            queryParams: function (params) {

                var param ={};

                if ($.isNotNull(data.searchFormId)) {
                    param = $.formSerializeObject(data.searchFormId);
                }

                param.pageSize = params.limit;
                param.pageNum = (params.offset / params.limit) + 1;
                $.deleteEmptyKey(param);

                return param;
            },
            responseHandler: function (res) {

                return {
                    "total": res.data.total,//总页数
                    "rows": res.data.list   //数据
                };

            },
            onLoadSuccess: function (data) {
                //console.log("表格加载成功!" + JSON.stringify(data));
            },
            onLoadError: function () {
                //console.log("表格加载异常!");
                alert("网络异常!请稍候再试!");
            }
        });
    }
});


/**
 * 时间戳格式化显示
 * @param value
 * @returns {string}
 */
function timeStampFormatter(value) {
    var date = new Date();
    date.setTime(value);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
}


/**
 * 判断是否为空
 */
$.extend({
    "isNull": function (data) {

        if (data === undefined || data == null || data == "") {
            return true;
        }

        return false;
    },
    "isNotNull": function (data) {
        return !$.isNull(data)
    }
});


/**
 * form表单数据格式化
 */
$.extend({
    "formSerializeObject": function (formId) {
        var result = {};
        var formElement = document.getElementById(formId);
        for (var i = 0; i < formElement.length; i++) {
            result[formElement.elements[i].name] = formElement.elements[i].value;
        }

        return result;
    }
});

/**
 * form重置
 */
$.extend({
    "formRest": function (formId) {
        var formElement = document.getElementById(formId);
        for (var i = 0; i < formElement.length; i++) {
            formElement.elements[i].value = "";
        }
    }
});

/**
 * form表单数据反显
 */
$.extend({
    "formReview": function (formId, data) {
        var formElement = document.getElementById(formId);
        for (var i = 0; i < formElement.length; i++) {
            formElement.elements[i].value = data[formElement.elements[i].name];

            if ("function" === typeof formElement.elements[i].onchange) {
                formElement.elements[i].onchange()
            }

        }
    }
});

/**
 * form表单只读
 */
$.extend({
    "formReadOnly": function (formId) {
        var formElement = document.getElementById(formId);
        for (var i = 0; i < formElement.length; i++) {
            formElement.elements[i].readOnly = true;

            if ("SELECT" === formElement.elements[i].tagName) {
                formElement.elements[i].disabled = true;
            }
        }
    }
});

/**
 * form可以填写
 */
$.extend({
    "formWrite": function (formId) {
        var formElement = document.getElementById(formId);
        for (var i = 0; i < formElement.length; i++) {
            formElement.elements[i].readOnly = false;

            if ("SELECT" === formElement.elements[i].tagName) {
                formElement.elements[i].disabled = false;
            }
        }
    }
});

/**
 * 删除空白字段
 */
$.extend({
    "deleteEmptyKey": function (data) {
        for (var item in data) {
            if (data[item] == "") {
                delete data[item];
            }
        }
        return data;
    }
});

/**
 * 格式化表格操作数组显示
 */
$.extend({
    "formatterOperateButton": function (data) {
        var result = "";

        if (data.length === 0) {
            return "--";
        }

        for (var item in data) {
            result += data[item];
            result += "&nbsp;&nbsp;";
        }
        return result;
    }
});

/**
 * 格式化表格操作数组显示
 */
$.extend({
    "getUrlPath": function (variable) {

        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] === variable) {
                return pair[1];
            }
        }
        return (false);

    }
});

/**
 * 判断是否是电脑
 */
$.extend({
    "isPC": function () {
        var flag = true;
        if ((navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i))) {
            flag = false;
        }
        return flag;
    },
    "isNotMobile": function () {
        var flag = true;
        if (window.top.document.body.clientWidth < 769) {
            flag = false;
        }
        return flag;
    }
});

$.extend({
    "getUrlParam": function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null){
            return unescape(r[2]);
        }
        return null;
    }
});
