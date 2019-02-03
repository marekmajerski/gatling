
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

	val headers_0 = Map("X-Requested-With" -> "XMLHttpRequest")

// first scenario to check first funcionality test
	val scn = scenario("EmployeeAcceptOrder")
		.exec(http("Open Main Page")
			.get("/api/user/current")
			.headers(headers_0)
			.check(status.is(404)))
		.pause(5)
		.exec(http("Login process")
			.post("/login")
			.formParam("username", "employee")
			.formParam("password", "test"))
		.pause(2)
		.exec(http("change status of order")
			.put("/api/orders/5c55bb94a3c66c07364187c5")
			.body(RawFileBody("EmployeeAcceptOrder_request.txt")))
		.pause(5)
		// logout from app
		.exec(http("Logout")
			.post("/logout")
			.check(status.is(404)))

	setUp(scn.inject(rampUsers(100) during (60 seconds))).protocols(httpProtocol)
}
