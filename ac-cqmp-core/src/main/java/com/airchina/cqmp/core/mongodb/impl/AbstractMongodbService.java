package com.airchina.cqmp.core.mongodb.impl;

import com.airchina.cqmp.core.mongodb.MongodbService;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * User: 梅海波
 * Date: 2016/11/2.
 * Time: 15:03
 * 说明：
 */
public abstract class AbstractMongodbService<T> implements MongodbService{
    @Override
    public List<T> find(Query query) {
        return null;
    }
}
