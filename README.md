1.  To run the app: in the root of project, run the command "mvn spring-boot:run" 
2.  In the bash shell, run "curl user:cGFzc3dvcmQ=@localhost:8080/superheroes" to get list of all heroes in JSON format.
3.  In the bash shell, run "curl user:cGFzc3dvcmQ=@localhost:8080/superheroes/superhero/pseudonym/Batman" to get details of the specific hero "Batman" in JSON format.
3.  In the bash shell, run "curl user:cGFzc3dvcmQ=@localhost:8080/superheroes/superhero/pseudonym/Superman" to get details of the specific hero "Superman" in JSON format.

Unit tests are in src/test/java <br/>
Integration tests are in src/intergration-test/java <br/><br/>
To run all unit tests and integration tests: Type "mvn clean verify" <br/>
