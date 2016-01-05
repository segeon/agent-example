/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * daidai@yiji.com 2016-01-05 09:34 创建
 *
 */
package com.segeon.agent.helper;

/**
 * @author daidai@yiji.com
 */
public class HttpTrace {
    private String url;
    private int statusCode;
    private long cost;
    private Exception e;

    public HttpTrace() {
    }

    public HttpTrace(String url, int statusCode, long cost, Exception e) {
        this.url = url;
        this.statusCode = statusCode;
        this.cost = cost;
        this.e = e;
    }

    public HttpTrace(String url, int statusCode, long cost) {
        this(url, statusCode, cost, null);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "HttpTrace{" +
                "url='" + url + '\'' +
                ", statusCode=" + statusCode +
                ", cost=" + cost +
                ", e=" + e +
                '}';
    }
}
