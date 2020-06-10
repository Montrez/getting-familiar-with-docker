# getting-familiar-with-docker

Getting Started using Java Spring Boot with MyEclipse/Eclipse:

- Will need Docker installed on local computer.
- Will need to start the Docker engine after installation.

Used Spring Initializer to create a Spring boot project:

- Spring boot : Spring 2.3.0
- Dependencies: Spring Web

Now inside of your project we will create a new package under /src/main/java named "resource". 

Inside of this package create a java class named "HelloResource" that is structured as follows:

```
@RestController
@RequestMapping("/rest/docker/hello")
public class HelloResource {
	
	@GetMapping
	public String hello() {
		return "Hello World";
	}

}
```
@RestController is used because we are using the Spring MVC
@RequestMapping("/rest/docker/hello") creates the mapping for the url.
@GetMapping simply takes that mapping and prints out the contents of this method on that url path.

Next we will do a maven install using eclipse:
- Right click the project and then click on 'Run As'
- Click on Run Configurations and then select Maven Build
- Inside the new Maven Build go to 'Goals' and click on 'Select'
- Next drop down default lifecycle phases and select 'install'
This will create your new jar file.

To shorten jar file name we go into the pom.xml file
And do the following: 
```
<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
		<finalName>docker-spring-boot</finalName>
</build>
```
Now we will create the Dockerfile
```
FROM openjdk:8
ADD target/docker-spring-boot.jar docker-spring-boot.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "docker-spring-boot.jar"]
```
- FROM is telling us which image we are going to use for our container. In this case we are using the offical Docker image for Java 8 that is listed on Dockerhub.
- ADD first lists the path of the jar file, and then lists the jar file we are using.
- EXPOSE will use port 8085 on localhost
- ENTRYPOINT lists all the commands we need for java to run this build.

Next we need to make sure the application knows that we are using port 8085 by updating our "application.properties" which should be located in the 'src/main/resources' package. If not, you can create the file since we only need to add one field.
```
server.port=8085
```

To create our database we will need to exectute this command "root" user
```
docker run  -p 3306:3306 -p 33060:33060 --name mysql-standalone -e MYSQL_ROOT_PASSWORD=Naruto12 -d mysql:5.6
```

Next we need to build the image:

```
docker build -f Dockerfile -t docker-spring-boot
```
-f indicator for file

-t indicator for tagname
docker-spring-boot is the name of our image.

After building, which may take a while if it's your first time. We will check that the image is created.
```
docker images
```
This command should list all of the images that we have created on Docker. We should see 'docker-spring-boot' as the most recent image at the top.

Now lets run the container with the image. On sucess, this will allow us to see Hello World on http://localhost:8085/rest/docker/hello. 
Run:
```
docker run -p 8085:8085 docker-spring-boot
```

Now if we ever need to change anything we can just rebuild using the Dockerfile and the build command from earlier and rerun the application with the command above.
