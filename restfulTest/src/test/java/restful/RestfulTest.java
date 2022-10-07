package restful;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import restful.util.HttpUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestfulApplication.class)
public class RestfulTest {

    private static Logger log = LoggerFactory.getLogger(RestfulTest.class);


    @Test
    public void restfulTest() {
        String s = HttpUtils.sendGet("http://127.0.0.1:8081/query/1", null);
        log.info("返回结果{}",s);
    }

    @Test
    public void restfulXmlTest() {
        String s = HttpUtils.sendGet("http://127.0.0.1:8081/query/xml/1", null);
        log.info("返回结果{}",s);
    }
}
