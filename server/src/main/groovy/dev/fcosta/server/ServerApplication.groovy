package dev.fcosta.server

import dev.fcosta.server.enumeration.Status
import dev.fcosta.server.model.Response
import dev.fcosta.server.model.Server
import dev.fcosta.server.repo.ServerRepo
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus

import java.time.LocalDateTime

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

        Response r = new Response(
                timestamp: LocalDateTime.now(),
                data: Map.of("servers", "test"),
                message: "Servers retrieved",
                status: HttpStatus.OK,
                statusCode: HttpStatus.OK.value()
        )
    }

    @Bean
    CommandLineRunner init(ServerRepo serverRepo) {
        return {
            serverRepo.save(new Server(
                    id: null,
                    ipAddress: "8.8.8.8",
                    name: "Ubuntu Linux",
                    memory: "16 GB",
                    type: "Personal PC",
                    imageUrl: "http://localhost:8080/server/image/server1.png",
                    status: Status.SERVER_UP
            )
            )

            serverRepo.save(new Server(
                    id: null,
                    ipAddress: "172.21.80.1",
                    name: "Windows Server",
                    memory: "32 GB",
                    type: "Desktop PC",
                    imageUrl: "http://localhost:8080/server/image/server2.png",
                    status: Status.SERVER_DOWN
            )
            )

            serverRepo.save(new Server(
                    id: null,
                    ipAddress: "208.67.222.222",
                    name: "MacOS",
                    memory: "64 GB",
                    type: "laptop",
                    imageUrl: "http://localhost:8080/server/image/server3.png",
                    status: Status.SERVER_UP
            )
            )

            serverRepo.save(new Server(
                    id: null,
                    ipAddress: "172.25.192.1",
                    name: "Unix",
                    memory: "128 GB",
                    type: "mainframe",
                    imageUrl: "http://localhost:8080/server/image/server4.png",
                    status: Status.SERVER_DOWN
            )
            )
        }
    }

}