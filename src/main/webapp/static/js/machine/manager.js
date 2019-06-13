$(function () {

    initMachineInfo();

    initLoanChart();

    setInterval(function () {
        Machine.socket.send("machine");
    }, 1000);

    initMemoryInfo();

    initRuntimeInfo();
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
                $.formReview("machine_info_form", responseDto.data);
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
 * 初始负载情况
 */
function initLoanChart() {
    let cpuDom = document.getElementById("system_cpu_load_chart");
    window.echarts.init(cpuDom).setOption(getLoanChartOptin("CPU使用率", 0), true);

    let memoryDom = document.getElementById("system_memory_load_chart");
    window.echarts.init(memoryDom).setOption(getLoanChartOptin("内存使用率", 0), true);
}

/**
 * 更新负载情况
 */
function updateLoanChart(responseDto) {

    let cpuValue = (responseDto.systemCpuLoad * 100).toFixed(2);
    let memoryValue = ((1 - (responseDto.freePhysicalMemorySize / responseDto.totalPhysicalMemory)) * 100).toFixed(2);

    echarts.getInstanceById(document.getElementById("system_cpu_load_chart").getAttribute('_echarts_instance_'))
        .setOption(getLoanChartOptin("CPU使用率", cpuValue), true);

    echarts.getInstanceById(document.getElementById("system_memory_load_chart").getAttribute('_echarts_instance_'))
        .setOption(getLoanChartOptin("内存使用率", memoryValue), true);
}

/**
 * 获取负载图标参数
 * @param name
 * @param value
 * @returns {{series: {data: {name: *, value: *}[], axisLine: {lineStyle: {width: number}}, name: *, axisTick: {lineStyle: {color: string}, length: number}, splitLine: {lineStyle: {color: string}, length: number}, detail: {formatter: string}, type: string}[], tooltip: {formatter: string}}}
 */
function getLoanChartOptin(name, value) {
    let option = {
        tooltip: {
            formatter: "{a} <br/>{b} : {c}%"
        },
        series: [
            {
                name: name,
                type: 'gauge',
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        width: 10
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length: 15,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    length: 20,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                detail: {formatter: '{value}%'},
                data: [{value: value, name: name}]
            }
        ]
    };
    return option;
}

/**
 * 初始化cpu使用率
 */
function initCpuLoanChart() {
    let dom = document.getElementById("system_cpu_load_chart");
    let loanChart = window.echarts.init(dom);
    let option = {
        tooltip: {
            formatter: "{a} <br/>{b} : {c}%"
        },
        series: [
            {
                name: 'CPU使用率',
                type: 'gauge',
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        width: 10
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length: 15,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    length: 20,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                detail: {formatter: '{value}%'},
                data: [{value: 0, name: 'CPU使用率'}]
            }
        ]
    };
    loanChart.setOption(option, true);

    // setInterval(function () {
    //     option.series[0].data[0].value = (Math.random() * 100).toFixed(2) - 0;
    //     loanChart.setOption(option, true);
    // },2000);

}

/**
 * 初始化内存使用率
 */
function initMemoryLoanChart(value) {
    let dom = document.getElementById("system_memory_load_chart");
    let loanChart = window.echarts.init(dom);
    let option = {
        tooltip: {
            formatter: "{a} <br/>{b} : {c}%"
        },
        series: [
            {
                name: '内存使用率',
                type: 'gauge',
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        width: 10
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length: 15,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    length: 20,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                detail: {formatter: '{value}%'},
                data: [{value: value, name: '内存使用率'}]
            }
        ]
    };
    loanChart.setOption(option, true);

    // setInterval(function () {
    //     option.series[0].data[0].value = (Math.random() * 100).toFixed(2) - 0;
    //     loanChart.setOption(option, true);
    // },2000);

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
                $.formReview("heap_memory_info_form", responseDto.data.jvmHeapMemory);
                $.formReadOnly("heap_memory_info_form");
                $.formReview("non_heap_memory_info_form", responseDto.data.jvmNonHeapMemory);
                $.formReadOnly("non_heap_memory_info_form");
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });
}

/**
 * 初始运行信息
 */
function initRuntimeInfo() {
    $.ajax({
        url: "/machine/runtime/Info",
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (responseDto) {
            if (responseDto.success) {
                $.formReview("runtime_info_form", responseDto.data);
                $.formReadOnly("runtime_info_form");
            }
        },
        error: function () {
            console.log("请求处理失败!");
            $.errorMassage("请求处理失败!");
        }
    });
}
