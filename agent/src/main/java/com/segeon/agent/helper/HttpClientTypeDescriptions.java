/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * daidai@yiji.com 2015-12-26 10:53 创建
 *
 */
package com.segeon.agent.helper;

import java.io.Closeable;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import net.bytebuddy.description.type.TypeDescription;

/**
 * @author daidai@yiji.com
 */
public class HttpClientTypeDescriptions {

    private static int getInterfaceModifiers() {
        return Modifier.PUBLIC|Modifier.INTERFACE|Modifier.ABSTRACT;
    }

    public static TypeDescription abstractHttpClientDescription() {
        return new TypeDescription.Latent("org.apache.http.impl.client.AbstractHttpClient",
                Modifier.PUBLIC|Modifier.ABSTRACT,
                TypeDescription.OBJECT,
                Arrays.asList(httpClientDescription()));
    }

    public static TypeDescription closeableHttpClientDescription() {
        return new TypeDescription.Latent("org.apache.http.impl.client.CloseableHttpClient",
                Modifier.PUBLIC|Modifier.ABSTRACT,
                TypeDescription.OBJECT,
                Arrays.asList(httpClientDescription(), new TypeDescription.ForLoadedType(Closeable.class)));
    }

    public static TypeDescription httpClientDescription() {
        return new TypeDescription.Latent("org.apache.http.client.HttpClient",
                getInterfaceModifiers(),
                TypeDescription.OBJECT,
                null);
    }

    public static TypeDescription httpMessageDescription() {
        return new TypeDescription.Latent("org.apache.http.HttpMessage",
                getInterfaceModifiers(),
                TypeDescription.OBJECT,
                null);
    }

    public static TypeDescription httpRequestDescription() {
        return new TypeDescription.Latent("org.apache.http.HttpRequest",
                getInterfaceModifiers(),
                httpMessageDescription(),
                null);
    }



    public static TypeDescription httpConnectionDescription() {
        return new TypeDescription.Latent("org.apache.http.HttpConnection",
                getInterfaceModifiers(),
                new TypeDescription.ForLoadedType(Closeable.class),
                null);
    }

    public static TypeDescription httpClientConnectionDescription() {
        return new TypeDescription.Latent("org.apache.http.HttpClientConnection",
                getInterfaceModifiers(),
                httpConnectionDescription(),
                null);
    }

    public static TypeDescription httpContextDescription() {
        return new TypeDescription.Latent("org.apache.http.protocol.HttpContext",
                getInterfaceModifiers(),
                TypeDescription.OBJECT,
                null);
    }


    public static TypeDescription httpHostDescription() {
        return new TypeDescription.Latent("org.apache.http.HttpHost",
                Modifier.PUBLIC|Modifier.FINAL,
                TypeDescription.OBJECT,
                Arrays.asList(new TypeDescription.ForLoadedType(Cloneable.class),
                        new TypeDescription.ForLoadedType(Serializable.class)));
    }


    public static TypeDescription httpUriRequestDescription() {
        return new TypeDescription.Latent("org.apache.http.client.methods.HttpUriRequest",
                getInterfaceModifiers(),
                httpRequestDescription(),
                null);
    }

    public static TypeDescription requestDirectorDescription() {
        return new TypeDescription.Latent("org.apache.http.client.RequestDirector",
                getInterfaceModifiers(),
                TypeDescription.OBJECT,
                null);
    }

    public static TypeDescription httpClientInterceptor4dot0Description() {
        return new TypeDescription.Latent("com.yiji.dtrace.agent.httpclient4.interceptor.HttpClientInterceptor4dot0Plus",
                Modifier.PUBLIC,
                TypeDescription.OBJECT,
                null);
    }
}
