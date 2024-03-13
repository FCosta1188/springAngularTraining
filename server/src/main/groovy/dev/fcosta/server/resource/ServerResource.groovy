package dev.fcosta.server.resource

import dev.fcosta.server.enumeration.Status
import dev.fcosta.server.model.Response
import dev.fcosta.server.model.Server
import dev.fcosta.server.service.implementation.ServerServiceImpl
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cglib.core.Local
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime

@RestController
@RequestMapping("/server")
class ServerResource {

    @Autowired
    final ServerServiceImpl serverService

    ServerResource(ServerServiceImpl serverService) {
        this.serverService = serverService
    }

    @GetMapping("/list")
    ResponseEntity<Response> getServers() {
        return ResponseEntity<Response>.ok(new Response(
                timestamp: LocalDateTime.now(),
                data: Map.of("servers", serverService.list(30)),
                message: "Servers retrieved",
                status: HttpStatus.OK,
                statusCode: HttpStatus.OK.value()
        ))
    }

    @GetMapping("/get/{id}")
    ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
        return ResponseEntity<Response>.ok(new Response(
                timestamp: LocalDateTime.now(),
                data: Map.of("server", serverService.get(id)),
                message: "Server retrieved",
                status: HttpStatus.OK,
                statusCode: HttpStatus.OK.value()
        ))
    }

    @GetMapping("/ping/{ipAddress}") // does not work while connected to VPN
    ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) {
        Server server = serverService.ping(ipAddress)

        return ResponseEntity<Response>.ok(new Response(
                timestamp: LocalDateTime.now(),
                data: Map.of("server", server),
                message: server.status == Status.SERVER_UP ? "Ping success" : "Ping failed",
                status: HttpStatus.OK,
                statusCode: HttpStatus.OK.value()
        ))
    }

    @GetMapping(path = "/image/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    byte[] getServerImage(@PathVariable("fileName") String fileName) {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/springAngularTrainingImages/" + fileName))
    }

    @PostMapping("/save")
    ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity<Response>.ok(new Response(
                timestamp: LocalDateTime.now(),
                data: Map.of("server", serverService.create(server)),
                message: "Server created",
                status: HttpStatus.CREATED,
                statusCode: HttpStatus.CREATED.value()
        ))
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        return ResponseEntity<Response>.ok(new Response(
                timestamp: LocalDateTime.now(),
                data: Map.of("deleted", serverService.delete(id)),
                message: "Server deleted",
                status: HttpStatus.OK,
                statusCode: HttpStatus.OK.value()
        ))
    }
}
