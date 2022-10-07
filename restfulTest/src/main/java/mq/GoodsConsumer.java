package mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;



/**
 * 消息监听消费
 *
 * @author yanglei
 */
@EnableBinding(GoodsSink.class)
public class GoodsConsumer {

    private static Logger logger = LoggerFactory.getLogger(GoodsConsumer.class);

    @StreamListener(GoodsSink.GOODS_INPUT)
    public void onReceive(String shopJson) {
        logger.info(shopJson);
    }
}

