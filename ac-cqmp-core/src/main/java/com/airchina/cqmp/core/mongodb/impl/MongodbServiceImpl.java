package com.airchina.cqmp.core.mongodb.impl;

import com.airchina.cqmp.core.mongodb.MongodbService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: 梅海波
 * Date: 2016/11/2.
 * Time: 15:04
 * 说明：
 */
public class MongodbServiceImpl extends AbstractMongodbService implements MongodbService {

    //注入mongoTemplate对象
    @Resource(name = "mongoTemplate")
    public MongoTemplate mongoTemplate;

    /**
     *
     * User: 张政
     * Date: 2016/11/4.
     * Time: 15:04
     * 说明： 重写父类通过查询语句查询方法
     * @param query 查询语句
     * @return 返回List对象
     */
    @Override
    public List find(Query query) {
        return mongoTemplate.getCollection("person").find().toArray();
    }

    //提供注入mongoTemplate对象方法
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
