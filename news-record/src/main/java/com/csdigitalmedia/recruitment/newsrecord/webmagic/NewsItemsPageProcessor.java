package com.csdigitalmedia.recruitment.newsrecord.webmagic;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * parsing and processing web page class
 */
@Component
public class NewsItemsPageProcessor implements PageProcessor {
    private org.apache.log4j.Logger logger = Logger.getLogger(getClass());

    @Override
    public void process(Page page) {
        // Get Html information from page
        Html html = page.getHtml();
        // Get item tag
        Selectable item =  html.$("item");
        // Get the text in the title of the item tag
        Selectable title =  item.$("title","text");
        // Replace characters CDATA in title
        title = title.replace("\\<!\\[CDATA\\[","");
        title = title.replace("\\]]>","");
        // Get the description of the item tag
        Selectable description =  item.$("description","text");
        // Get the pubDate of the item tag
        Selectable pubDate =  item.$("pubDate","text");

        // Get the first ten pieces of information each time
        List<String> titles = title.all().subList(0,10);
        List<String> descriptions = description.all().subList(0,10);
        List<String> pubDates = pubDate.all().subList(0,10);
        List<String> imgUrls = page.getHtml().regex("https://cdn.nos.nl/image/\\S+\\.jpg").all().subList(0,10);

        logger.info("News Items Titles: "+ titles);
        logger.info("News Items Descriptions: " + descriptions);
        logger.info("News Items PubDates: " + pubDates);
        logger.info("News Items ImgUrls: " + imgUrls);

        page.putField("titles", titles);
        page.putField("descriptions", descriptions);
        page.putField("pubDates", pubDates);
        page.putField("imgUrls", imgUrls);
    }

    @Override
    public Site getSite() {
        // create site
        Site site = Site.me();
        // set time out
        site.setTimeOut(1000);
        // set retry times
        site.setRetryTimes(3);
        return site;
    }


}
