package com.cy.dctms.timeTask;

import com.cy.dctms.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by haoy on 2015/1/6.
 */
public class Tf56Task {

    private Logger log = LoggerFactory.getLogger(getClass());

    public void execute() {
        if (log.isInfoEnabled()) {
            log.info("易配货开始爬取...");
        }

        final String urlHuo = "http://127.0.0.1:8755/tf56.php?type=huo";
        final String urlChe = "http://127.0.0.1:8755/tf56.php?type=che";

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new ExecuteThread(urlHuo));
        executorService.execute(new ExecuteThread(urlChe));

        executorService.shutdown();
    }

    class ExecuteThread extends Thread {
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
                    if (log.isInfoEnabled()) {
                        log.info("线程休眠结束...当前时间：" + DateUtils.getCurrentDateTime());
                    }

                    int nowHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                    if (nowHour >= 6 && nowHour < 18) {//凌晨6点至下午18点
                        log.info("本次爬虫工作中......");
                        accessUrl(url);//访问爬虫地址
                        log.info("本次爬虫结束......");
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
