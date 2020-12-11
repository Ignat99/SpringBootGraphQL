package com.ochiengolanga.tuts.bootgraphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class BootGraphqlApplication {
  public static void main(String... args) {
    SpringApplication.run(BootGraphqlApplication.class, args);
  }
}
