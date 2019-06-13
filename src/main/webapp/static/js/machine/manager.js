$(function () {

    initMachineInfo();

    initRuntimeInfo();

    initLoanChart();
    initMemoryChart();
    initThreadChart();

    setInterval(function () {
        Machine.socket.send("start");
    }, 1000);
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


/********************************************** 机器负载图 *****************************************/

/**
 * 初始负载情况
 */
function initLoanChart() {
    let cpuDom = document.getElementById("system_cpu_load_chart");
    window.echarts.init(cpuDom).setOption(getLoanChartOperation("CPU使用率", 0), true);

    let memoryDom = document.getElementById("system_memory_load_chart");
    window.echarts.init(memoryDom).setOption(getLoanChartOperation("内存使用率", 0), true);
}

/**
 * 更新负载情况
 */
function updateLoanChart(responseDto) {

    let cpuValue = (responseDto.systemCpuLoad * 100).toFixed(2);
    let memoryValue = ((1 - (responseDto.freePhysicalMemorySize / responseDto.totalPhysicalMemory)) * 100).toFixed(2);

    echarts.getInstanceById(document.getElementById("system_cpu_load_chart").getAttribute('_echarts_instance_'))
        .setOption(getLoanChartOperation("CPU使用率", cpuValue), true);

    echarts.getInstanceById(document.getElementById("system_memory_load_chart").getAttribute('_echarts_instance_'))
        .setOption(getLoanChartOperation("内存使用率", memoryValue), true);
}

/**
 * 获取负载图标参数
 * @param name
 * @param value
 * @returns {{series: {data: {name: *, value: *}[], axisLine: {lineStyle: {width: number}}, name: *, axisTick: {lineStyle: {color: string}, length: number}, splitLine: {lineStyle: {color: string}, length: number}, detail: {formatter: string}, type: string}[], tooltip: {formatter: string}}}
 */
function getLoanChartOperation(name, value) {
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

/********************************************** jvm 运行信息 *****************************************/
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

/********************************************** jvm内存图 *****************************************/

/**
 * 获取内存图标配置数据
 * @param name
 * @param date
 * @param usedData
 * @param committedData
 * @returns {{yAxis: {type: string}, xAxis: {data: *, splitLine: {show: boolean}, type: string}, series: *[], tooltip: {axisPointer: {label: {backgroundColor: string}, type: string}, trigger: string}, title: {text: string}}}
 */
function getMemoryChartOperation(name, date, usedData, committedData) {

    let option = {
        title: {
            text: name
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        xAxis: {
            type: 'category',
            splitLine: {
                show: false
            },
            data: date
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name: '当前(已使用)(M)',
                type: 'line',
                areaStyle: {},
                data: usedData
            },
            {
                name: '提交的内存(已申请)(M)',
                type: 'line',
                areaStyle: {color: 'rgb(252, 232, 205)'},
                data: committedData
            }]
    };
    return option;
}

let date = [];
let heapUsed = [];
let heapCommitted = [];
let nonHeapUsed = [];
let nonHeapCommitted = [];

/**
 * 初始jvmMemory情况
 */
function initMemoryChart() {

    for (let i = 0; i < 600; i++) {
        date.push('');
        heapUsed.push('');
        heapCommitted.push('');
        nonHeapUsed.push('');
        nonHeapCommitted.push('');
    }

    let heapDom = document.getElementById("heap_memory_chart");
    window.echarts.init(heapDom).setOption(getMemoryChartOperation("堆", date, heapUsed, heapCommitted), true);

    let nonHeapDom = document.getElementById("non_heap_memory_chart");
    window.echarts.init(nonHeapDom).setOption(getMemoryChartOperation("metaspace", date, nonHeapUsed, nonHeapCommitted), true);
}


/**
 * 更新jvmMemory情况
 */
function updateMemoryChart(responseDto) {

    let heap = responseDto.jvmHeapMemory;
    let nonheap = responseDto.jvmNonHeapMemory;
    date.shift();
    heapUsed.shift();
    heapCommitted.shift();
    nonHeapUsed.shift();
    nonHeapCommitted.shift();

    let heapMax = "堆(最大:" + heap.max + "MB)";
    let heapUsedItem = randomData(heap.used);
    let committedItem = randomData(heap.committed);

    let nonHeapUsedItem = randomData(nonheap.used);
    let nonHeapCommittedItem = randomData(nonheap.committed);

    date.push(heapUsedItem.value[0]);

    heapUsed.push(heapUsedItem);
    heapCommitted.push(committedItem);
    nonHeapUsed.push(nonHeapUsedItem);
    nonHeapCommitted.push(nonHeapCommittedItem);


    echarts.getInstanceById(document.getElementById("heap_memory_chart").getAttribute('_echarts_instance_'))
        .setOption(getMemoryChartOperation(heapMax, date, heapUsed, heapCommitted), true);

    echarts.getInstanceById(document.getElementById("non_heap_memory_chart").getAttribute('_echarts_instance_'))
        .setOption(getMemoryChartOperation("metaspace", date, nonHeapUsed, nonHeapCommitted), true);
}

/**
 * 格式化数据
 * @param value
 * @returns {{value: any[]}}
 */
function randomData(value) {
    let now = new Date();
    return {
        value: [
            [now.getHours(), now.getMinutes(), now.getSeconds()].join(':'),
            Math.round(value)
        ]
    }
}

/********************************************** jvm线程图 *****************************************/

function initThreadChart() {

}
