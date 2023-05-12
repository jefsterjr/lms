package org.arcone.lmsapi.util.config;

import org.arcone.lmsapi.util.config.dto.ErrorResponseDTO;
import org.arcone.lmsapi.util.exceptions.BusinessRuleException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ErrorResponseDTO(e.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
    }

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleBusinessRulesException(BusinessRuleException e) {
        return new ErrorResponseDTO(e.getMessage());
    }

}
