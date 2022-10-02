package com.net128.testing.load.reqres;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class BasicSimulation extends Simulation {
	protected final HttpProtocolBuilder httpProtocol = http.baseUrl("https://reqres.in/api");

	protected final ScenarioBuilder fetchUsers = scenario("Get API Request Demo").exec(
		http("Get Single User")
			.get("/users/2").check(status().is(200)).
			check(jsonPath("$.data.first_name").is("Janet"))).pause(1);

	protected final ScenarioBuilder createUsers = scenario("Create User").exec(
		http("Create a new User")
			.post("/users").header("content-type","application/json")
			.asJson()
			.body(StringBody("{\n" +
				"    \"name\": \"QaAutomationHub\",\n" +
				"    \"job\": \"leader\"\n" +
				"}")).asJson()
			.check(status().is(201),jsonPath("$.name").is("QaAutomationHub"))).pause(1);

	protected final ScenarioBuilder updateUsers = scenario("Update User").exec(
		http("Update the User")
			.put("/users/2").header("content-type","application/json")
			.asJson().body(RawFileBody("reqres/user.json"))
			.check(status().is(200),jsonPath("$.name").is("qaautomationhub")));

	protected final ScenarioBuilder deleteUsers = scenario("Delete User").exec(
		http("Delete the User")
			.delete("/users/2").header("content-type","application/json")
			.check(status().is(204)));

	protected void init() {
		setUp(
			createUsers.injectOpen(rampUsers(5).during(5)),
			updateUsers.injectOpen(rampUsers(10).during(7)),
			deleteUsers.injectOpen(rampUsers(10).during(7)),
			fetchUsers.injectOpen(atOnceUsers(30))
		).protocols(httpProtocol);
	}

	{
		init();
	}
}