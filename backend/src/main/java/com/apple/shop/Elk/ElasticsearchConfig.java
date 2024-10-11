package com.apple.shop.Elk;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;

import org.apache.http.HttpHost;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration // 설정파일, 이 클래스 읽고 설정 적용하게 해줌
public class ElasticsearchConfig {

    @Value("${spring.elasticsearch.uris}") // application.properties나 application.yml 설정 값 가져와서 변수 저장
    private String[] esHosts;

    @Bean // 반환 객체 관리
    public ElasticsearchClient elasticsearchClient() { // elasticsearch 통신할 수 있는 클라이언트 만드는 메서드

        // 호스트 설정
        HttpHost[] hosts = Arrays.stream(esHosts)
                .map(HttpHost::create)
                .toArray(HttpHost[]::new);

        // RestClientBuilder 생성, 실제 연결 만들기 위한 설정 준비
        RestClientBuilder builder = RestClient.builder(hosts);

        // RestClient 생성
        RestClient restClient = builder.build();

        // ElasticsearchTransport 생성
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // ElasticsearchClient 생성
        return new ElasticsearchClient(transport);
    }
}
