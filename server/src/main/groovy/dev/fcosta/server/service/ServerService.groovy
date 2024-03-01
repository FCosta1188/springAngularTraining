package dev.fcosta.server.service

import dev.fcosta.server.model.Server

interface ServerService {
    Server create(Server server)
    Collection<Server> list(int limit)
    Server get(Long id)
    Server update(Server server)
    Boolean delete(Long id)
    Server ping(String ipAddress)
}