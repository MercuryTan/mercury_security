package com.tx.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 *
 */
public class WireMockServer {

    public static void main(String[] args) throws IOException {
        //连接到wiremock server
        WireMock.configureFor(8081);
        //移除所有请求
        WireMock.removeAllMappings();

        send("/user/1","01");

    }

    private static void send(String url, String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource("wiremock/response/"+fileName+".txt");

        String content = FileUtils.readFileToString(resource.getFile(),"UTF-8");

        WireMock.stubFor(WireMock.get(url).willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }
}