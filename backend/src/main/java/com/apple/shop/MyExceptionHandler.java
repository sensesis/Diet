package com.apple.shop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handler() {

        return ResponseEntity.status(400).body("에러남 ㅋㅋ");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle2() {

        return ResponseEntity.status(400).body("에러남 ㅋㅋ");
    }
}
