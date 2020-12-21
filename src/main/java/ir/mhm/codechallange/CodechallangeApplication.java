package ir.mhm.codechallange;

import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class CodechallangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodechallangeApplication.class, args);


}}