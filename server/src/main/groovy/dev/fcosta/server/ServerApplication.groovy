package dev.fcosta.server

import dev.fcosta.server.enumeration.Status
import dev.fcosta.server.model.Server
import dev.fcosta.server.repo.ServerRepo
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

//***** https://www.youtube.com/watch?v=8ZPsZBcue50 *****
/**
 * @author Francesco Costa (https://fcosta.dev/)
 * @version 0.0
 * @since 29/02/2024
 */
@SpringBootApplication
class ServerApplication {

    static void main(String[] args) {
        SpringApplication.run(ServerApplication, args)
    }

    @Bean
    CommandLineRunner init(ServerRepo serverRepo) {
        return {
            serverRepo.save(new Server(
                    null,
                    "192.168.1.160",
                    "Ubuntu Linux",
                    "16 GB",
                    "Personal PC",
                    "http://localhost:8080/server/image/server1.png",
                    Status.SERVER_UP
            )
            )

            serverRepo.save(new Server(
                    null,
                    "192.168.1.160",
                    "Ubuntu Linux",
                    "16 GB",
                    "Personal PC",
                    "http://localhost:8080/server/image/server2.png",
                    Status.SERVER_UP
            )
            )

            serverRepo.save(new Server(
                    null,
                    "192.168.1.160",
                    "Ubuntu Linux",
                    "16 GB",
                    "Personal PC",
                    "http://localhost:8080/server/image/server3.png",
                    Status.SERVER_UP
            )
            )

            serverRepo.save(new Server(
                    null,
                    "192.168.1.160",
                    "Ubuntu Linux",
                    "16 GB",
                    "Personal PC",
                    "http://localhost:8080/server/image/server4.png",
                    Status.SERVER_UP
            )
            )
        }
    }

}