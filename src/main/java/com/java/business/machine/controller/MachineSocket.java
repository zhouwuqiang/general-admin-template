package com.java.business.machine.controller;

import com.alibaba.fastjson.JSON;
import com.java.business.machine.dto.MachineSocketDto;
import com.java.business.machine.facade.MachineFacade;
import com.java.general.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/13 15:11
 */
@ServerEndpoint(value = "/machine/socket")
@Component
public class MachineSocket {

    private static final Logger LOGGER = LoggerFactory.getLogger(MachineSocket.class);

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<MachineSocket> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    private MachineFacade machineFacade;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();

        if (machineFacade == null) {
            initMachineFacade();
        }

        LOGGER.info("有新连接加入！当前在线人数为{}", getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        LOGGER.info("有一连接关闭！当前在线人数为{}", getOnlineCount());
    }


    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        LOGGER.info("来自客户端的消息{}", message);
        MachineSocketDto socketDto = new MachineSocketDto();

        socketDto.setMachineInfo(machineFacade.getMachineInfo());
        socketDto.setMemoryInfo(machineFacade.getMemoryInfo());
        socketDto.setThreadDto(machineFacade.getThreadDto());

        sendMessage(JSON.toJSONString(socketDto));
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    private void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            LOGGER.error("发送信息异常:", e);
        }
    }

    /**
     * 群发自定义消息
     */
    private static void sendInfo(String message) {
        for (MachineSocket item : webSocketSet) {
            item.sendMessage(message);
        }
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        MachineSocket.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        MachineSocket.onlineCount--;
    }

    private synchronized void initMachineFacade() {
        machineFacade = SpringContextUtil.getBean(MachineFacade.class);
    }
}
