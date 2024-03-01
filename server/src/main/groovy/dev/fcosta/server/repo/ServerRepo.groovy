package dev.fcosta.server.repo

import dev.fcosta.server.model.Server
import org.springframework.data.jpa.repository.JpaRepository

interface ServerRepo extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress)
}