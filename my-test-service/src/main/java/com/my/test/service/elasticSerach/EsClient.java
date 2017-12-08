package com.my.test.service.elasticSerach;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;

import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.net.InetAddress;

public class EsClient {
    private static final String IP = "127.0.0.1";
    private static final int PORT = 9300;  //端口
    //1.设置集群名称：默认是elasticsearch，并设置client.transport.sniff为true，使客户端嗅探整个集群状态，把集群中的其他机器IP加入到客户端中

    //创建客户端
    public static Client getTransportClient() {
        try {
            Client client = TransportClient.builder().build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(IP), PORT));
            return client;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }


    public static void close(Client client) {
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                client.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void main(String args[]){
        String index="megacorp";
        String type="employee";
        SearchResponse response = EsClient.getTransportClient().prepareSearch(index)//设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(type)//设置type, 这个在建立索引的时候同时设置了, 或者可以使用head工具查看
                .setQuery(QueryBuilders.matchQuery("last_name", "smith")) //在这里"message"是要查询的field,"Accept"是要查询的内容
                .setFrom(0)
                .setSize(10)
                .setExplain(true)
                .execute()
                .actionGet();
        for(SearchHit hit:response.getHits()){
            System.out.println(hit.getSourceAsString());
        }
    }

}