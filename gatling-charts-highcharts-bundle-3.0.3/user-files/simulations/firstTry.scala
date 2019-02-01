
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class firstTry extends Simulation {

	val httpProtocol = http
		.baseUrl("http://detectportal.firefox.com")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("pl,en-US;q=0.7,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:65.0) Gecko/20100101 Firefox/65.0")

	val headers_0 = Map("Pragma" -> "no-cache")



	val scn = scenario("firstTry")
		.exec(http("request_0")
			.get("/success.txt")
			.headers(headers_0))
		.pause(88)
		.exec(http("request_1")
			.get("/success.txt")
			.headers(headers_0))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}