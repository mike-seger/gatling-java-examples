package reqres;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class SampleSimulation extends Simulation {
    private HttpProtocolBuilder httpProtocol = http.baseUrl("https://reqres.in/api");

    private ScenarioBuilder FetchUsers = scenario("Get API Request Demo").exec(
            http("Get Single User")
                  .get("/users/2").check(status().is(200)).
                  check(jsonPath("$.data.first_name").is("Janet"))).pause(1);

    private ScenarioBuilder Createusers = scenario("Create User").exec(
        http("Create a new User")
            .post("/users").header("content-type","application/json")
            .asJson()
            .body(StringBody("{\n" +
                    "    \"name\": \"QaAutomationHub\",\n" +
                    "    \"job\": \"leader\"\n" +
                    "}")).asJson()
            .check(status().is(201),jsonPath("$.name").is("QaAutomationHub"))).pause(1);

    private ScenarioBuilder Upateusers = scenario("Update User").exec(
        http("Update the User")
            .put("/users/2").header("content-type","application/json")
            .asJson().body(RawFileBody("Data/user.json"))
            .check(status().is(200),jsonPath("$.name").is("qaautomationhub")));

    private ScenarioBuilder Deleteusers = scenario("Delete User").exec(
        http("Delete the User")
            .delete("/users/2").header("content-type","application/json")
            .check(status().is(204)));

    {
        setUp(
            Createusers.injectOpen(rampUsers(5).during(5)),
            Upateusers.injectOpen(rampUsers(10).during(7)),
            Deleteusers.injectOpen(rampUsers(10).during(7))).protocols(httpProtocol);
            FetchUsers.injectOpen(atOnceUsers(30)).protocols(httpProtocol);    
    }
}