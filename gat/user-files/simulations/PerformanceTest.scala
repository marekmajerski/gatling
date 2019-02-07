
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._


class PerformanceTest extends Simulation {
// main application should be run at address below



	val httpProtocol = http
		.baseUrl("http://localhost:8080")
		.inferHtmlResources()
		.acceptHeader("application/json, text/plain, */*")

	val HeadersJSON = Map("Content-Type" -> "application/javascript", "Accept" -> "text/html")

	val scn = scenario("OrderItemsAndAcceptOrder")

		.exec(http("Login as a Client")
			.post("/login")
			.formParam("username", "client")
			.formParam("password", "test")
			.check(status.is(200)))

		// step for easy DEBUG
		//.exec(http("View Order Page")
		//	.get("/api/orders/?page=1&size=10")
		//	.check(status.is(200)))
		//.pause(2)

		// step for easy DEBUG
		//.exec(http("Current User")
		//	.get("/api/user/current")
		//	.check(status.is(200)))
		//.pause(2)

		.exec(http("Add Order")
			.post("/api/orders/")
			.headers(HeadersJSON)
			.body(RawFileBody("test.txt"))
			.asJson
			.check(status.is(200))
			.check(jsonPath("$.id").saveAs("responseId")))

		.exec(http("Client Logout")
			.post("/logout")
			.check(status.is(404)))

		.exec(http("Login as a Employee")
			.post("/login")
			.formParam("username", "employee")
			.formParam("password", "test")
			.check(status.is(200)))

		// step for easy DEBUG
		//.exec(http("Current User")
		//	.get("/api/user/current")
		//	.check(status.is(200)))
		//.pause(2)

		.exec(http("Change status of order")
		 // find way to copy generated id
			.put("/api/orders/${responseId}")
			.body(StringBody("""{"id": "${responseId}","orderDate": "2019-02-07 14:28:39","completeDate": null,"status": "ACCEPTED"}"""))
			.asJson
			.check(status.is(200)))

		.exec(http("Employee Logout")
			.post("/logout")
			.check(status.is(404)))

// line for easy DEBUG
// setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
setUp(scn.inject(rampUsers(100) during (30 seconds))).protocols(httpProtocol)
}
