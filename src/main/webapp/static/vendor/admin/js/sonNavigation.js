/*!
  * v1.0.0
  * iframe 中引用 打开tab
  */
$(function () {

    //创建菜单
    let menuObj = new menu();
    menuObj.menuInit();
});

/**
 * 菜单
 */
let menu = function () {
    this.menuInit = function () {
        $(window.document).on("click", ".toTab", function () {
            let tabItem = {};
            tabItem.tabId = this.dataset.tab_id;
            tabItem.tabUrl = this.dataset.tab_url;
            tabItem.tabParam = this.dataset.tab_param;
            tabItem.tabTitle = this.dataset.tab_title;

            if ($.isNull(tabItem.tabId)
                || $.isNull(tabItem.tabUrl)
                || $.isNull(tabItem.tabTitle)) {
                console.log("tab参数不全,请检查数据");
                return false;
            }

            if (!window.top.menu.existsTab(tabItem)) {
                window.top.menu.addTab(tabItem);
            } else {
                window.top.menu.refreshTab(tabItem);
            }
        });
    }
};


