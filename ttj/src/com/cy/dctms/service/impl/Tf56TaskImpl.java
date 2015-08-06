package com.cy.dctms.service.impl;

import com.cy.dctms.common.util.DateUtils;
import com.cy.dctms.service.Tf56ThreadProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by haoy on 2015/1/19.
 */
@Service("tf56ThreadProvider")
public class Tf56TaskImpl implements Tf56ThreadProvider {
    private Logger log = LoggerFactory.getLogger(getClass());

    private List<Thread> threads;
    private static volatile boolean threadCanRun = true;

    @Override
    public List<Thread> getBehindThread() {
        return threads;
    }

    @Override
    public void startBehindThread() {
        threads = new ArrayList<Thread>();

        final String urlHuo = "http://127.0.0.1:8755/tf56.php?type=huo";
        final String urlChe = "http://127.0.0.1:8755/tf56.php?type=che";

        Thread cheThread = new ExecuteThread(urlChe);
        Thread huoThread = new ExecuteThread(urlHuo);

        threads.add(cheThread);
        threads.add(huoThread);

        cheThread.start();
        huoThread.start();

    }

    @Override
    public void stopBehindthread() {
        threadCanRun = false;
    }

    class ExecuteThread extends Thread {
        final String qq56Huo = "http://www.56top.cn:8755/56qq.php?type=huo";

        final String url;

        ExecuteThread(final String url) {
            this.url = url;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    long threadLocalRandomValue = ThreadLocalRandom.current().nextLong(3, 10);
                    if (log.isInfoEnabled()) {
                        log.info("线程开始准备休眠-" + threadLocalRandomValue + "分钟...当前时间：" + DateUtils.getCurrentDateTime());
                    }
                    Thread.sleep(threadLocalRandomValue * 60 * 1000);

                    if (!threadCanRun) {
                        log.info("tomcat容器停止，线程结束.....");
                        break;
                    }

                    if (log.isInfoEnabled()) {
                        log.info("线程休眠结束...当前时间：" + DateUtils.getCurrentDateTime());
                    }

                    int nowHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                    if (nowHour >= 6 && nowHour < 18) {//凌晨6点至下午18点
                        log.info("本次爬虫工作中......");
                        accessUrl(url);//访问爬虫地址
                        log.info("本次爬虫结束......");
                        log.info("易配货货源爬取完成...开始爬取物流qq货源...");
                        if (url.contains("huo")) {
                            accessUrl(qq56Huo);
                        }
                    }

                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    private void accessUrl(String urlPath) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlPath);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setDefaultUseCaches(false);
            connection.setDoOutput(true);

            StringBuilder sb = new StringBuilder();
            String temp;
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            while ((temp = reader.readLine()) != null) {
                sb.append(temp);
            }
            if (log.isDebugEnabled()) {
                log.debug("访问返回结果-" + sb.toString());
            }
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }
}
