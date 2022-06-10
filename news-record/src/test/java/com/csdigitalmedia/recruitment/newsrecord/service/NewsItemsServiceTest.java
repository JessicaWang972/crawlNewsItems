package com.csdigitalmedia.recruitment.newsrecord.service;

import com.csdigitalmedia.recruitment.newsrecord.dao.NewsItemsMapper;
import com.csdigitalmedia.recruitment.newsrecord.entity.NewsItemsEntity;
import com.csdigitalmedia.recruitment.newsrecord.service.implement.NewsItemsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class NewsItemsServiceTest {
    @InjectMocks
    NewsItemsServiceImpl newsItemsServiceImpl;

    @Mock
    NewsItemsMapper newItemsMapper;

    @Test
    public void getNewsItems(){
        List<NewsItemsEntity> newsItemsEntity = new ArrayList<>();
        NewsItemsEntity newsItemsEntity1 = new NewsItemsEntity();
        newsItemsEntity1.setId("001");
        newsItemsEntity1.setTitle("news001");
        newsItemsEntity1.setDescription("newsDescription001");
        newsItemsEntity1.setImgUrl("newsImgUrl001");
        NewsItemsEntity newsItemsEntity2 = new NewsItemsEntity();
        newsItemsEntity2.setId("002");
        newsItemsEntity2.setTitle("news002");
        newsItemsEntity2.setDescription("newsDescription002");
        newsItemsEntity2.setImgUrl("newsImgUrl002");
        Mockito.when(newItemsMapper.getNewsItems())
                .thenReturn(newsItemsEntity);
        assertThat(newsItemsServiceImpl.getNewsItems())
                .isEqualTo(newsItemsEntity);

    }


}