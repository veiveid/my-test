package com.my.test.service.elasticSerach;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.max.MaxBuilder;

import java.util.Iterator;
import java.util.Map;

public class TestQuery {
/**
 * 所有球队
 { "name": "thompson", "age": 26, "salary": 2000, "team": "war", "position": "sg" }
 { "name": "wigins",   "age": 20, "salary": 500,  "team": "tim", "position": "sf"}
 { "name": "green",    "age": 26, "salary": 2000, "team": "war", "position": "pf" }
 { "name": "james",    "age": 33, "salary": 3000, "team": "cav", "position": "sf" }
 { "name": "garnett",  "age": 40, "salary": 1000, "team": "tim", "position": "pf" }
 { "name": "irving",   "age": 25, "salary": 2000, "team": "cav", "position": "pg" }
 { "name": "towns",    "age": 21, "salary": 500,  "team": "tim", "position": "c" }
 { "name": "curry",    "age": 29, "salary": 1000, "team": "war", "position": "pg" }
 { "name": "lavin",    "age": 21, "salary": 300,  "team": "tim", "position": "sg" }
 */

    public Client getClient(){
        return EsClient.getTransportClient();
    }



    public SearchRequestBuilder initSearchRequestBuilder(Client client){
        String index = "player";
        String type = "player";
        SearchRequestBuilder sbuilder = client.prepareSearch(index).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setTypes(type);
        return sbuilder;
    }


    /**
     * 以球队分组
     */
    public void test1(){
        Client client = getClient();
        SearchRequestBuilder sbuilder = initSearchRequestBuilder(client);
        AggregationBuilder teamAgg = AggregationBuilders.terms("team_group").field("team").size(Integer.MAX_VALUE);
        sbuilder.addAggregation(teamAgg);
        SearchResponse response = sbuilder.execute().actionGet();
        Map<String,Aggregation> aggMap = response.getAggregations().asMap();
        StringTerms teamAggres = (StringTerms)aggMap.get("team_group");
        Iterator<Terms.Bucket> teamBucketIt = teamAggres.getBuckets().iterator();
        while(teamBucketIt.hasNext()){
            Terms.Bucket buck = teamBucketIt.next();
            String teamName = (String)buck.getKey();
            long count = buck.getDocCount();
            System.out.println("teamName="+teamName+","+"count="+count);
        }
        EsClient.close(client);
    }

    //计算每个球队每个位置的球员数
    public void test3(){
        Client client = getClient();
        SearchRequestBuilder sbuilder = initSearchRequestBuilder(client);
        TermsBuilder teamAgg = AggregationBuilders.terms("team_group").field("team");
        TermsBuilder posAgg = AggregationBuilders.terms("position_count").field("position");
        sbuilder.addAggregation(teamAgg.subAggregation(posAgg));
        SearchResponse response = sbuilder.execute().actionGet();
        Map<String,Aggregation> aggMap = response.getAggregations().asMap();
        StringTerms teamAggres = (StringTerms) aggMap.get("team_group");
        Iterator<Terms.Bucket> teamBucketIt = teamAggres.getBuckets().iterator();
        while(teamBucketIt.hasNext()){
            Terms.Bucket buck = teamBucketIt.next();
            String teamName = (String) buck.getKey();
            System.out.println("球队名称："+teamName);
            Map<String,Aggregation> aggMapSub = buck.getAggregations().asMap();
            StringTerms posCouAggres = (StringTerms)aggMapSub.get("position_count");
            Iterator<Terms.Bucket> posCouBucketIt = posCouAggres.getBuckets().iterator();
            while (posCouBucketIt.hasNext()){
                Terms.Bucket buckPosCou = posCouBucketIt.next();
                String posName = (String)buckPosCou.getKey();
                Long posCou = (Long)buckPosCou.getDocCount();
                System.out.println("位置："+posName+","+"数量："+posCou);
            }
        }
        EsClient.close(client);
    }

    /**
     * 查询薪水2000元的人
     */
    public void test2(){
        Client client = getClient();
        SearchRequestBuilder sbuilder = initSearchRequestBuilder(client);
        QueryBuilder qb = QueryBuilders.termQuery("salary",2000);
        System.out.println(qb.toString());
        SearchResponse response = sbuilder.setQuery(qb).setFrom(0).setSize(10).setExplain(true).get();

        SearchHit[] sh = response.getHits().getHits();
        for (SearchHit s : sh){
            Map<String, Object> map = s.getSource();
            //System.out.println(map);
            System.out.println(map.get("name")+":"+map.get("salary"));
        }
        EsClient.close(client);
    }

    //查找每个球队的最大年龄
    public void queryMaxAgeVeryTeam(){
        Client client = getClient();
        SearchRequestBuilder sbuilder = initSearchRequestBuilder(client);
        TermsBuilder teamAgg = AggregationBuilders.terms("position_count").field("team");
        MaxBuilder maxAgg = AggregationBuilders.max("max_age").field("age");
        sbuilder.addAggregation(teamAgg.subAggregation(maxAgg));
        SearchResponse response = sbuilder.execute().actionGet();
        Map<String,Aggregation> aggMap = response.getAggregations().asMap();
        StringTerms teamAggres = (StringTerms) aggMap.get("position_count");
        Iterator<Terms.Bucket> teamIt = teamAggres.getBuckets().iterator();
        while(teamIt.hasNext()){
            Terms.Bucket bucket = teamIt.next();
            String teamName = (String)bucket.getKey();
            System.out.println("球队名称："+teamName);
            Map<String,Aggregation> maxAgeAggMap = bucket.getAggregations().asMap();
            for (Aggregation agg : maxAgeAggMap.values()){
                Double age =  (Double)agg.getProperty("value");
                System.out.println(age);
            }

        }
        EsClient.close(client);
    }


    public static void main(String[] args) {
        TestQuery t = new TestQuery();
        //t.test1();
        //t.test2();
        //t.test3();
        t.queryMaxAgeVeryTeam();
    }
}