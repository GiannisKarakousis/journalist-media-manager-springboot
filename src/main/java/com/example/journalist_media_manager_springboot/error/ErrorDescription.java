package com.example.journalist_media_manager_springboot.error;

/**
 * Error Descriptions for logging and exception usage
 */
public enum ErrorDescription {
    /**
     * Common Rest Controllers Errors Description
     */
    INTERNAL_SERVER_ERROR("Internal error during process.Please check logs for additional information"),
    REQUEST_METHOD_NOT_SUPPORTED_ERROR("Http Request Method not supported"),
    MESSAGE_NOT_READABLE_EXCEPTION("Incoming request body has errors"),
    ACCESS_DENIED_EXCEPTION("Access is denied"),
    NOT_FOUND_EXCEPTION("Resource not found"),
    ENDPOINT_NOT_FOUND_ERROR("API endpoint does not exist"),
    BAD_REQUEST_ERROR("Incoming request is not valid"),
    MISCONFIGURATION_ERROR("Application Misconfiguration error"),
    /**
     * Common Client Requests Errors Description
     */
    CLIENT_BAD_REQUEST_ERROR("Error communicating with GSIS.Please check logs for additional information"),
    CLIENT_COMMUNICATION_ERROR("Error communicating with GSIS.Please check logs for additional information"),
    /**
     * User Rest Errors
     */
    USER_NOT_FOUND_ERROR("User cannot be found"),
    APPLICATION_FORM_NOT_FOUND_ERROR("Application Form cannot be found"),
    DATA_NOT_FOUND_ERROR("Data cannot be found."),
    /**
     * Auth error codes
     */
    AUTH_TOKEN_EXPIRED_ERROR("JWT token is expired"),
    AUTH_TOKEN_SIGNATURE_ERROR("Invalid JWT signature"),
    AUTH_TOKEN_MALFORMED_JWT_ERROR("Invalid JWT token"),
    AUTH_TOKEN_UNSUPPORTED_JWT_ERROR("JWT token is unsupported");
//    AUTH_TOKEN_EMPTY_JWT_CLAIMS_ERROR("JWT claims string is empty"),
//    FILE_EXCEPTION_MAX_UPLOAD_SIZE_EXCEEDED("MaxUploadSizeExceededException");


    private String description;

    ErrorDescription(String description) {
        this.description = description;
    }

    public String geKey() {
        return name();
    }

    public String getDescription() {
        return this.description;
    }
}