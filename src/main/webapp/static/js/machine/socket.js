$(function () {

    window.Machine = {};

//判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        Machine.socket = new WebSocket("ws://" + path + "/machine/socket");
    } else {
        $.errorMassage("浏览器不支持websocket");
    }

//连接发生错误的回调方法
    Machine.socket.onerror = function () {
        console.log("websocket error");
    };

//连接成功建立的回调方法
    Machine.socket.onopen = function (event) {
        console.log("websocket open");
    };

//接收到消息的回调方法
    Machine.socket.onmessage = function (event) {
        let data = JSON.parse(event.data);
        updateLoanChart(data.machineInfo);
        updateMemoryChart(data.memoryInfo);
        updateThreadChart(data.threadDto);
        updateRuntimeInfo(data.runtimeInfo);
    };

//连接关闭的回调方法
    Machine.socket.onclose = function () {
        console.log("websocket close");
    };


//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        Machine.socket.close();
    };

});


/**
 * 关闭连接
 */
function closeWebSocket() {
    Machine.socket.close();
}

/**
 * 发送消息
 */
function send() {
    Machine.socket.send("测试数据");
}
