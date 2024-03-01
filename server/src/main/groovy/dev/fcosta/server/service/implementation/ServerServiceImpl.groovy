package dev.fcosta.server.service.implementation

import dev.fcosta.server.enumeration.Status
import dev.fcosta.server.model.Server
import dev.fcosta.server.repo.ServerRepo
import dev.fcosta.server.service.ServerService
import groovy.util.logging.Slf4j
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Service
@Transactional //any database operations performed within the marked method or class will be executed within a transaction. If the transaction is successful, the changes will be committed to the database.
@RequiredArgsConstructor //constructor which handles dependency injection with serverRepo and @Autowired
@Slf4j //log
class ServerServiceImpl implements ServerService {
    final ServerRepo serverRepo

    @Override
    Server create(Server server) {
        log.info("Saving new server: {}", server.name)

        server.imageUrl = setServerImageUrl()
        return serverRepo.save(server)
    }

    @Override
    Collection<Server> list(int limit) {
        log.info("Fetching all servers")

        return serverRepo.findAll(PageRequest.of(0, limit)).toList()
    }

    @Override
    Server get(Long id) {
        log.info("Fetching server by ID: {}", id)

        return serverRepo.findById(id).get()
    }

    @Override
    Server update(Server server) {
        log.info("Updating server: {}", server.name)

        return serverRepo.save(server) //upsert
    }

    @Override
    Boolean delete(Long id) {
        log.info("Deleting server by ID: {}", id)

        serverRepo.deleteById(id)
        return true
    }

    @Override
    Server ping(String ipAddress) {
        log.info("Pinging server IP: {}", ipAddress)

        Server server = serverRepo.findByIpAddress(ipAddress)
        InetAddress address = InetAddress.getByName(ipAddress)
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN)
        serverRepo.save(server)
        return server
    }

    String setServerImageUrl() {
        String[] imageNames = ["server1.jpg", "server2.jpg", "server3.jpg", "server4.jpg"]
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString()
    }
}
