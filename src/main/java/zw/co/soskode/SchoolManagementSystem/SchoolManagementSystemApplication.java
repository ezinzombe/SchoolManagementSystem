package zw.co.soskode.SchoolManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SchoolManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolManagementSystemApplication.class, args);
    }
}
