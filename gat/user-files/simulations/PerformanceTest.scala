import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PerformanceTest extends Simulation {
  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .inferHtmlResources()
    .acceptHeader("application/json, text/plain, */*")

  val HeadersJSON =
    Map("Content-Type" -> "application/javascript", "Accept" -> "text/html")

  val scn = scenario("OrderItemsAndAcceptOrder")
    .exec(
      http("Login as a Client")
        .post("/login")
        .formParam("username", "client")
        .formParam("password", "test")
        .check(status.is(200)))
    .exec(
      http("Get first category id")
        .get("/api/select/categories")
        .check(status.is(200))
        .check(jsonPath("$..id").saveAs("categoryId")))
    .exec(
      http("Get first product id")
        .get("/api/select/products")
        .check(status.is(200))
        .check(jsonPath("$..id").saveAs("productId")))
    .exec(
      http("Add Order")
        .post("/api/orders/")
        .headers(HeadersJSON)
        .body(StringBody("""{
                "id": "",
                "items": [
                  {
                    "product": {
                      "id": "${productId}",
                      "name": "Testowy produkt 1",
                      "category": {
                        "id": "${categoryId}"
                      },
                      "price": 1,
                      "expirationDate": null
                    },
                    "quantity": 10,
                    "id": 1
                  }
                ],
                "status": "DRAFT"
                }
        """))
        .asJson
        .check(status.is(200))
        .check(jsonPath("$.id").saveAs("responseId")))
    .exec(http("Client Logout")
      .post("/logout")
      .check(status.is(404)))
    .exec(
      http("Login as a Employee")
        .post("/login")
        .formParam("username", "employee")
        .formParam("password", "test")
        .check(status.is(200)))
    .exec(
      http("Change status of order")
        .put("/api/orders/${responseId}")
        .body(StringBody("""{"id": "${responseId}", "status": "ACCEPTED"}"""))
        .asJson
        .check(status.is(200)))
    .exec(http("Employee Logout")
      .post("/logout")
      .check(status.is(404))) */

  setUp(scn.inject(rampUsers(1) during (30 seconds))).protocols(httpProtocol)
}
