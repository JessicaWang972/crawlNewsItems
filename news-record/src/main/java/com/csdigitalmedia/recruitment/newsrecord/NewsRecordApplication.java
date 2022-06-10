package com.csdigitalmedia.recruitment.newsrecord;

import com.csdigitalmedia.recruitment.newsrecord.webmagic.NewsItemsScheduledTask;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;


@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
@MapperScan(basePackages = "com.csdigitalmedia.recruitment.newsrecord.dao")
public class NewsRecordApplication implements CommandLineRunner {
    @Autowired
    NewsItemsScheduledTask newsItemsScheduledTask;

    /**
     * crawl data
     * @param strings
     * @throws Exception
     */
    @Override
    public void run(String... strings){
        newsItemsScheduledTask.crawl();
    }

    public static void main(String[] args) {
        SpringApplication.run(NewsRecordApplication.class, args);
    }

}
