
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
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("pl,en-US;q=0.7,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:65.0) Gecko/20100101 Firefox/65.0")

	val headers_0 = Map("X-Requested-With" -> "XMLHttpRequest")

	val headers_1 = Map(
		"Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_8 = Map(
		"Content-Type" -> "application/json;charset=utf-8",
		"X-Requested-With" -> "XMLHttpRequest")


// first scenario to check first funcionality test
	val scn = scenario("EmployeeAcceptOrder")
	// open main page and check elements on page using regexp
		.exec(http("request_0")
			.get("/api/user/current")
			.headers(headers_0)
			.check(status.is(404)))
		.pause(5)
	// login page	TODO: set user name and password ass parameter
		.exec(http("request_1")
			.post("/login")
			.headers(headers_1)
			.formParam("username", "employee")
			.formParam("password", "test"))
		.pause(2)
		// open orders page
		.exec(http("request_2")
			.get("/api/select/statuses")
			.headers(headers_0)
			.resources(http("request_3")
			.get("/api/orders/?page=0&size=10")
			.headers(headers_0)))
		.pause(2)
		// select order
		.exec(http("request_4")
			.get("/api/orders/5c55bb94a3c66c07364187c5/statuses")
			.headers(headers_0)
			.resources(http("request_5")
			.get("/api/select/clients")
			.headers(headers_0),
            http("request_6")
			.get("/api/orders/5c55bb94a3c66c07364187c5")
			.headers(headers_0),
            http("request_7")
			.get("/api/select/products")
			.headers(headers_0)))
		.pause(7)
		// change status of order
		.exec(http("request_8")
			.put("/api/orders/5c55bb94a3c66c07364187c5")
			.headers(headers_8)
			.body(RawFileBody("EmployeeAcceptOrder_0008_request.txt"))
			.resources(http("request_9")
			.get("/api/select/statuses")
			.headers(headers_0),
            http("request_10")
			.get("/api/orders/?page=0&size=10")
			.headers(headers_0)))
		.pause(5)
		// logout from app
		.exec(http("request_11")
			.post("/logout")
			.headers(headers_8)
			.body(RawFileBody("EmployeeAcceptOrder_0011_request.txt"))
			.check(status.is(404)))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
