package testCases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import pojosAPIStructure.EmployeeAPI;
import utilities.APIUtilities;

public class TestWithSimpleJson {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

		Map<String, String> map = APIUtilities.getJsonFileAsMap(
				"C:\\Users\\SheetalRastogi\\eclipse-workspace2\\APIFramework\\src\\main\\resources\\simpleJson.txt");

		String i1d = (String)map.get("id");
		String nam1e = (String) map.get("name");
		
		System.out.println(map + i1d + nam1e);
		
		if(i1d.equals("1"))
			map.put("1", "new Value");
		
		System.out.println(map);

		Map<String, JsonNode> map2 = APIUtilities.getAllRecords(
				"C:\\Users\\SheetalRastogi\\eclipse-workspace2\\APIFramework\\src\\main\\resources\\arrayJson.txt");
		for (JsonNode node : map2.values()) {
			// Access individual fields in array
			int id = node.get("id").asInt();  			// json contains a field   "id"
			String name = node.get("name").asText();	// json contains a field   "name"

			if(id == 1)
			((ObjectNode)node).put("id", "99");
			//.put("id", 99);
			// Conditional Print values of Json node

			System.out.println("ID: " + id + ", Name: " + name);
		}
		
		for(String n :map2.keySet()) {
			String x = n.toString();
			if(x.equals("TC001")) {
				System.out.println("This is map for my Test case");
			}
		}
		System.out.println(map2);
	}

}
