package common;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;


import static io.restassured.RestAssured.given;

@Slf4j
public class WebServiceMethods {
    public static RequestSpecification requestSpecification=given();
    public static Response response;

    public static Response get(String endPoint){
        final long startTime = System.currentTimeMillis();
        response=requestSpecification.get(endPoint);
        final long endTime = System.currentTimeMillis();
        log.info("Time taken for Get call : {} is : {}", endPoint, endTime - startTime);
        logResponse();
        return response;
    }

    public static Response post(String endPoint, Object body){
        final long startTime = System.currentTimeMillis();
        requestSpecification.header("Content-Type","application/json");
        requestSpecification.body(body);
        response=requestSpecification.post(endPoint);
        final long endTime = System.currentTimeMillis();
        log.info("Time taken for Get call : {} is : {}", endPoint, endTime - startTime);
        logResponse();
        return response;
    }

    public static Response put(String endPoint, Object body){
        final long startTime = System.currentTimeMillis();
        response=requestSpecification.body(body).put(endPoint);
        final long endTime = System.currentTimeMillis();
        log.info("Time taken for Get call : {} is : {}", endPoint, endTime - startTime);
        logResponse();
        return response;
    }

    public static Response delete(String endPoint){
        final long startTime = System.currentTimeMillis();
        response=requestSpecification.delete(endPoint);
        final long endTime = System.currentTimeMillis();
        log.info("Time taken for Get call : {} is : {}", endPoint, endTime - startTime);
        logResponse();
        return response;
    }

    public static void logResponse() {
        requestSpecification.log().all();
        response.then().log().all().extract().response();
    }
}
