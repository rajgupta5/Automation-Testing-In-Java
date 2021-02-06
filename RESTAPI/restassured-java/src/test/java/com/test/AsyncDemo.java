package com.test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncDemo {

    private static Logger LOG = LoggerFactory.getLogger(AsyncDemo.class);

//    static {
//        // setting logger to INFO to disable unwanted http client logs
//        ((ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory
//                .getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME))
//                .setLevel(ch.qos.logback.classic.Level.INFO);
//    }
//
//    @Test
//    public void asyncTest() throws InterruptedException, ExecutionException, TimeoutException {
//
//        String url = "https://reqres.in/api/users?delay=3";
//
//        Future<Response> whenResponse = Dsl.asyncHttpClient().prepareGet(url).execute();
//
//        Response response = whenResponse.get();
//        LOG.info(response.getResponseBody());
//    }
}
