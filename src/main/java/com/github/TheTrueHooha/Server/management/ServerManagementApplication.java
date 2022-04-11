package com.github.TheTrueHooha.Server.management;

import com.github.TheTrueHooha.Server.management.Enums.ServerStatus;
import com.github.TheTrueHooha.Server.management.Model.Servers;
import com.github.TheTrueHooha.Server.management.Repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.github.TheTrueHooha.Server.management.Enums.ServerStatus.*;

@SpringBootApplication
public class ServerManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerManagementApplication.class, args);
	}


	//added some input fields for the models
	@Bean
	CommandLineRunner serverRunner(ServerRepository repository){
		return args -> {
			repository.save(new Servers(
					null,
					"Logic Computers",
					"192.154.1.155",
					"8gb ram",
					"Enterprise",
					"http://localhost:8000/api/vi/servers/image/server.png",
					SERVER_RUNNING
			) );

			repository.save(new Servers(
					null,
					"GraceBounty Systems Information",
					"192.666.0.334",
					"4gb ram",
					"Business",
					"http://localhost:8000/api/v1/servers/image/server2.png",
					SERVER_RUNNING
			));

			repository.save(new Servers(
				null,
				"Danny williams",
				"192.554.0.552",
				"16gb ram",
				"Personal",
				"http://localhost:8000/api/v1/serves/image/server3.png",
					SERVER_RUNNING
			));
		};
	}
}
