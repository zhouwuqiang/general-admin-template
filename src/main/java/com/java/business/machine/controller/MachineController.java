package com.java.business.machine.controller;

import com.java.business.machine.dto.MachineInfo;
import com.java.business.machine.dto.MemoryInfo;
import com.java.business.machine.dto.RuntimeInfo;
import com.java.business.machine.facade.MachineFacade;
import com.java.general.interceptors.controller.ControllerRecorder;
import com.java.general.response.dto.ResponseDto;
import com.java.general.response.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/10 0:49
 */
@RestController
@RequestMapping("/machine")
public class MachineController {


    @Autowired
    private MachineFacade machineFacade;

    /**
     * 获取机器信息
     *
     * @return
     */
    @RequestMapping(value = "/info")
    @ControllerRecorder(path = "/machine/info")
    public ResponseDto machineInfo() {
        MachineInfo machineInfo = machineFacade.getMachineInfo();
        return ResponseUtil.bindSuccessResponse(machineInfo);
    }

    /**
     * 获取机器信息
     *
     * @return
     */
    @RequestMapping(value = "/memory")
    @ControllerRecorder(path = "/machine/memory")
    public ResponseDto memoryInfo() {
        MemoryInfo memoryInfo = machineFacade.getMemoryInfo();
        return ResponseUtil.bindSuccessResponse(memoryInfo);
    }

    /**
     * 获取运行信息
     *
     * @return
     */
    @RequestMapping(value = "/runtime/Info")
    @ControllerRecorder(path = "/machine/runtime/Info")
    public ResponseDto runtimeInfo() {
        RuntimeInfo runtimeInfo = machineFacade.getRuntimeInfo();
        return ResponseUtil.bindSuccessResponse(runtimeInfo);
    }


}
