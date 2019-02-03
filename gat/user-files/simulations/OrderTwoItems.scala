
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class OrderTwoItems extends Simulation {

	val httpProtocol = http
		.baseUrl("http://localhost:8080")
		.inferHtmlResources()

	val headers_0 = Map("X-Requested-With" -> "XMLHttpRequest")

	val headers_1 = Map(
		"Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
		"X-Requested-With" -> "XMLHttpRequest")

	val scn = scenario("OrderTwoItems")
		.exec(http("Open Main Page")
			.get("/api/user/current")
			.headers(headers_0)
			.check(status.is(404))
		.pause(2)
		// fill parameters (it works without password :D )
		.exec(http("Login as a Client")
			.post("/login")
			.headers(headers_1)
			.formParam("username", "client")
			.formParam("password", ""))
		.pause(3)
		.exec(http("Add Order")
			.post("/api/orders/")
			.body(RawFileBody("OrderTwoItems_request.txt"))
			.resources(http("request_8")
			.get("/api/select/statuses")
			.headers(headers_0)
			.get("/api/orders/?page=0&size=10")
			.check(status.is(404))
		.pause(2)
		// logout
		.exec(http("request_10")
			.post("/logout")
			.check(status.is(404)))

	setUp(scn.inject(rampUsers(100) during (60 seconds))).protocols(httpProtocol)
}
