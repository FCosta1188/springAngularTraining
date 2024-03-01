package dev.fcosta.server.enumeration

enum Status {
    SERVER_UP("SERVER_UP"),
    SERVER_DOWN("SERVER_DOWN")

    final String status

    Status(String status) {
        this.status = status
    }

    String getStatus() {
        return this.status
    }
}