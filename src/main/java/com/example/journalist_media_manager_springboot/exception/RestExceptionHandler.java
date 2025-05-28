package com.example.journalist_media_manager_springboot.exception;

import com.example.journalist_media_manager_springboot.error.ErrorCodes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends AbstractRestExceptionHandler{

    protected RestExceptionHandler() {
        super(ErrorCodes.PressboxApi.Generic);
    }
}
