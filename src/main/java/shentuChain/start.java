package shentuChain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
@Controller
@EnableAutoConfiguration //开启自动配置
@ComponentScan
public class start {
    public static void main(String[] args) {
        SpringApplication.run(start.class, args);
    }
}