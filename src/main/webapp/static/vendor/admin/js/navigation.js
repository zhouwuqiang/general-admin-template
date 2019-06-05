/*!
  * v1.0.0
  */
$(function () {

    //移动端样式调整
    if ($.isNotMobile()){
        var height = window.document.body.clientHeight - 90;
        $("#iframe_welcome").height(height + "px");
    }else{
        var height = window.document.body.clientHeight - 50;
        $("#iframe_welcome").height(height + "px");
    }



    // 手风琴导航
    $(window.document).on("click", ".nav-item a", function () {
        if ($.isNotMobile()){
            if (!$('.nav-wrapper').hasClass('nav-mini')) {
                if ($(this).hasClass("toTab")) {
                    if ($(this).parent().parent().hasClass("menu")) {
                        $(this).parent().parent().find('ul').hide()
                    } else {
                        return;
                    }
                }
                if ($(this).next().css('display') === "none") {
                    $(this).parent().parent().find('ul').hide()
                    $(this).parent().parent().find('ul').removeClass("nav-active");
                    $(this).parent().parent().find('.nav-more-icon').removeClass('expand-more');

                    $(this).next('ul').show();
                    $(this).next('ul').addClass("nav-active");
                    $(this).children(".nav-more-icon").addClass('expand-more');
                } else {
                    $(this).next('ul').hide();
                    $(this).parent().parent().find('ul').removeClass("nav-active");
                    $(this).children(".nav-more-icon").removeClass('expand-more');
                }
            }
        }else{
            if ($('.nav-wrapper').hasClass('nav-mini')) {
                if ($(this).hasClass("toTab")) {
                    if ($(this).parent().parent().hasClass("menu")) {
                        $(this).parent().parent().find('ul').hide()
                    } else {
                        return;
                    }
                }
                if ($(this).next().css('display') === "none") {
                    $(this).parent().parent().find('ul').hide()
                    $(this).parent().parent().find('ul').removeClass("nav-active");
                    $(this).parent().parent().find('.nav-more-icon').removeClass('expand-more');

                    $(this).next('ul').show();
                    $(this).next('ul').addClass("nav-active");
                    $(this).children(".nav-more-icon").addClass('expand-more');
                } else {
                    $(this).next('ul').hide();
                    $(this).parent().parent().find('ul').removeClass("nav-active");
                    $(this).children(".nav-more-icon").removeClass('expand-more');
                }
            }
        }

    });

    //nav-mini切换
    $(window.document).on("click", ".mini-button", function () {

        if (!$('.nav-wrapper').hasClass('nav-mini')) {
            $('.menu').find('ul').hide();
            $('.menu').find('ul').removeClass('nav-active');
            $('.menu').find('ul').removeAttr('style');
            $('.menu').find('.nav-more-icon').removeClass('expand-more');

            $('.nav-wrapper').addClass('nav-mini');
            $('.head-wrapper').addClass('nav-mini');
            $('.page-wrapper').addClass('nav-mini');
        } else {
            $('.nav-wrapper').removeClass('nav-mini');
            $('.head-wrapper').removeClass('nav-mini');
            $('.page-wrapper').removeClass('nav-mini');
        }

    });


    //创建菜单
    var menuObj = new menu();
    menuObj.menuInit();
});


var menu = function () {

    this.menuInit = function () {
        window.menu = this;
        var _self = this;
        //绑定创建菜单事件
        $(window.document).on("click", ".toTab", function () {
            var tabItem = {};
            tabItem.tabId = this.dataset.tab_id;
            tabItem.tabUrl = this.dataset.tab_url;
            tabItem.tabParam = this.dataset.tab_param;
            tabItem.tabTitle = this.dataset.tab_title;

            if ($.isNull(tabItem.tabId)
                || $.isNull(tabItem.tabUrl)
                || $.isNull(tabItem.tabParam)
                || $.isNull(tabItem.tabTitle)) {
                console.log("tab参数不全,请检查数据");
                return false;
            }


            if (_self.isNotMobile()) {
                if (!_self.existsTab(tabItem)) {
                    _self.addTab(tabItem);
                } else {
                    _self.refreshTab(tabItem);
                }
            } else {
                //移动端只能打开一个页面
                $("#iframe_welcome").attr('src', tabItem.tabUrl);
                $(".mini-button").trigger("click");
            }

        });

        //绑定激活tab事件
        $(window.document).on("click", ".tab-item", function () {
            var tabItem = {};
            tabItem.tabId = this.dataset.tab_id;
            tabItem.tabUrl = this.dataset.tab_url;
            tabItem.tabParam = this.dataset.tab_param;
            tabItem.tabTitle = this.dataset.tab_title;
            _self.activeTab(tabItem);
        });

        //绑定关闭tab事件
        $(window.document).on("click", ".tab-item .tab-close", function (e) {
            e.stopPropagation();

            var tabItem = {};
            tabItem.tabId = this.parentNode.dataset.tab_id;
            tabItem.tabUrl = this.parentNode.dataset.tab_url;
            tabItem.tabParam = this.parentNode.dataset.tab_param;
            tabItem.tabTitle = this.parentNode.dataset.tab_title;
            _self.closeTab(tabItem);
        });

        //绑定左移事件
        $(window.document).on("click", ".left-button", function () {

            var tabArray = $(".head-tabs-menu").find(".tab-item");

            //现有tabs总长度
            var headTabsMenu = 0;
            for (var i = 0; i < tabArray.length; i++) {
                headTabsMenu = headTabsMenu + $(tabArray[i]).width() + 20
            }

            //tabsDiv 长度
            var headTabsDiv = $(".head-tabs-div").width();

            //不需要移动直接返回
            if (headTabsMenu <= headTabsDiv) {
                return 0;
            }

            // 左边距
            var oldLeft = Number($(".head-tabs-menu").css("left").replace("px", ""));

            //如果oldLeft>0 不可以在向右移动
            if (oldLeft > 0) {
                return 0;
            }

            //可以移动的位置
            var moveSize = headTabsMenu - headTabsDiv;


            console.log("tab容器长度:" + headTabsDiv + " 现有tab长度:" + headTabsMenu + " 可有移动长度:" + moveSize + "   偏移长度" + oldLeft);

            var left = 0;
            if (oldLeft + 500 < 0) {
                left = oldLeft + 500;
            }

            $(".head-tabs-menu").css("left", left + "px");

        });

        //绑定右移事件
        $(window.document).on("click", ".right-button", function () {

            var tabArray = $(".head-tabs-menu").find(".tab-item");

            //现有tabs总长度
            var headTabsMenu = 0;
            for (var i = 0; i < tabArray.length; i++) {
                headTabsMenu = headTabsMenu + $(tabArray[i]).width() + 20
            }

            //tabsDiv 长度
            var headTabsDiv = $(".head-tabs-div").width();

            //不需要移动直接返回
            if (headTabsMenu <= headTabsDiv) {
                return 0;
            }

            // 左边距
            var oldLeft = Number($(".head-tabs-menu").css("left").replace("px", ""));

            //可以移动的位置
            var moveSize = headTabsMenu - headTabsDiv;

            //如果oldLeft>-moveSize 不可以在向右移动
            if (oldLeft < -moveSize) {
                return 0;
            }

            console.log("tab容器长度:" + headTabsDiv + " 现有tab长度:" + headTabsMenu + " 可有移动长度:" + moveSize + "   偏移长度" + oldLeft);

            var left = -moveSize;
            if (oldLeft - 500 > -moveSize) {
                left = oldLeft - 500;
            }

            $(".head-tabs-menu").css("left", left + "px");

        });

        //tabsDiv 鼠标右击事件
        this.menu();

    },

        this.existsTab = function (tabItem) {
            var _self = this;
            console.log("判断tab是否存在" + JSON.stringify(tabItem));
            var objTab = $(".head-tabs-menu").find("#title_" + tabItem.tabId);
            if (objTab.length > 0) {
                //如果存在参数需要页面重新打开
                if (JSON.stringify(tabItem.tabParam.length) === '{}'){
                    _self.closeTab(tabItem);
                    return false;
                }else{
                    return true;
                }
            } else {
                return false;
            }
        },
        this.addTab = function (tabItem) {
            var _self = this;

            $(window.document).find(".tab-item").removeClass("tab-active");
            $(window.document).find(".page-item").removeClass("page-active");


            var titleHtml = "<div class='tab-item tab-active head-nav-hover' id='title_" + tabItem.tabId + "'data-tab_id='" + tabItem.tabId + "' " +
                "                    data-tab_url='" + tabItem.tabUrl + "' data-tab_param='" + tabItem.tabParam + "' data-tab_title='" + tabItem.tabTitle + "'  >" +
                "               <span class='tab-title'>" + tabItem.tabTitle + "</span>" +
                "               <i class='tab-close fa fa-times'></i>" +
                "           </div>";
            $(window.document).find(".head-tabs-menu").append(titleHtml);

            var paramUrl = tabItem.tabUrl + "?";

            if ($.isNotNull(tabItem.tabParam)) {
                var params =JSON.parse(tabItem.tabParam);
                for (var key in params) {
                    paramUrl = paramUrl + key + "=" + params[key] + "&";
                }
                paramUrl = paramUrl.substr(0, paramUrl.length - 1);
            }

            var bodyHtml = "<div class='page-item page-active' id='body_" + tabItem.tabId + "' data-tab_id='" + tabItem.tabId + "' data-tab_url='" + tabItem.tabUrl +
                "                           ' data-tab_param='" + tabItem.tabParam + "' data-tab_title='" + tabItem.tabTitle + "'>" +
                "               <iframe id='iframe_" + tabItem.tabId + "' data-tab_id='" + tabItem.tabId + "' frameborder='0' src='" + paramUrl + "'" +
                " style='width:100%;height:" + (window.document.body.clientHeight - 90) + "px' onscroll='scroll'></iframe>" +
                "           </div>";
            $(window.document).find(".page-wrapper").append(bodyHtml);

            _self.showAddTab();

            console.log("添加tab" + JSON.stringify(tabItem));
        },
        this.closeTab = function (tabItem) {
            var _self = this;
            console.log("关闭tab" + JSON.stringify(tabItem));

            if (!$("#title_" + tabItem.tabId).hasClass("tab-active")) {
                $(window.document).find("#title_" + tabItem.tabId).remove();
                $(window.document).find("#body_" + tabItem.tabId).remove();
            } else {
                var before = $(window.document).find("#title_" + tabItem.tabId).prev();
                var next = $(window.document).find("#title_" + tabItem.tabId).next();
                if ($.isNotNull(before) && before.hasClass("tab-item")) {
                    var beforeTabItem = {};
                    beforeTabItem.tabId = before[0].dataset.tab_id;
                    beforeTabItem.tabUrl = before[0].dataset.tab_url;
                    beforeTabItem.tabParam = before[0].dataset.tab_param;
                    beforeTabItem.tabTitle = before[0].dataset.tab_title;
                    _self.activeTab(beforeTabItem);
                } else if ($.isNotNull(next) && next.hasClass("tab-item")) {
                    var nextTabItem = {};
                    nextTabItem.tabId = next[0].dataset.tab_id;
                    nextTabItem.tabUrl = next[0].dataset.tab_url;
                    nextTabItem.tabParam = next[0].dataset.tab_param;
                    nextTabItem.tabTitle = next[0].dataset.tab_title;
                    _self.activeTab(nextTabItem);
                } else {
                    $("#body_welcome").addClass("page-active");
                    $("#iframe_welcome").attr('src', $("#iframe_welcome").attr('src'));
                }

                $(window.document).find("#title_" + tabItem.tabId).remove();
                $(window.document).find("#body_" + tabItem.tabId).remove();
            }
            _self.showActiveTab();
        },
        this.refreshTab = function (tabItem) {
            var _self = this;
            console.log("刷新tab" + JSON.stringify(tabItem));
            $('#iframe_' + tabItem.tabId).attr('src', $('#iframe_' + tabItem.tabId).attr('src'));
            _self.activeTab(tabItem);
        },
        this.activeTab = function (tabItem) {
            var _self = this;

            $(window.document).find(".tab-item").removeClass("tab-active");
            $(window.document).find(".page-item").removeClass("page-active");

            $(window.document).find("#title_" + tabItem.tabId).addClass("tab-active");
            $(window.document).find("#body_" + tabItem.tabId).addClass("page-active");

            _self.showActiveTab(tabItem);
            console.log("激活tab" + JSON.stringify(tabItem));
        },
        this.showActiveTab = function () {

            //调整tab位置 将激活的tab调整显示出来
            var tabArray = $(window.document).find(".head-tabs-menu").find(".tab-item");
            //tabs总长度
            var headTabsMenu = 0;
            //到激活位置的长度
            var activeTabsMenu = 0;
            for (var i = 0; i < tabArray.length; i++) {
                if ($(tabArray[i]).hasClass("tab-active")) {
                    activeTabsMenu = headTabsMenu
                }
                headTabsMenu = headTabsMenu + $(tabArray[i]).width() + 20
            }
            //tabs div长度
            var headTabsDiv = $(window.document).find(".head-tabs-div").width();

            // 左边距
            var oldLeft = Number($(window.document).find(".head-tabs-menu").css("left").replace("px", ""));


            //当前active 位置
            var nowIndex = activeTabsMenu + oldLeft;

            //当前位置超过容器位置 显示在容器外面 如果小于0 也显示在容器外面
            if (nowIndex > headTabsDiv || nowIndex <= 0) {
                var left = 0;
                if (activeTabsMenu - 500 > 0) {
                    left = activeTabsMenu - 500;
                }
                $(window.document).find(".head-tabs-menu").css("left", -left + "px");
            }

        },
        this.showAddTab = function () {
            //调整新增tab位置
            var tabArray = $(".head-tabs-menu").find(".tab-item");
            var headTabsMenu = 0;
            for (var i = 0; i < tabArray.length; i++) {
                headTabsMenu = headTabsMenu + $(tabArray[i]).width() + 20
            }
            var headTabsDiv = $(".head-tabs-div").width();
            if (headTabsMenu <= headTabsDiv) {
                return 0;
            }
            var moveSize = headTabsMenu - headTabsDiv;
            $(".head-tabs-menu").css("left", -moveSize + "px");

        },
        this.menu = function () {
            var html = "<div class='contextMenu' id='context_menu' style='display:none;'>" +
                "  <ul>" +
                "    <li id='refreshActiveTab'> 刷新当前窗口</li>" +
                "    <li id='closeThisTab'> 关闭当前窗口</li>" +
                "    <li id='closeOtherTab'> 关闭其他窗口</li>" +
                "    <li id='closeAllTab'> 关闭所有窗口</li>" +
                "  </ul>" +
                "</div>";

            if ($("#context_menu").length <= 0) {
                $("body").append(html);
            }

            $('.head-tabs-menu').contextMenu('context_menu', {
                // //菜单样式
                menuStyle: {
                    width: 110
                },
                // //菜单项样式
                // itemStyle: {
                //     fontFamily: 'verdana',
                //     backgroundColor: 'white',
                //     color: 'black',
                //     border: 'none',
                //     padding: '1px'
                // },
                // //菜单项鼠标放在上面样式
                // itemHoverStyle: {
                //     color: 'blue',
                //     backgroundColor: 'red',
                //     border: 'none'
                // },
                //事件
                bindings:
                    {
                        'refreshActiveTab': function (t) {
                            window.menu.refreshActiveTab();
                        },
                        'closeThisTab': function (t) {
                            window.menu.closeThisTab();
                        },
                        'closeOtherTab': function (t) {
                            window.menu.closeOtherTab();
                        },
                        'closeAllTab': function (t) {
                            window.menu.closeAllTab();
                        }
                    }
            });
        },
        this.isPC = function () {
            var flag = true;
            if ((navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i))) {
                flag = false;
            }
            return flag;
        },
        this.isNotMobile = function () {
            var flag = true;
            if (window.top.document.body.clientWidth < 769) {
                flag = false;
            }
            return flag;
        },
        this.closeThisTab = function () {
            var tabArray = $(".head-tabs-menu").find(".tab-item");
            for (var i = 0; i < tabArray.length; i++) {
                if ($(tabArray[i]).hasClass("tab-active")) {
                    $(tabArray[i]).children(".tab-close").trigger("click");
                }
            }
        },
        this.closeOtherTab = function () {
            var tabArray = $(".head-tabs-menu").find(".tab-item");
            for (var i = 0; i < tabArray.length; i++) {
                if (!$(tabArray[i]).hasClass("tab-active")) {
                    var id = tabArray[i].dataset.tab_id;
                    $(window.document).find("#title_" + id).remove();
                    $(window.document).find("#body_" + id).remove();
                }
            }
        },
        this.closeAllTab = function () {
            var tabArray = $(".head-tabs-menu").find(".tab-item");
            for (var i = 0; i < tabArray.length; i++) {
                var id = tabArray[i].dataset.tab_id;
                $(window.document).find("#title_" + id).remove();
                $(window.document).find("#body_" + id).remove();
            }

            $("#body_welcome").addClass("page-active");
            $("#iframe_welcome").attr('src', $("#iframe_welcome").attr('src'));

        },
        this.refreshActiveTab = function () {
            var tabArray = $(".head-tabs-menu").find(".tab-item");
            for (var i = 0; i < tabArray.length; i++) {
                if ($(tabArray[i]).hasClass("tab-active")) {
                    var id = tabArray[i].dataset.tab_id;
                    $('#iframe_' + id).attr('src', $('#iframe_' + id).attr('src'));
                }
            }
        }
};
