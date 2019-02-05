
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class EmployeeAcceptOrder extends Simulation {
// main application should be run at address below
	val httpProtocol = http
		.baseUrl("http://localhost:8080")
		.inferHtmlResources()
		.acceptHeader("application/json, text/plain, */*")

	val header = Map("X-Requested-With" -> "XMLHttpRequest")

// create order
	val header1 = Map("X-Requested-With" -> "XMLHttpRequest")

	val scn = scenario("OrderTwoItems")
		.exec(http("Open Main Page")
			.get("/api/user/current"))
		.pause(2)
		// fill parameters (it works without password :D )
		.exec(http("Login as a Client")
			.post("/login")
			.headers(header1)
			.formParam("username", "client")
			.formParam("password", "test"))
		.pause(3)
		.exec(http("Add Order")
			.post("/api/orders/")
			.body(RawFileBody("OrderTwoItems_request.txt"))
			// find way to get id to store it in second scenario
			.check(status.is(201)))
		.pause(2)
		// logout
		.exec(http("request_10")
			.post("/logout")
			.check(status.is(404)))

// accept order
	val scn = scenario("EmployeeAcceptOrder")
		.exec(http("Open Main Page")
			.get("/api/user/current")
			.headers(header)
			.check(status.is(404)))
		.pause(5)
		.exec(http("Login process")
			.post("/login")
			.formParam("username", "employee")
			.formParam("password", "test"))
		.pause(2)
		.exec(http("change status of order")
		// find way to copy generated id
			.put("/api/orders/5c55bb94a3c66c07364187c5")
			.body(RawFileBody("EmployeeAcceptOrder_request.txt")))
		.pause(5)
		.exec(http("Logout")
			.post("/logout")
			.check(status.is(404)))
// find way how to run every scenario to use data from first scenario and accept the same project in second scenario
	setUp(scn.inject(rampUsers(100) during (60 seconds))).protocols(httpProtocol)
}
