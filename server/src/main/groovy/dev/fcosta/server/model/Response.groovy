package dev.fcosta.server.model

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical
import groovy.transform.builder.Builder
import lombok.experimental.SuperBuilder
import org.springframework.http.HttpStatus

import java.time.LocalDateTime

@Canonical
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // don't include null values in Response json
class Response {
    protected LocalDateTime timestamp
    protected int statusCode
    protected HttpStatus status
    protected String reason
    protected String message
    protected String developerMessage
    protected Map<?, ?> data
}
