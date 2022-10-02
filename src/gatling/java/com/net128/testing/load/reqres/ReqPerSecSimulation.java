package com.net128.testing.load.reqres;

import static io.gatling.javaapi.core.CoreDsl.*;

public class ReqPerSecSimulation extends BasicSimulation {
	protected void init() {
		setUp(
			createUsers.injectOpen(rampUsers(5).during(5)),
			updateUsers.injectOpen(rampUsers(10).during(7)),
			deleteUsers.injectOpen(rampUsers(10).during(7)),
			fetchUsers.injectOpen(constantUsersPerSec(300).during (20))).throttle(
			reachRps(300).in (20),
			holdFor(10),
			reachRps(500).in (20),
			holdFor(10)
		).protocols(httpProtocol);
	}
}