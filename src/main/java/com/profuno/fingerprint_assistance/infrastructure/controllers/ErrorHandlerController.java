package com.profuno.fingerprint_assistance.infrastructure.controllers;

import com.profuno.fingerprint_assistance.domain.dto.response.ErrorDTO;
import com.profuno.fingerprint_assistance.domain.dto.response.ResponseDTO;
import com.profuno.fingerprint_assistance.utils.resources.LanguageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorHandlerController {

    @Autowired
    private LanguageResource languageResource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handle(MethodArgumentNotValidException ex){
        List<ErrorDTO> listErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            listErrors.add(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), fieldName +" "+ errorMessage));
        });
        String message = languageResource.getDefaultMessage("response.invalid_fields");
        ResponseDTO response = new ResponseDTO( message,null , listErrors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<?> handle(HttpMessageNotReadableException ex){
        List<ErrorDTO> listErrors = new ArrayList<>();
        listErrors.add(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
        String message = languageResource.getDefaultMessage("response.request_data_not_readable");
        ResponseDTO response = new ResponseDTO( message,null , listErrors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PersistenceException.class)
    protected ResponseEntity<?> handle(PersistenceException ex){
        List<ErrorDTO> listErrors = new ArrayList<>();
        listErrors.add(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
        String message = languageResource.getDefaultMessage("response.persistence_error");
        ResponseDTO response = new ResponseDTO( message,null , listErrors);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
