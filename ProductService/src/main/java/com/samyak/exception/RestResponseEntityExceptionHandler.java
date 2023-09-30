package com.samyak.exception;

import com.samyak.dto.ErrorResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductServiceCustomException.class)
    public ResponseEntity<ErrorResponses> handleProductServiceException(ProductServiceCustomException productServiceCustomException){
        log.error("**************Handle Product Service Exception occurred**************");
        return new ResponseEntity<ErrorResponses>(new ErrorResponses()
                .builder()
                .errorMessages(productServiceCustomException.getMessage())
                .errorCode(productServiceCustomException.getErrorCode())
                .build(), HttpStatus.NOT_FOUND
        );
    }
}
