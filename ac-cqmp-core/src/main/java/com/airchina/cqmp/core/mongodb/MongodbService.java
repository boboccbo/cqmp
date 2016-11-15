package com.airchina.cqmp.core.mongodb;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * User: 梅海波
 * Date: 2016/11/2.
 * Time: 15:02
 * 说明：
 */
public interface MongodbService<T> {


    public List<T> find(Query query) ;

}
