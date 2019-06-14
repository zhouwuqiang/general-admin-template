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
                areaStyle: {color: 'rgb(109, 124, 135)'},
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
let initEmpty = 600;

/**
 * 初始jvmMemory情况
 */
function initMemoryChart() {

    for (let i = 0; i < initEmpty; i++) {
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
    let nonHeap = responseDto.jvmNonHeapMemory;
    if (initEmpty > 0) {
        date.shift();
        heapUsed.shift();
        heapCommitted.shift();
        nonHeapUsed.shift();
        nonHeapCommitted.shift();
        initEmpty--;
    }


    let heapMax = "堆(最大:" + heap.max + "MB)";
    let heapUsedItem = formatData(heap.used);
    let committedItem = formatData(heap.committed);

    let nonHeapUsedItem = formatData(nonHeap.used);
    let nonHeapCommittedItem = formatData(nonHeap.committed);

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
function formatData(value) {
    let now = new Date();
    return {
        value: [
            [now.getHours(), now.getMinutes(), now.getSeconds()].join(':'),
            Math.round(value)
        ]
    }
}

/********************************************** jvm线程图 *****************************************/
let threadDate = [];
let newData = [];
let runnableData = [];
let blockedData = [];
let waitingData = [];
let timedWaitingData = [];
let terminatedData = [];
let initThreadEmpty = 600;

/**
 * 初始化线程数据
 */
function initThreadChart() {

    for (let i = 0; i < initThreadEmpty; i++) {
        threadDate.push('');
        newData.push('');
        runnableData.push('');
        blockedData.push('');
        waitingData.push('');
        timedWaitingData.push('');
        terminatedData.push('');
    }

    let dom = document.getElementById("thread_chart");
    window.echarts.init(dom).setOption(getThreadChartOperation(threadDate, newData, runnableData, blockedData, waitingData, timedWaitingData, terminatedData), true);

}


/**
 * 更新线程数据
 */
function updateThreadChart(responseDto) {

    let newDataItem = formatThreadData(responseDto.newData);
    threadDate.push(newDataItem.value[0]);
    newData.push(newDataItem);
    runnableData.push(formatThreadData(responseDto.runnableData));
    blockedData.push(formatThreadData(responseDto.blockedData));
    waitingData.push(formatThreadData(responseDto.waitingData));
    timedWaitingData.push(formatThreadData(responseDto.timedWaitingData));
    terminatedData.push(formatThreadData(responseDto.terminatedData));

    echarts.getInstanceById(document.getElementById("thread_chart").getAttribute('_echarts_instance_'))
        .setOption(getThreadChartOperation(threadDate, newData, runnableData, blockedData, waitingData, timedWaitingData, terminatedData), true);

    $("#totalStartedThreadCount").html(responseDto.totalStartedThreadCount);
    $("#peakThreadCount").html(responseDto.peakThreadCount);
    $("#threadCount").html(responseDto.threadCount);
    $("#daemonThreadCount").html(responseDto.daemonThreadCount);
}

/**
 * 格式化数据
 * @param value
 * @returns {{value: any[]}}
 */
function formatThreadData(value) {
    let now = new Date();
    return {
        value: [
            [now.getHours(), now.getMinutes(), now.getSeconds()].join(':'),
            Math.round(value)
        ]
    }
}

/**
 * 获取线程配置数据
 * @param date
 * @param newData
 * @param runnableData
 * @param blockedData
 * @param waitingData
 * @param timedWaitingData
 * @param terminatedData
 * @returns {{yAxis: {type: string}[], xAxis: {data: *, type: string, boundaryGap: boolean}[], legend: {data: string[]}, grid: {left: string, bottom: string, right: string, containLabel: boolean}, series: *[], tooltip: {axisPointer: {label: {backgroundColor: string}, type: string}, trigger: string}, toolbox: {feature: {saveAsImage: {}}}, title: {text: string}}}
 */
function getThreadChartOperation(date, newData, runnableData, blockedData, waitingData, timedWaitingData, terminatedData) {


    let option = {
        title: {
            text: '线程状态'
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
        legend: {
            data: ['NEW', 'RUNNABLE', 'BLOCKED', 'WAITING', 'TIMED_WAITING', 'TERMINATED']
        },
        toolbox: {
            // feature: {
            //     saveAsImage: {}
            // }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: date
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: 'NEW',
                type: 'line',
                stack: '总量',
                areaStyle: {},
                data: newData
            },
            {
                name: 'RUNNABLE',
                type: 'line',
                stack: '总量',
                areaStyle: {},
                data: runnableData
            },
            {
                name: 'BLOCKED',
                type: 'line',
                stack: '总量',
                areaStyle: {},
                data: blockedData
            },
            {
                name: 'WAITING',
                type: 'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data: waitingData
            },
            {
                name: 'TIMED_WAITING',
                type: 'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data: timedWaitingData
            },
            {
                name: 'TERMINATED',
                type: 'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data: terminatedData
            }
        ]
    };

    return option;
}
