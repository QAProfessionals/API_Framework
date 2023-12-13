package testCases;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import utilities.APIUtilities;

public class PostAPIResponse {

	public static void main(String[] args) throws IOException {
		Map<String, String> map = APIUtilities.getJsonFileAsMap("./src/test/resources/simpleJson.txt");
        Map<String, String> headers = utilities.APIUtilities.readPropertiesAsMap("./src/test/resources/header.properties");

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		Response response1 = RestAssured.given()
                .contentType(ContentType.JSON)
                .headers(headers)
                .when()
                .post("/posts");
		
		int status = response1.getStatusCode();
		System.out.println(status == 201);
		String jsonResponse = response1.getBody().asString();
		System.out.println(jsonResponse.contains("101"));
		List<Header> responseHeaders = response1.getHeaders().asList();
		System.out.println(responseHeaders.get(1).equals("Content-Type=application/json; charset=utf-8"));
		
		
		
		//-- Processing results with APIUtilities
		String endpoint = "https://jsonplaceholder.typicode.com";
		Map<String, JsonNode> map1 = APIUtilities.getAllRecords("./src/test/resources/arrayJson.txt");

		response1 = utilities.APIUtilities.performPostRequest(endpoint, headers, map1.get("TC001"));
		status = response1.getStatusCode();
		System.out.println(status == 201);
		jsonResponse = response1.getBody().asString();
		System.out.println(jsonResponse.contains("101"));
		responseHeaders = response1.getHeaders().asList();
		System.out.println(responseHeaders.get(1).equals("Content-Type=application/json; charset=utf-8"));
				
	}

}
