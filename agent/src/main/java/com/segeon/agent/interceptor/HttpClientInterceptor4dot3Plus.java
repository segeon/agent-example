/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * daidai@yiji.com 2015-12-18 22:44 创建
 *
 */
package com.segeon.agent.interceptor;

import static com.segeon.agent.helper.HttpUtil.getRequestUrl;

import java.util.concurrent.Callable;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

import com.segeon.agent.helper.HttpTrace;

/**
 * @author daidai@yiji.com
 */
public class HttpClientInterceptor4dot3Plus {

    public static CloseableHttpResponse execute(
            @SuperCall Callable<CloseableHttpResponse> client, @AllArguments Object[] args) throws Exception {
        HttpTrace httpTrace = new HttpTrace();
        long start = System.currentTimeMillis();
        try {
            httpTrace.setUrl(extractRequestUrl(args));
            CloseableHttpResponse response = client.call();
            httpTrace.setCost(System.currentTimeMillis() - start);
            httpTrace.setStatusCode(response.getStatusLine().getStatusCode());
            System.out.println(httpTrace);
            return response;
        } catch (Exception e) {
            httpTrace.setCost(System.currentTimeMillis() - start);
            httpTrace.setE(e);
            httpTrace.setStatusCode(-1);
            System.out.println(httpTrace);
            throw e;
        }
    }

    private static String extractRequestUrl(Object[] args) {
        if (args[0] instanceof HttpHost) {
            HttpHost host = (HttpHost) args[0];
            HttpRequest request = (HttpRequest) args[1];
            return getRequestUrl(host, request);
        } else if (args[0] instanceof HttpUriRequest){
            HttpUriRequest request = (HttpUriRequest) args[0];
            if (request != null && request.getURI() != null) {
                StringBuilder builder = new StringBuilder(1024);
                builder.append(request.getMethod()).append(" ").append(request.getURI().toString());
                return builder.toString();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

}
