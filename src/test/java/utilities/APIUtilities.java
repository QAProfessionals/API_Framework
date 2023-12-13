package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIUtilities {
	// not important
	public static Map<String, String> getJsonFileAsMap(String path) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new File(path), new TypeReference<Map<String, String>>() {
		});
	}

	// Generic method to read entire json (simple or Array JSON)
	public static Map<String, JsonNode> getAllRecords(String path) throws IOException {
		Map<String, JsonNode> map = new HashMap<>();
		String jsonArray = new String(Files.readAllBytes(Paths.get(path)));
		int i = 1;
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode arrayNode = mapper.readTree(jsonArray);
			for (JsonNode node : arrayNode) {
				map.put("TC00" + i, node);
				i++;
			}

		} catch (Exception e) {

		}
		return map;
	}

	public static Response performPostRequest(String endpoint, Map<String, String> headers, JsonNode jsonNode) {
		return RestAssured.given().baseUri(endpoint).headers(headers).contentType(ContentType.JSON).body(jsonNode)
				.post();
	}

	public static Response performGetRequest(String endpoint, Map<String, String> headers) {
		return RestAssured.given().baseUri(endpoint).headers(headers).contentType(ContentType.JSON).param("postId", "2")
				.get("/comments");
	}

	public static Map<String, String> readPropertiesAsMap(String filePath) {
		Properties properties = new Properties();
		Map<String, String> propertiesMap = new HashMap<>();

		try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
			properties.load(fileInputStream);
			// Store properties into the Map
			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				propertiesMap.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return propertiesMap;
	}
}