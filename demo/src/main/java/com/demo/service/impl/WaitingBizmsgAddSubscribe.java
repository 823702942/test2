//package com.demo.service.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.dhcc.bizmessage.platform.model.messagemodel.BizMessageRabbitmqSubscribeId;
//import com.dhcc.bizmessage.platform.model.messagemodel.BizMessageRabbitmqTopic;
//import com.dhcc.bizmessage.platform.model.messagemodel.MessageWaitingMsgAddModel;
//import com.dhcc.easymq.easyrabbitmq.IEasyRabbitMQConsumer;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
///**
// * @Author: 罗帅
// * @Date: 2021/6/17
// */
//@Service
//@Slf4j
//public class WaitingBizmsgAddSubscribe {
//    @Autowired
//    private IEasyRabbitMQConsumer easyRabbitMQConsumer;
//    /**
//     * 订阅【待办】消息新增
//     *
//     * @author 罗帅
//     * @date 2020/09/24
//     */
//    public void subscribeAddWaitingMsg() {
//        try {
//            easyRabbitMQConsumer.subscribeTopicAsync(MessageWaitingMsgAddModel.class,
//                    BizMessageRabbitmqTopic.WAITING_BIZMESSAGE_ADD + "#",
//                    BizMessageRabbitmqSubscribeId.WAITING_BIZMESSAGE_ADD,
//                    (message) -> {
//                        log.debug("待办消息新增，入参message:{}",message);
//                        try {
//                          log.info("待办消息新增-订阅成功");
//                        } catch (Exception e) {
//                            log.error("【待办】消息新增消费过程中发生异常,入参消息体:{},具体异常:", JSON.toJSON(message), e);
//                        }
//                    });
//        } catch (Exception ee) {
//            log.error("待办消息新增mq消息接收出现异常:", ee);
//        }
//    }
//}
