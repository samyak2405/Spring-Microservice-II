package com.samyak.exception;

import com.samyak.external.response.ErrorResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponses> handleCustomException(CustomException customException){
        log.error("**************Handle Order Service Exception occurred**************");
        return new ResponseEntity<ErrorResponses>(new ErrorResponses()
                .builder()
                .errorMessages(customException.getMessage())
                .errorCode(customException.getErrorCode())
                .build(), HttpStatus.valueOf(customException.getStatus())
        );
    }
}
