<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />


    <title>session管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="/static/img/favicon.ico">
    <link rel="stylesheet" href="/static/vendor/admin/css/reset.css?v=2.0">
    <link rel="stylesheet" href="/static/vendor/bootstrap/css/bootstrap.css?v=3.3.7">
    <link rel="stylesheet" href="/static/vendor/font-awesome/css/font-awesome.css?v=4.7.0">
    <link rel="stylesheet" href="/static/vendor/bootstrap-table/bootstrap-table.min.css">
    <link rel="stylesheet" href="/static/vendor/jquery-toast-plugin/jquery.toast.min.css">


</head>

<body class="gray-bg">

<div class="container-fluid">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="search-form-div clearfix">
                        <form role="form" id="main_table_search_form">
                            <#if columnSet??>
                                <#list columnSet as field>
                                    <div class="col-xs-2">
                                        <input type="text" autocomplete="off" id="param_${field.columnName}" name="${field.columnName}"
                                            <#if field.remarks??>
                                                       placeholder="${field.remarks}"
                                            </#if>
                                               class="col-sm-6  form-control"/>
                                    </div>
                                </#list>
                            </#if>


                            <div class="col-md-1">
                                <a href="javascript:void(0);" class="btn btn-info" onclick="initTable()">
                                    <i class="fa fa-search fa-icon"></i>搜索
                                </a>
                            </div>
                            <div class="col-md-1">
                                <a href="javascript:void(0);" class="btn btn-warning"
                                   onclick="$.formRest('main_table_search_form')">
                                    <i class="fa fa-undo fa-icon"></i>重置
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="main-table-div clearfix">
                        <table id="main_table"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--数据模态框-->
<div class="modal fade" id="main_mode" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="modal_title">
                    信息
                </h4>
            </div>

            <div class="modal-body">
                <form class="form-horizontal" role="form" id="main_form">

                    <#if columnSet??>
                        <#list columnSet as field>
                            <div class="form-group">
                                <#if field.remarks??>
                                <label for="${field.columnName}" class="col-sm-4 control-label">${field.remarks}</label>
                                </#if>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="${field.columnName}" name="${field.columnName}">
                                </div>
                            </div>

                        </#list>
                    </#if>

                </form>
            </div>

            <div class="modal-footer">
                <button id="cancel_button" type="button" class="btn btn-default add-show edit-show"
                        data-dismiss="modal">
                    取消
                </button>
                <button id="define_button" type="button" class="btn btn-default detail-show" data-dismiss="modal">
                    确定
                </button>
                <button id="save_button" type="button" class="btn btn-primary  add-show edit-show" onclick="saveEntity()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>





</body>
<!-- 全局js -->
<script src="/static/vendor/jquery/jquery.js?v=2.2.4"></script>
<script src="/static/vendor/bootstrap/js/bootstrap.js?v=3.3.7"></script>
<script src="/static/vendor/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/static/vendor/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/static/vendor/jquery-toast-plugin/jquery.toast.min.js"></script>
<script src="/static/vendor/admin/js/common.js?v=1.0.0"></script>
<script src="/static/js/${module}/manager.js"></script>
</html>
