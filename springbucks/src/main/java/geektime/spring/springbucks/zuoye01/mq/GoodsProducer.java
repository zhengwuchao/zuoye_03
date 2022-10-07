package geektime.spring.springbucks.zuoye01.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 消息发送
 *
 * @author yanglei
 */
@EnableBinding(GoodsSource.class)
public class GoodsProducer {

    @Autowired
    private GoodsSource source;

    public void sendMessage(String msg) {
        try {
            source.output().send(MessageBuilder.withPayload(msg).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

