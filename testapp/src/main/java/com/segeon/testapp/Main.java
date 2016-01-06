package com.segeon.testapp;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int i = 0;
        while(i < 100) {
            try {
                CloseableHttpResponse response = HttpClients.createDefault().execute(new HttpGet("http://www.ifeng.com/"));
                //System.out.println(EntityUtils.toString(response.getEntity()));
                System.out.println(response.getStatusLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            i++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
}