package com.csdigitalmedia.recruitment.newsrecord.dao;

import com.csdigitalmedia.recruitment.newsrecord.entity.NewsItemsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("NewsItemsMapper")
public interface NewsItemsMapper {

    int addNewsItems(List<NewsItemsEntity> newsItemsEntities);

    void deleteNewsItems();

    List<NewsItemsEntity> getNewsItems();
}
