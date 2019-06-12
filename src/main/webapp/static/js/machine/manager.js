$(function () {

    initMachineInfo();

    initMemoryInfo();
});

/**
 * 初始化机器信息
 */
function initMachineInfo() {
    $.ajax({
        url: "/machine/info",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (responseDto) {
            if (responseDto.success) {
               $.formReview("machine_info_form",responseDto.data);
               $.formReadOnly("machine_info_form");
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });
}

/**
 * 初始内存信息
 */
function initMemoryInfo() {
    $.ajax({
        url: "/machine/memory",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (responseDto) {
            if (responseDto.success) {
                $.formReview("heap_memory_info_form",responseDto.data.jvmHeapMemory);
                $.formReadOnly("heap_memory_info_form");
                $.formReview("non_heap_memory_info_form",responseDto.data.jvmNonHeapMemory);
                $.formReadOnly("non_heap_memory_info_form");
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });
}
