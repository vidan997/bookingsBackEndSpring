package bookingsproject.app.application.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MultipartDebugConfig {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @Value("${spring.servlet.multipart.max-request-size}")
    private String maxRequestSize;

    @PostConstruct
    public void printMultipartLimits() {
        System.out.println("MAX FILE SIZE = " + maxFileSize);
        System.out.println("MAX REQUEST SIZE = " + maxRequestSize);
    }
}