package dev.fcosta.server.model

import dev.fcosta.server.enumeration.Status
import groovy.transform.Canonical
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotEmpty

@Entity
@Table(name = "server")
@Canonical
class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id

    @Column(unique = true)
    @NotEmpty(message = "IP address cannot be empty or null")
    String ipAddress

    String name
    String memory
    String type
    String imageUrl
    Status status
}
