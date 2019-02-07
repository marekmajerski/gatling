steps to run Performance Tests

1. Clone repository and open it
2. Run jar app on localhost:8080 using `java -jar ladybug-0.0.1-SNAPSHOT.jar`
4. Scenarios are inside `user-files/simulations/`
5. Run `/gatling.sh` from `gat/bin/` directory
6. `OPTIONAL:` you can add description and press ENTER
7. Wait for results


TODO:
1. Add regexp to check dome elements instead checking 404 response code
2. Improve JSON to cover funcional Tests

HINT: you can decrease log level by comment logback.xml line
`<logger name="io.gatling.http.engine.response" level="TRACE" /> `
