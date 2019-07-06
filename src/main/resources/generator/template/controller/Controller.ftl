package ${targetPackage};


import com.github.pagehelper.PageInfo;
import com.java.general.interceptors.controller.ControllerRecorder;
import com.java.general.response.dto.ResponseDto;
import com.java.general.response.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${modelPath}.${lowerCaseModelName}.dto.${classModelName}TableRequestDto;
import ${modelPath}.${lowerCaseModelName}.dto.${classModelName}SaveRequestDto;
import ${modelPath}.${lowerCaseModelName}.facade.${classModelName}Facade;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/7 12:41
 */
@RestController
@RequestMapping("/${lowerCaseModelName}")
public class ${classModelName}Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(${classModelName}Controller.class);

    @Autowired
    private ${classModelName}Facade ${lowerCaseModelName}Facade;

    /**
     * 获取主表数据
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/table")
    @ControllerRecorder(path = "/menu/table", validateClass = ${classModelName}TableRequestDto.class)
    public ResponseDto table(@RequestBody ${classModelName}TableRequestDto requestDto) {

        PageInfo pageInfo = ${lowerCaseModelName}Facade.queryTable(requestDto);

        return ResponseUtil.bindSuccessResponse(pageInfo.getList());
    }


    /**
     * 保存
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/save")
    @ControllerRecorder(path = "/${classModelName}/save")
    public ResponseDto save(@RequestBody ${classModelName}SaveRequestDto requestDto) {

        ${lowerCaseModelName}Facade.save(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

    /**
     * 删除
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/delete")
    @ControllerRecorder(path = "/${classModelName}/delete")
    public ResponseDto delete(@RequestBody ${classModelName}SaveRequestDto requestDto) {

        ${lowerCaseModelName}Facade.delete(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

}
