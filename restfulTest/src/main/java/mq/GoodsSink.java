package mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 接收消息通道
 */
public interface GoodsSink {

    String GOODS_INPUT = "goods_input";

    @Input(GoodsSink.GOODS_INPUT)
    SubscribableChannel input();

}

