package com.gunnampalli.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class ELKUtils {
	private ELKUtils() {}
	//This method will call from ListenersClass and update the status using elasticsearch in RealTime Dashboard in kibana
	public static void sendDetailstoELK(String testName,String status)
	{
		//here it will read current or now time from system and format to ISO DATE and Time and store into the timestamp
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

		Map<String,String> payloadMap = new HashMap<>();
		payloadMap.put("testname", testName);
		payloadMap.put("status", status);
		payloadMap.put("executionTime", timestamp);

		Response response = given()
				.header("Content-Type","application/json")
				.body(payloadMap)
				.post("http://localhost:9200/test/result");
		
        // Log response details
        int statusCode = response.getStatusCode();
        String result = response.path("result");
        String docId = response.path("_id");

        if (statusCode == 200 || statusCode == 201) {
            System.out.println(" Test result indexed successfully.");
            System.out.println("Document ID: " + docId);
            System.out.println("Indexing result: " + result);
        } else {
            System.err.println(" Failed to index test result. Status code: " + statusCode);
            System.err.println("Response: " + response.asString());
        }
	}

}
