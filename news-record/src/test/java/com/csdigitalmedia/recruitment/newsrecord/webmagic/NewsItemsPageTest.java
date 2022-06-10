package com.csdigitalmedia.recruitment.newsrecord.webmagic;

import com.csdigitalmedia.recruitment.newsrecord.NewsRecordApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Spider;


@SpringBootTest(classes = NewsRecordApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class NewsItemsPageTest {
    @Autowired
    private NewsItemsPageProcessor newsItemsPageProcessor;

    @Autowired
    private NewsItemsPipeline newsItemsPipeline;

    @Test
    void processTest() {
        Spider spider = Spider.create(newsItemsPageProcessor);
        spider.addUrl("http://feeds.nos.nl/nosjournaal?format=xml");
        spider.addPipeline(newsItemsPipeline);
        spider.thread(1);
        spider.run();

    }
}