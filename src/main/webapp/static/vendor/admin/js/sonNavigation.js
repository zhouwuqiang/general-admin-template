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
 * 自己请求关闭
 * @param self
 */
function selfCloseTab() {
    let closeTabItem = {};
    let closeId = self.frameElement.getAttribute('id');
    closeTabItem.tabId = closeId.substring(7, closeId.length);
    window.top.menu.closeTab(closeTabItem)
}


/**
 * 打开新的table
 * @param self
 */
function openTab(tabId,tabUrl,tabTitle,tabParam) {

    let tabItem = {};
    tabItem.tabId = tabId;
    tabItem.tabUrl = tabUrl;
    tabItem.tabParam = tabParam;
    tabItem.tabTitle = tabTitle;

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
}

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
            tabItem.tabSelfClose = this.dataset.tab_self_close;

            if ($.isNull(tabItem.tabId)
                || $.isNull(tabItem.tabUrl)
                || $.isNull(tabItem.tabTitle)) {
                console.log("tab参数不全,请检查数据");
                return false;
            }

            if (!window.top.menu.existsTab(tabItem)) {
                window.top.menu.addTab(tabItem);
            } else {
                // window.top.menu.refreshTab(tabItem);
            }

            //需要关闭当前打开
            if ("01" === tabItem.tabSelfClose && window.top.menu.isNotMobile()) {
                let closeTabItem = {};
                let closeId = self.frameElement.getAttribute('id');
                closeTabItem.tabId = closeId.substring(7, closeId.length);
                window.top.menu.closeTab(closeTabItem)
            }

        });
    }
};


