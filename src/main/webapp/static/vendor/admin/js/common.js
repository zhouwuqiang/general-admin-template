/**
 * 需要顶级窗口处理的事件
 */
$(function () {

    $(document).ajaxStart(function (event, xhr, options) {
        if ($.isNotNull(window.top.Pace)) {
            window.top.Pace.restart();
        }
    });

    $(document).ajaxStop(function (event, xhr, options) {
        if ($.isNotNull(window.top.Pace)) {
            window.top.Pace.stop();
        }
    });

    $(document).ajaxComplete(function (event, xhr, options) {
        if (xhr.status === 200) {
            let response = xhr.responseJSON;
            if (!response.success) {
                switch (response.code) {
                    case "000004":
                        alert(response.message);
                }
            }
        }
        // console.log(JSON.stringify(event));
        // console.log(JSON.stringify(xhr));
        // console.log(JSON.stringify(options));
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

const bootStrapTableConfig = {
    url: '',
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
    toolbar: '#toolbar',               // 搜索框位置
    search: false,                      // 搜索开启,
    strictSearch: false,
    clickToSelect: true,                   // 点击选中行
    maintainSelected: false,                 //多选还是单选
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
    columns: []
};
/**
 * tableId 表格id
 * url 请求地址
 * searchFormId 查询表格
 * columns返回列
 */
$.extend({
    "tableExpand": function (data) {

        let config = Object.assign({}, bootStrapTableConfig, data);

        $('#' + config.tableId).bootstrapTable('destroy').bootstrapTable({
            url: config.url,
            method: config.method,
            contentType: config.contentType,
            uniqueId: config.uniqueId,
            striped: config.striped,
            cache: config.cache,
            sortable: config.sortable,
            sortOrder: config.sortOrder,
            sidePagination: config.sidePagination,
            undefinedText: config.undefinedText,
            singleSelect: config.singleSelect,
            showRefresh: config.showRefresh,
            showColumns: config.showColumns,
            toolbar: config.toolbar,
            search: config.search,
            strictSearch: config.strictSearch,
            clickToSelect: config.clickToSelect,
            maintainSelected: config.maintainSelected,
            pagination: config.pagination,
            pageNumber: config.pageNumber,
            pageSize: config.pageSize,
            pageList: config.pageList,
            paginationPreText: config.paginationPreText,
            paginationNextText: config.paginationNextText,
            paginationLoop: config.paginationLoop,
            showToggle: config.showToggle,
            cardView: config.cardView,
            detailView: config.detailView,
            showPaginationSwitch: config.showPaginationSwitch,
            columns: config.columns,
            queryParams: function (params) {
                let param = {};

                if ($.isNotNull(config.searchFormId)) {
                    param = $.formSerializeObject(config.searchFormId);
                }

                if ($.isNotNull(config.params)) {
                    Object.assign(param, config.params)
                }

                param.pageSize = params.limit;
                param.pageNum = (params.offset / params.limit) + 1;
                $.deleteEmptyKey(param);

                return param;
            },
            responseHandler: function (res) {

                if (res.success) {
                    if (config.pagination) {
                        return {
                            "total": res.data.total,//总页数
                            "rows": res.data.list   //数据
                        };
                    } else {
                        return res.data.list;
                    }
                }

                $.ajaxMassage(res);

                return {};
            },
            onLoadSuccess: function (data) {
                //console.log("表格加载成功!" + JSON.stringify(data));
            },
            onLoadError: function (status, jqXHR) {
                //console.log("表格加载异常!");
                debugger;
                alert("网络异常!请稍候再试!");
            }
        });
    },
    "tableSaveRow": function (tableId, row, id) {
        if ($.isNull(id)) {
            $('#' + tableId).bootstrapTable('append', row);
        } else {
            $('#' + tableId).bootstrapTable('updateByUniqueId', {
                'id': id,
                'row': row
            });
        }
    }
});


/**
 * 表单操作
 */
$.extend({
    "formRest": function (formId) {
        let formElement = document.getElementById(formId);

        if ($.isNull(formElement)) {
            return;
        }

        for (let i = 0; i < formElement.length; i++) {
            // radio 选中第一个
            if (formElement.elements[i].type === "radio") {
                let name = formElement.elements[i].name;
                let radioList = document.getElementsByName(name);
                radioList[0].checked = true;
                continue;
            }

            formElement.elements[i].value = "";
        }
    },
    "formReview": function (formId, data) {
        let formElement = document.getElementById(formId);
        for (let i = 0; i < formElement.length; i++) {

            if (formElement.elements[i].type === "radio") {
                if (formElement.elements[i].value === data[formElement.elements[i].name]) {
                    formElement.elements[i].checked = true;
                }
                continue;
            }

            formElement.elements[i].value = data[formElement.elements[i].name];

            if ("function" === typeof formElement.elements[i].onchange) {
                formElement.elements[i].onchange()
            }

        }
    },
    "formReadOnly": function (formId) {
        let formElement = document.getElementById(formId);
        for (let i = 0; i < formElement.length; i++) {
            formElement.elements[i].readOnly = true;

            if ("SELECT" === formElement.elements[i].tagName) {
                formElement.elements[i].disabled = true;
            }
            if ("radio" === formElement.elements[i].type) {
                formElement.elements[i].disabled = true;
            }
        }
    },
    "formWrite": function (formId) {
        let formElement = document.getElementById(formId);
        for (let i = 0; i < formElement.length; i++) {
            formElement.elements[i].readOnly = false;

            if ("SELECT" === formElement.elements[i].tagName) {
                formElement.elements[i].disabled = false;
            }

            if ("radio" === formElement.elements[i].type) {
                formElement.elements[i].disabled = false;
            }
        }
    },
    "formSerializeObject": function (formId) {
        let result = {};
        let formElement = document.getElementById(formId);
        for (let i = 0; i < formElement.length; i++) {

            if ("radio" === formElement.elements[i].type && formElement.elements[i].checked !== true) {
                continue;
            }

            result[formElement.elements[i].name] = formElement.elements[i].value;
        }

        $.deleteEmptyKey(result);
        return result;
    }
});


/**
 * 判断是否为空
 */
$.extend({
    "isNull": function (data) {

        if (data === undefined || data === null || data === "") {
            return true;
        }

        return false;
    },
    "isNotNull": function (data) {
        return !$.isNull(data)
    },
    "deleteEmptyKey": function (data) {
        for (let item in data) {
            if ($.isNull(data[item])) {
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
        let query = window.location.search.substring(1);
        let vars = query.split("&");
        for (let i = 0; i < vars.length; i++) {
            let pair = vars[i].split("=");
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
        let flag = true;
        if (window.top.document.body.clientWidth < 769) {
            flag = false;
        }
        return flag;
    }
});


/**
 * 新增,修改,查看 model初始化
 */
$.extend({
    "initModel": function (modeId, title, formId, show) {
        let model = $("#" + modeId);
        model.find(".modal-header").find(".modal-title").html(title);

        $.formRest(formId);
        $.formWrite(formId);

        model.find(".modal-footer").find("button").hide();
        model.find(".init-hidden").hide();

        if ($.isNotNull(show)) {
            model.find("." + show).show();
        }
    }
});


/**
 * ajax请求返回提示
 */
$.extend({
    "ajaxMassage": function (responseDto, messagePosition) {

        if ($.isNull(messagePosition)) {
            messagePosition = 'top-right';
        }

        if (responseDto.success) {
            $.toast({
                text: responseDto.message,
                allowToastClose: true,
                hideAfter: 1000,
                position: messagePosition,
                bgColor: '#5bc0de'
            });
        } else {
            $.toast({
                text: responseDto.message,
                allowToastClose: false,
                position: messagePosition,
                bgColor: '#AC2925'
            });
        }
    },
    "errorMassage": function (message, messagePosition) {

        if ($.isNull(messagePosition)) {
            messagePosition = 'top-right';
        }

        $.toast({
            text: message,
            allowToastClose: false,
            position: messagePosition,
            bgColor: '#AC2925'
        });
    }
});

/**
 * 初始化下拉框
 * selectId 下拉框id
 * code 属性code
 * needEmpty 是否需要空选型
 */
$.extend({
    "initSelect": function (selectId, code, needEmpty) {

        if (needEmpty) {
            $("#" + selectId).append("<option value=''>--请选择--</option>");
        }

        $.ajax({
            url: "/wordbook/select/" + code,
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            success: function (responseDto) {
                let $select = $("#" + selectId);
                if (responseDto.success) {
                    let option = responseDto.data;
                    for (let i in option) {
                        $select.append("<option value='" + option[i].attributeValue + "'>" + option[i].attributeName + "</option>");
                    }
                }
            },
            error: function () {
                console.log("请求处理失败!");
                $.errorMassage("请求处理失败!");
            }
        });
    },
    "selectOption": function (code, value, needEmpty) {
        let result = "";

        let optionList = [];
        if (needEmpty) {
            optionList.push("<option value=''>--请选择--</option>");
        }

        $.ajax({
            url: "/wordbook/select/" + code,
            type: 'post',
            dataType: 'json',
            async: false,
            contentType: 'application/json',
            success: function (responseDto) {
                if (responseDto.success) {
                    let option = responseDto.data;
                    for (let i in option) {
                        if (option[i].attributeValue === value) {
                            optionList.push("<option  selected='selected' value='" + option[i].attributeValue + "'>" + option[i].attributeName + "</option>");
                        } else {
                            optionList.push("<option value='" + option[i].attributeValue + "'>" + option[i].attributeName + "</option>");
                        }

                    }


                    for (let item in optionList) {
                        result += optionList[item];
                    }


                }
            },
            error: function () {
                console.log("请求处理失败!");
                $.errorMassage("请求处理失败!");
            }
        });

        return result;
    }
});



