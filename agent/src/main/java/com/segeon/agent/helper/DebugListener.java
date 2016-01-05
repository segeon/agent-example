/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved
 */

/*
 * 修订记录:
 * daidai@yiji.com 2015-12-28 18:47 创建
 *
 */
package com.segeon.agent.helper;

import java.io.File;
import java.io.IOException;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

/**
 * @author daidai@yiji.com
 */
public class DebugListener {
    public static AgentBuilder.Listener getListener() {
        return new AgentBuilder.Listener() {
            @Override
            public void onTransformation(TypeDescription typeDescription, DynamicType dynamicType) {
                System.err.println("onTransformation:" + typeDescription.getCanonicalName());
                /*try {
                    //dynamicType.saveIn(new File("generated_classes"));
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onIgnored(TypeDescription typeDescription) {
                //System.err.println("onIgored:" + typeDescription.getCanonicalName());
            }

            @Override
            public void onError(String typeName, Throwable throwable) {
                System.err.println("onError:" + typeName);
                throwable.printStackTrace();
            }

            @Override
            public void onComplete(String typeName) {
                //System.err.println("onComplete:" + typeName);
            }
        };
    }
}
