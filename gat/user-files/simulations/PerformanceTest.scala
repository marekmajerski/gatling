
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
		.pause(2)
		.exec(http("View Order Page").get("/api/orders/?page=1&size=10")
		.check( bodyString.saveAs( "RESPONSE_DATA" ) ))
		.pause(2)
		// .exec(http("Login").get("/login").basicAuth("myUser", "myPassword").check(status.is(200)))
		//.exec(http("Add Order")
		//	.post("/api/orders/")
		//	.headers(HeadersJSON)
		//	.body(StringBody("""{"id": "","orderDate": "2019-02-20 14:25:44","status": "DRAFT"}"""))
		//	.check(status.is(200)))
		//.pause(2)
		//.exec(http("Add Order")
		//	.post("/api/orders/")
		//	.body(RawFileBody("OrderTwoItems_request.txt"))
		// find way to get id to store it in second scenario
		//	.check(status.is(200)))
		//.pause(2)
		// logout
		//.exec(http("Client Logout")
		//	.post("/logout")
		//	.check(status.is(404)))

// accept order
//	val scn = scenario("EmployeeAcceptOrder")
		//.exec(http("Login as an employee")
		//	.post("/login")
		//	.formParam("username", "employee")
		//	.formParam("password", "test")
		//	.check(status.is(200)))
		//.pause(2)
		//.exec(http("change status of order")
		// find way to copy generated id
		//	.put("/api/orders/5c55bb94a3c66c07364187c5")
		//	.body(RawFileBody("EmployeeAcceptOrder_request.txt")))
		//.pause(5)
		//.exec(http("Employee Logout")
		//	.post("/logout")
		//	.check(status.is(404)))
// find way how to run every scenario to use data from first scenario and accept the same project in second scenario

setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
//	setUp(scn.inject(rampUsers(2) during (10 seconds))).protocols(httpProtocol)
}
