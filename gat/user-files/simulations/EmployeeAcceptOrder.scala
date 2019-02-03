
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class EmployeeAcceptOrder extends Simulation {
	val httpProtocol = http
		.baseUrl("http://localhost:8080")
		.inferHtmlResources()
		.acceptHeader("application/json, text/plain, */*")

	val headers_0 = Map("X-Requested-With" -> "XMLHttpRequest")

	val headers_1 = Map(
		"Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
		"X-Requested-With" -> "XMLHttpRequest")

	val scn = scenario("EmployeeAcceptOrder")
		.exec(http("Open Main Page")
			.get("/api/user/current")
			.headers(headers_0)
			.check(status.is(404)))
		.pause(5)
		.exec(http("Login process")
			.post("/login")
			.headers(headers_1)
			.formParam("username", "employee")
			.formParam("password", "test"))
		.pause(2)
		// TODO: find way to pass id of order to step
		.exec(http("Select order")
			.get("/api/orders/5c55bb94a3c66c07364187c5/statuses")
			.headers(headers_0)
			.check(status.is(404))
		.pause(7)
		.exec(http("change status of order")
			.put("/api/orders/5c55bb94a3c66c07364187c5")
			.body(RawFileBody("EmployeeAcceptOrder_request.txt"))
			.check(status.is(404))
		.pause(5)
		// logout from app
		.exec(http("request_11")
			.post("/logout")
			.check(status.is(404)))

	setUp(scn.inject(rampUsers(100) during (60 seconds))).protocols(httpProtocol)
}
