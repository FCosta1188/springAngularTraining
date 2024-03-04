package dev.fcosta.server.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import groovy.transform.Canonical
import org.springframework.http.HttpStatus

import java.time.LocalDateTime

@Canonical
//@SuperBuilder //using groovy constructor instead
//@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL) // don't include null values in Response json
class Response {
     LocalDateTime timestamp
     int statusCode
     HttpStatus status
     String reason
     String message
     String developerMessage
     Map<?, ?> data
}
