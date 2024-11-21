package app.labs.linksy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("app.labs.linksy.DAO")
public class LinksyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinksyApplication.class, args);
    }

}
