package com.csdigitalmedia.recruitment.newsrecord.webmagic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * crawl news items scheduled task
 */
@Component
public class NewsItemsScheduledTask {
    private org.apache.log4j.Logger logger = Logger.getLogger(getClass());

    @Autowired
    private NewsItemsPipeline newsItemsPipeline;

    @Autowired
    private NewsItemsPageProcessor newsItemsPageProcessor;

    private ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

    public void crawl(){
        // Scheduled task, crawling every 5 minutes
        timer.scheduleWithFixedDelay(() -> {
            try {
                logger.info("Start Crawling...........");
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date startDate = new Date(System.currentTimeMillis());
                logger.info("Start Crawing Time: " + formatter.format(startDate));
                Thread.currentThread().setName("newsItemsCrawlerThread");

                Spider.create(newsItemsPageProcessor)
                        // Crawling data from http://feeds.nos.nl/nosjournaal?format=xml
                        .addUrl("http://feeds.nos.nl/nosjournaal?format=xml")
                        // Store the captured data into database
                        .addPipeline(newsItemsPipeline)
                        // Open 5 threads to crawl
                        .thread(5)
                        // Start the crawler asynchronously
                        .start();
            } catch (Exception e) {
                logger.info("Periodically Crawl Data Thread Execution Exception: "+ e);
            }
        }, 0, 5, TimeUnit.MINUTES);
    }


}
