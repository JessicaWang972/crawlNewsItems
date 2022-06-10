package com.csdigitalmedia.recruitment.newsrecord.service.implement;

import com.csdigitalmedia.recruitment.newsrecord.dao.NewsItemsMapper;
import com.csdigitalmedia.recruitment.newsrecord.entity.NewsItemsEntity;
import com.csdigitalmedia.recruitment.newsrecord.service.NewsItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsItemsServiceImpl implements NewsItemsService {
    @Autowired
    private NewsItemsMapper newsItemsMapper;

    @Override
    public List<NewsItemsEntity> getNewsItems() {
        List<NewsItemsEntity> newsItemsEntities = newsItemsMapper.getNewsItems();
        return newsItemsEntities;
    }
}
