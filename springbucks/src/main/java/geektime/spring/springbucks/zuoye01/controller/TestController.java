package geektime.spring.springbucks.zuoye01.controller;

import geektime.spring.springbucks.zuoye01.mq.GoodsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送mq test
 */
@RestController
@RequestMapping("mq")
public class TestController {

    @Autowired
    private GoodsProducer goodsProducer;

    @GetMapping("send")
    public void get() {
        goodsProducer.sendMessage("我发送了一条消息");
    }

}

