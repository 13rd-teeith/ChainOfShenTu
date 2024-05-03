package shentuChain.controller.addSrc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${src.path}")
    private String src;

    @Value("${dst.path}")
    private String dst;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("已将"+src+"映射到"+dst);
        registry.addResourceHandler(dst + "/**")
                .addResourceLocations("file:" + src + "/");
    }
}

