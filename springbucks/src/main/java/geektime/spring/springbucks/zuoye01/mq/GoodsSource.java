package geektime.spring.springbucks.zuoye01.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 发送消息通道
 *
 * @author yanglei
 */
public interface GoodsSource {

    String GOODS_OUTPUT = "goods_output";

    @Output(GoodsSource.GOODS_OUTPUT)
	MessageChannel output();

}

