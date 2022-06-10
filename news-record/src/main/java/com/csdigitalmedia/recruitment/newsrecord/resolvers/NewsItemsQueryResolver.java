package com.csdigitalmedia.recruitment.newsrecord.resolvers;

import com.csdigitalmedia.recruitment.newsrecord.entity.NewsItemsEntity;
import com.csdigitalmedia.recruitment.newsrecord.service.NewsItemsService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Get NewsItems using GraphQL
 */
@Component
public class NewsItemsQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private NewsItemsService newsItemsService;

    public List<NewsItemsEntity> getNewsItems() {
        return newsItemsService.getNewsItems();
    }
}
