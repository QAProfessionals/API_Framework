package testCases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class getAPIResponse {

	public static void main(String[] args) {
		//---  Generic approach
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		Response response1 = RestAssured.given()
                .contentType(ContentType.JSON)
                .param("postId", "2")
                .when()
                .get("/comments");
		
		int status = response1.getStatusCode();
		System.out.println(status == 200);
		String jsonResponse = response1.getBody().asString();
		System.out.println(jsonResponse.contains("eaque et deleniti atque tenetur ut quo ut"));
		List<Header> responseHeaders = response1.getHeaders().asList();
		System.out.println(responseHeaders.get(1));

		
		//---   Approach via APIUtilities
		String endpoint = "https://jsonplaceholder.typicode.com";
        Map<String, String> headers = utilities.APIUtilities.readPropertiesAsMap("./src/test/resources/header.properties");
 

		Response response = utilities.APIUtilities.performGetRequest(endpoint, headers);
		int status1 = response.getStatusCode();
		System.out.println(status1 == 200);
		String jsonResponse1 = response.getBody().asString();
		System.out.println(jsonResponse1.contains("eaque et deleniti atque tenetur ut quo ut"));
		List<Header> responseHeaders1 = response.getHeaders().asList();
		System.out.println(responseHeaders1.get(2));
		System.out.println(responseHeaders1.get(2).equals("Transfer-Encoding=chunked"));
		
		
		
	}

}
