steps to run Performance Tests

1. Clone repository and open it
2. Run jar app on localhost:8080 using `java -jar ladybug-0.0.1-SNAPSHOT.jar`
4. Scenarios are inside `user-files/simulations/`
5. Run `/gatling.sh` from `gat/bin/` directory
6. Chose id number to run one scenario
7. `OPTIONAL:` you can add description and press ENTER
8. Wait for results


TODO:
1. Change request form basic data to more detailed http://localhost:8080/swagger-ui.html
2. Add regexp to check dome elements instead checking 404 response code
3. Removed unnecessary requests
