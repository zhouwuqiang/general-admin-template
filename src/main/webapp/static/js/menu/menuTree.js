/**
 * 初始化权限菜单 roleCode  organizationCode 二选一
 */
function initAuthorizationTree(treeId, param) {
    let $treeId = $('#' + treeId);
    $treeId.treeview('remove').treeview({
        data: getTree(param),
        showCheckbox: true,
        hierarchicalCheck: true,
        showTags: true,
        onNodeChecked: function (event, node) { //选中节点
            let selectNodes = getChildNodeIdArr(node); //获取所有子节点
            if (selectNodes) { //子节点不为空，则选中所有子节点
                $treeId.treeview('checkNode', [selectNodes, {silent: true}]);
            }
            setParentNodeCheckOnly(node, $treeId);
        },
        onNodeUnchecked: function (event, node) { //取消选中节点
            let selectNodes = getChildNodeIdArr(node); //获取所有子节点
            if (selectNodes) { //子节点不为空，则取消选中所有子节点
                $treeId.treeview('uncheckNode', [selectNodes, {silent: true}]);
            }
        },
    });
}


/**
 * 获取数据
 * @returns {Array}
 */
function getTree(param) {

    let menuList = [];

    $.ajax({
        url: "/menu/list/tree",
        type: 'post',
        dataType: 'json',
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(param),
        success: function (responseDto) {
            if (responseDto.success) {
                menuList = responseDto.data;
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });

    return menuList;
}

/**
 * 获取所有字节点
 * @param node
 * @returns {Array}
 */
function getChildNodeIdArr(node) {
    let ts = [];
    if (node.nodes) {
        for (let x in node.nodes) {
            ts.push(node.nodes[x].nodeId);
            if (node.nodes[x].nodes) {
                let getNodeDieDai = getChildNodeIdArr(node.nodes[x]);
                for (j in getNodeDieDai) {
                    ts.push(getNodeDieDai[j]);
                }
            }
        }
    } else {
        ts.push(node.nodeId);
    }
    return ts;
}

/**
 * 设置父节点选中 子节点都选中,父节点自动选中
 * @param node
 */
function setParentNodeCheck(node) {
    let parentNode = $("#menu_tree").treeview("getNode", node.parentId);
    if (parentNode.nodes) {
        let checkedCount = 0;
        for (let x in parentNode.nodes) {
            if (parentNode.nodes[x].state.checked) {
                checkedCount++;
            } else {
                break;
            }
        }
        if (checkedCount === parentNode.nodes.length) {
            $("#menu_tree").treeview("checkNode", parentNode.nodeId);
            setParentNodeCheck(parentNode);
        }
    }
}

/**
 * 设置父节点选中 子节点选中一个 ,父节点选中
 * @param node
 */
function setParentNodeCheckOnly(node, $treeId) {
    let parentNode = $treeId.treeview("getNode", node.parentId);
    if (parentNode.nodes) {
        let checkedCount = 0;
        for (let x in parentNode.nodes) {
            if (parentNode.nodes[x].state.checked) {
                checkedCount++;
            } else {
                break;
            }
        }
        if (checkedCount > 0) {
            $treeId.treeview("checkNode", [parentNode.nodeId, {silent: true}]);
            setParentNodeCheck(parentNode, $treeId);
        }
    }
}
