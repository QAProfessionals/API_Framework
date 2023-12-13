How to write tests:
BeforeTest()
1.	Read Json test data as Map
2.	Read headers as Map
Test()
•	Define endpoint
      String endpoint = "https://api.example.com/your-endpoint";
•	Create headers Map by reading  contents of “properties” file:
      Map<String, String> headers = utilities.APIUtilities.readPropertiesAsMap("./src/test/resources/header.properties");
•	Read json payload from “Test data” file:
      For Simple Json 
        Map<String, String> jsonBody = APIUtilities.getJsonFileAsMap (“pathTo\\simpleJson.txt");
      For Array Json
        Map<String, JsonNode> jonBody1 = APIUtilities.getAllRecords("./src/test/resources/arrayJson.txt");
•	Generate response by calling post/get methods(Delete and other verbs need to be implemented in API Utilities) operation with above request payload and header  
		response1 = utilities.APIUtilities.performPostRequest(endpoint, headers, map1.get("TC001"));
•	Assert response headers (statusCode, contentLength etc)
		int status = response1.getStatusCode();
		System.out.println(status == 201);
•	Assert response body
		Response jsonResponse = response1.getBody().asString();
		System.out.println(jsonResponse.contains("101"));
•	Write Request, Request Headers, Response, Response Headers to “JIRA Test Execution”
    This step needs to be implemented
