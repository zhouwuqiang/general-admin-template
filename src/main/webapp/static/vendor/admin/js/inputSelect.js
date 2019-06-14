let inputSelectFactory = function ($) {

    //默认配置
    let defaultConfig = {
        enable: true,
        getData: function () {
            return [];
        },
        onNodeSelected: function (event, node) {
            alert(JSON.stringify(node));
        },
        onNodeUnselected: function (event, node) {
            alert(JSON.stringify(node));
        }
    };

    //绑定方法
    $.fn.initTreeSelect = function (config) {

        let $this = $(this);

        let option = Object.assign({}, defaultConfig, config);

        let $div = {};

        /**
         * 创建文件对象
         */
        let createDom = function () {
            $div = $(document.createElement("div"));
            $div.addClass("input-select");
            $div.addClass("container-fluid");
            $this.after($div[0]);
        };

        /**
         * 显示div
         */
        let showDom = function () {
            createDom();
            initTree();
            clickOtherToHide();
        };

        /**
         * 隐藏div
         */
        let hideDom = function () {
            $div.remove();
        };

        /**
         * 绑定事件
         */
        let bindEvents = function () {
            if ($this.attr("readonly")) {
                //不做任何操作
            } else {
                showDom();
            }
        };

        let proxyOnNodeSelected = function (event, node) {
            option.onNodeSelected(event, node);
            hideDom();
        };

        let initTree = function () {
            $div.treeview({
                data: option.getData,
                onNodeSelected: proxyOnNodeSelected,
                onNodeUnselected: option.onNodeUnselected
            });
        };

        /**
         * 点击其他地方关闭
         */
        let clickOtherToHide = function () {
            $(document).one("click", function (e) {
                hideDom();
            });
            $this.parent().click(function () {
                event.stopPropagation();
            });
        };

        $this.focus(bindEvents);

    };
};

/**
 * 只调用函数
 */
(function (factory) {
    factory(jQuery);
})(inputSelectFactory);
