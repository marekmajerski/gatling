
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class OrderTwoItems extends Simulation {

	val httpProtocol = http
		.baseUrl("http://localhost:8080")
		.inferHtmlResources()
		.acceptHeader("application/json, text/plain, */*")

	val headers_0 = Map("X-Requested-With" -> "XMLHttpRequest")

	val scn = scenario("OrderTwoItems")
		.exec(http("Open Main Page")
			.get("/api/user/current")
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
			.body(RawFileBody("OrderTwoItems_request.txt")))
		.pause(2)
		// logout
		.exec(http("request_10")
			.post("/logout")
			.check(status.is(404)))

	setUp(scn.inject(rampUsers(100) during (60 seconds))).protocols(httpProtocol)
}
