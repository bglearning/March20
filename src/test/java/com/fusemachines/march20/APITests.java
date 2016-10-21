package com.fusemachines.march20;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fusemachines.march20.thought.Thought;
import com.fusemachines.march20.thought.ThoughtRepository;

public class APITests extends BaseTest {

	@Autowired
	private ThoughtRepository repo;

	@BeforeClass
	public void databaseSetup() {
		repo.deleteAll();
		repo.save(new Thought("Life is Beautiful", "Thoughtful"));
	}

	@Test
	public void basicPingTest() {
		given().when().get("/").then().statusCode(200);
	}

	@Test
	public void getThoughtContent() {
		given().when().get("/").then().body("_embedded.thoughts[0].content", equalTo("Life is Beautiful"));
	}

	@Test
	public void postNewThought() {

		Map<String, String> thought = createThought();

		given().contentType("application/json").body(thought).when().post("/").then().statusCode(201);
	}

	@Test
	public void putUpdatedThought() {
		Map<String, String> thought = createThought();

		String resource_link = given().contentType("application/json").body(thought).when().post("/").then().extract()
				.response().path("_links.self.href");

		thought.put("content", "Hmm... Maybe life is simple");

		given().contentType("application/json").body(thought).when().put(resource_link).then().statusCode(200);
	}

	@Test
	public void deleteThought() {
		Map<String, String> thought = createThought();

		String resource_link = given().contentType("application/json").body(thought).when().post("/").then().extract()
				.response().path("_links.self.href");

		given().when().delete(resource_link).then().statusCode(204);

	}

	public Map<String, String> createThought() {
		Map<String, String> thought = new HashMap<>();
		thought.put("content", "Life is difficult");
		thought.put("feeling", "Serious");
		thought.put("timestamp", String.valueOf(Clock.systemUTC().millis()));

		return thought;

	}
}