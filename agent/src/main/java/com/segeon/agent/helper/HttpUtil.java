/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * daidai@yiji.com 2015-12-28 19:40 创建
 *
 */
package com.segeon.agent.helper;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.RequestLine;

/**
 * @author daidai@yiji.com
 */
public class HttpUtil {

    public static int statusCode(HttpResponse response) {
        if (response == null)
            return -1;
        try {
            return response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            return -1;
        }
    }

    public static String getRequestUrl(HttpHost host, HttpRequest request) {
        StringBuilder builder = new StringBuilder(1024);
        if (request != null && request.getRequestLine() != null && host != null) {
            RequestLine requestLine = request.getRequestLine();
            builder.append(requestLine.getMethod()).append(" ").append(host.toURI()).append(requestLine.getUri());
        }
        return builder.toString();
    }
}
