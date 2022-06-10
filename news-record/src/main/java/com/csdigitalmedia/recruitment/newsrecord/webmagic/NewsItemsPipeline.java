package com.csdigitalmedia.recruitment.newsrecord.webmagic;

import com.csdigitalmedia.recruitment.newsrecord.dao.NewsItemsMapper;
import com.csdigitalmedia.recruitment.newsrecord.entity.NewsItemsEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class NewsItemsPipeline implements Pipeline {
    private org.apache.log4j.Logger logger = Logger.getLogger(getClass());

    @Value("${img.downloadAddress}")
    private String imgAddress;

    @Autowired
    NewsItemsMapper newsItemsMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<NewsItemsEntity> newsItemsEntities = new ArrayList<>();
        List<String> titles = resultItems.get("titles");
        List<String> descriptions = resultItems.get("descriptions");
        List<String> pubDates = resultItems.get("pubDates");
        List<String> imgUrls = resultItems.get("imgUrls");
        for (int i = 0; i < titles.size(); i++) {
            NewsItemsEntity newsItemsEntity = new NewsItemsEntity();
            newsItemsEntity.setTitle(titles.get(i));
            newsItemsEntity.setDescription(descriptions.get(i));
            newsItemsEntity.setPubDate(pubDates.get(i));
            newsItemsEntity.setImgUrl(imgUrls.get(i));

            newsItemsEntities.add(newsItemsEntity);
        }

        if (!CollectionUtils.isEmpty(newsItemsEntities)) {
            this.newsItemsMapper.deleteNewsItems();
            this.newsItemsMapper.addNewsItems(newsItemsEntities);
            deleteImg(new File(imgAddress));
            imgDownload(imgUrls);
        }
    }

    /**
     * Delete images
     * @param file
     */
    public static void deleteImg(File file) {
        if (file != null) {
            if (file.isDirectory()) {
                String[] list = file.list();
                if (list != null && list.length > 0) {
                    for (String s : list) {
                        File file1 = new File(file, s);
                        deleteImg(file1);
                        String[] strs = s.split("\\.");
                        if (strs.length > 0) {
                            String type = strs[strs.length - 1];
                            if (type.equals("png") || type.equals("jpg") || type.equals("jpeg")) {
                                boolean b = file1.delete();
                                System.out.println(file1.getAbsolutePath() + "\t" + b);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Download images
     * @param links
     */
    public void imgDownload(List<String> links){

        InputStream inStream = null;
        String imgName = "";
        for(int i = 0; i < links.size(); i++){
            String link = links.get(i);
            // Set the download image name
            String pattern = "(?<=image/).*?(?=.jpg)";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(link);
            if (m.find()) {
                imgName = m.group().replace("/", "_");
            } else {
                imgName = String.valueOf(i);
            }

            try {
                URL url = new URL(link);
                URLConnection con = url.openConnection();
                inStream = con.getInputStream();
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int len = 0;
                while((len = inStream.read(buf)) != -1){
                    outStream.write(buf,0,len);
                }
                inStream.close();
                outStream.close();
                File file = new File(imgAddress + imgName+".jpg");
                FileOutputStream op = new FileOutputStream(file);
                op.write(outStream.toByteArray());
                op.close();
            } catch (MalformedURLException e) {
                logger.info("News Items Download MalformedURLException: " + e);
            } catch (IOException e) {
                logger.info("News Items Download IOException: " + e);
            }
        }
    }
}
