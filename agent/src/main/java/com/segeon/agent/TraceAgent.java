package com.segeon.agent;/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * daidai@yiji.com 2016-01-05 09:25 创建
 *
 */

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.pool.TypePool;

import java.lang.instrument.Instrumentation;

import static com.segeon.agent.helper.DebugListener.getListener;
import static com.segeon.agent.helper.HttpClientTypeDescriptions.*;
import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * @author daidai@yiji.com
 */
public class TraceAgent {

    public static void premain(String arguments, Instrumentation instrumentation) {
        new AgentBuilder.Default()
                .withListener(getListener())
                .type(named("org.apache.http.impl.client.CloseableHttpClient"))
                .transform(new AgentBuilder.Transformer() {
                    public DynamicType.Builder transform(DynamicType.Builder builder,
                                                         TypeDescription typeDescription) {
                        try {
                            String interceptor = "com.segeon.agent.interceptor.HttpClientInterceptor4dot3Plus";
                            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                            new ClassFileLocator.Compound(ClassFileLocator.ForClassLoader.of(contextClassLoader),
                                    ClassFileLocator.ForClassLoader.of(TraceAgent.class.getClassLoader()));
                            //ClassLoader contextClassLoader = TraceAgent.class.getClassLoader();
                            TypePool.Resolution resolution = TypePool.Default.of(ClassFileLocator.ForClassLoader.of(contextClassLoader)).describe(interceptor);
                            return builder.method(named("execute")
                                    .and(takesArguments(httpHostDescription(), httpRequestDescription(), httpContextDescription())
                                            .or(takesArguments(httpUriRequestDescription(), httpContextDescription()))
                                            .or(takesArguments(httpHostDescription(), httpRequestDescription())))
                                    .and(returns(named("org.apache.http.client.methods.CloseableHttpResponse"))))
                                    .intercept(MethodDelegation.to(resolution.resolve()));
                        } catch (Exception e) {
                            e.printStackTrace();
                            return builder;
                        }
                    }
                }).installOn(instrumentation);
    }
}
