package geektime.spring.springbucks.zuoye01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhengwuchao
 */
@ImportResource({"classpath*:/spring/spring-bean.xml"})
@SpringBootApplication
@EnableTransactionManagement
public class Zuoye01ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Zuoye01ServiceApplication.class, args);
    }


}
