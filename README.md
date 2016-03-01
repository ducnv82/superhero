**Note about port** : The application will be running at the port **8443** and the integration tests run at the port **9443**, so please make sure that the such ports in your machine are available

1.  To run the app: in the root of project, run the command "mvn spring-boot:run"
2.  In the bash shell, run "curl -ku user:cGFzc3dvcmQ= https://localhost:8443/superheroes" to get list of all heroes in JSON format.
3.  In the bash shell, run "curl -ku user:cGFzc3dvcmQ= https://localhost:8443/superheroes/superhero/pseudonym/Batman" to get details of the specific hero "Batman" in JSON format.
3.  In the bash shell, run "curl -ku user:cGFzc3dvcmQ= https://localhost:8443/superheroes/superhero/pseudonym/Superman" to get details of the specific hero "Superman" in JSON format.

Unit tests are in src/test/java <br/>
Integration tests are in src/intergration-test/java <br/><br/>
To run all unit tests and integration tests: Type "mvn clean verify" <br/><br/>

-----------------------------------------------------------------------------------------

A Docker image is at <https://hub.docker.com/r/ducnv82/superhero/> <br/>
Command to get Docker image: "sudo docker pull ducnv82/superhero" <br/>
Verify that you get the Docker image ok with command "sudo docker images". The output should be: <br/>
	
          REPOSITORY            TAG                 IMAGE ID            CREATED             SIZE
          ducnv82/superhero     latest              00c91fc7f510        XXX minutes ago     672.7 MB

After pulling the Docker image, running with command "sudo docker run -p 8443:8443 -t ducnv82/superhero"
