package com.example.journalist_media_manager_springboot.exception;

import com.example.journalist_media_manager_springboot.error.ApiError;
import com.example.journalist_media_manager_springboot.error.ErrorCodes;
import com.example.journalist_media_manager_springboot.error.ErrorDescription;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

public abstract class AbstractRestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRestExceptionHandler.class);

    private Integer apiErrorCode;

    protected AbstractRestExceptionHandler(Integer apiErrorCode) {
        this.apiErrorCode = apiErrorCode;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ApiError> processGenericException(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
        ApiError apiError = new ApiError(apiErrorCode, ErrorDescription.INTERNAL_SERVER_ERROR.getDescription());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processNoResourceFoundException(NoResourceFoundException exception) {
        LOGGER.error(exception.getMessage());
        ApiError apiError = new ApiError(ErrorCodes.GatewayApi.EndpointNotFound, ErrorDescription.ENDPOINT_NOT_FOUND_ERROR.getDescription());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        LOGGER.error(exception.getMessage(), exception);
        ApiError apiError = new ApiError(apiErrorCode, ErrorDescription.REQUEST_METHOD_NOT_SUPPORTED_ERROR.getDescription());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        LOGGER.error(exception.getMessage(), exception);
        ApiError apiError = new ApiError(apiErrorCode, ErrorDescription.MESSAGE_NOT_READABLE_EXCEPTION.getDescription());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUserNotFoundException(UserNotFoundException exception) {
        LOGGER.error(ErrorDescription.USER_NOT_FOUND_ERROR.getDescription());
        return new ResponseEntity<>(ErrorCodes.User.UserNotFoundError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistAuthenticationException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUserAlreadyExistAuthenticationException(UserAlreadyExistAuthenticationException exception) {
        LOGGER.error(exception.getMessage());
        return new ResponseEntity<>(ErrorCodes.User.UserAlreadyExistsOnDatabase, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidFieldValueException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processInvalidFieldValueException(InvalidFieldValueException exception) {
        LOGGER.error("{} - {}", ErrorDescription.DATA_NOT_FOUND_ERROR.getDescription(), exception.getMessage());
        ApiError apiError = new ApiError(ErrorCodes.PressboxApi.ResourceNotFoundError, ErrorDescription.DATA_NOT_FOUND_ERROR.getDescription());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processEntityNotFoundException(EntityNotFoundException exception) {
        LOGGER.error("{} - {}", ErrorDescription.NOT_FOUND_EXCEPTION.getDescription(), exception.getMessage());
        ApiError apiError = new ApiError(ErrorCodes.PressboxApi.ResourceNotFoundError, ErrorDescription.NOT_FOUND_EXCEPTION.getDescription());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processExpiredJwtException(ExpiredJwtException exception) {
        String errorMessage = ErrorDescription.AUTH_TOKEN_EXPIRED_ERROR.getDescription();
        LOGGER.error("{} - {}", errorMessage, exception.getMessage());
        ApiError apiError = new ApiError(ErrorCodes.GatewayApi.TokenExpiredAuthError, errorMessage);
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processSignatureException(SignatureException exception) {
        String errorMessage = ErrorDescription.AUTH_TOKEN_SIGNATURE_ERROR.getDescription();
        LOGGER.error("{} - {}", errorMessage, exception.getMessage());
        ApiError apiError = new ApiError(ErrorCodes.GatewayApi.TokenSignatureAuthError, errorMessage);
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processMalformedJwtException(MalformedJwtException exception) {
        String errorMessage = ErrorDescription.AUTH_TOKEN_MALFORMED_JWT_ERROR.getDescription();
        LOGGER.error("{} - {}", errorMessage, exception.getMessage());
        ApiError apiError = new ApiError(ErrorCodes.GatewayApi.TokenMalformedJwtAuthError, errorMessage);
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUnsupportedJwtException(UnsupportedJwtException exception) {
        String errorMessage = ErrorDescription.AUTH_TOKEN_UNSUPPORTED_JWT_ERROR.getDescription();
        LOGGER.error("{} - {}", errorMessage, exception.getMessage());
        ApiError apiError = new ApiError(ErrorCodes.GatewayApi.TokenUnsupportedJwtAuthError, errorMessage);
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processAccessDeniedException(AuthenticationException exception) {
        LOGGER.error(exception.getMessage());
        ApiError apiError = new ApiError(apiErrorCode, ErrorDescription.ACCESS_DENIED_EXCEPTION.getDescription());
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        LOGGER.error(exception.getMessage());
        ApiError apiError = new ApiError(ErrorCodes.PressboxApi.ProcessError, ErrorDescription.BAD_REQUEST_ERROR.getDescription());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
