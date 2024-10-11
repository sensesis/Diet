package com.apple.shop.Elk;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
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

    @Value("${spring.elasticsearch.username}")
    private String username;

    @Value("${spring.elasticsearch.password}")
    private String password;

    @Bean // 반환 객체 관리
    public ElasticsearchClient elasticsearchClient() { // elasticsearch 통신할 수 있는 클라이언트 만드는 메서드

        // 호스트 설정
        HttpHost[] hosts = Arrays.stream(esHosts)
                .map(HttpHost::create)
                .toArray(HttpHost[]::new);

        // 인증 설정, 서버 접근할 때 필요한 아이디, 비번 설정 후 이 정보로 인증 받음
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

        // RestClientBuilder 생성, 실제 연결 만들귀 위한 설정 준비
        RestClientBuilder builder = RestClient.builder(hosts)
                .setHttpClientConfigCallback(httpClientBuilder ->
                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
                );

        // RestClient 생성
        RestClient restClient = builder.build();

        // ElasticsearchTransport 생성
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // ElasticsearchClient 생성
        return new ElasticsearchClient(transport);
    }
}
