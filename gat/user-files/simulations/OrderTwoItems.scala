
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class OrderTwoItems extends Simulation {

	val httpProtocol = http
		.baseUrl("http://localhost:8080")
		.inferHtmlResources()
		.acceptHeader("application/json, text/plain, */*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("pl,en-US;q=0.7,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:65.0) Gecko/20100101 Firefox/65.0")

	val headers_0 = Map("X-Requested-With" -> "XMLHttpRequest")

	val headers_1 = Map(
		"Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_2 = Map(
		"Accept" -> "*/*",
		"Pragma" -> "no-cache")

	val headers_7 = Map(
		"Content-Type" -> "application/json;charset=utf-8",
		"X-Requested-With" -> "XMLHttpRequest")

	val scn = scenario("OrderTwoItems")
		.exec(http("Open Main Page")
			.get("/api/user/current")
			.headers(headers_0))
		.pause(2)
		// fill parameters (it works without password :D )
		.exec(http("Login to system as a Client")
			.post("/login")
			.headers(headers_1)
			.formParam("username", "client")
			.formParam("password", ""))
		.pause(3)
		.exec(http("Open Orders list")
			.get("/api/select/statuses")
			.headers(headers_0)
			.resources(http("request_4")
			.get("/api/orders/?page=0&size=10")
			.headers(headers_0)))
		.pause(2)
		// get products
		.exec(http("request_5")
			.get("/api/select/clients")
			.headers(headers_0)
			.resources(http("request_6")
			.get("/api/select/products")
			.headers(headers_0)))
		.pause(13)
		// change status
		.exec(http("request_7")
			.post("/api/orders/")
			.headers(headers_7)
			.body(RawFileBody("OrderTwoItems_0007_request.txt"))
			.resources(http("request_8")
			.get("/api/select/statuses")
			.headers(headers_0),
            http("request_9")
			.get("/api/orders/?page=0&size=10")
			.headers(headers_0)))
		.pause(2)
		// logout
		.exec(http("request_10")
			.post("/logout")
			.headers(headers_7)
			.body(RawFileBody("OrderTwoItems_0010_request.txt"))
			.check(status.is(404)))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
