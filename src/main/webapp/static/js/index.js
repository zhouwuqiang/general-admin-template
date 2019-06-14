$(function () {

    $(".full-screen-button").click(function (e) {
        $(".wrapper").fullScreen();
        e.preventDefault();
    });

    getMenu();
});

/**
 * 获取菜单
 */
function getMenu() {
    $.ajax({
        url: "/menu/list",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (responseDto) {
            console.log(JSON.stringify(responseDto));
            var html = "";
            if (responseDto.success) {
                for (var i = 0; i < responseDto.data.length; i++) {
                    var item = responseDto.data[i];
                    html += buildMenu(item);
                }
                console.log(html);
                $(".menu").append(html);
            }
        },
        error: function () {
            console.log("请求处理失败!");
        }
    });
}

/**
 * 构建菜单
 */
function buildMenu(item) {
    var html = "";

    if (item.isDisplay === "01") {
        return html;
    }

    if (item.hasChild) {
        html = html +
            "<li class='nav-item'>" +
            "    <a href='javascript:;' data-tab_id='" + item.menuCode + "' data-tab_url='" + item.menuAction + "' data-tab_param='{}' data-tab_title='" + item.menuName + "'>" +
            "        <i class='nav-icon fa " + item.menuIcon + "'></i>" +
            "        <span>" + item.menuName + "</span>" +
            "        <i class='nav-more-icon fa fa-angle-right'></i>" +
            "    </a>" +
            "    <ul>";

        for (var i = 0; i < item.nodes.length; i++) {
            html += buildMenu(item.nodes[i]);
        }

        html = html +
            "    </ul>" +
            "</li>";

    } else {
        html = html +
            "<li class='nav-item'>" +
            "    <a class='toTab' href='javascript:;' data-tab_id='" + item.menuCode + "' data-tab_url='" + item.menuAction + "' data-tab_param='{}' data-tab_title='" + item.menuName + "'>" +
            "        <i class='nav-icon fa " + item.menuIcon + "'></i>" +
            "        <span>" + item.menuName + "</span>" +
            "        <i class='nav-more-icon fa'></i>" +
            "    </a>" +
            "</li>";
    }
    return html;
}

