package com.example.journalist_media_manager_springboot.error;

/**
 * Contains cross applications error codes
 *
 *
 */
public class ErrorCodes {

    /**
     * Gateway related error codes 1xxx
     */
    public static class GatewayApi {

        /**
         * gateway api unknown error
         */
        public static Integer Generic = 1000;
        /**
         * gateway api prefilter generic error
         */
        public static Integer PreFilterGeneric = 1001;

        /**
         * gateway api prefilter error json string to AuthError converting
         */
        public static Integer PreFilterCantConvertAuthError = 1002;

        /**
         * gateway api Token Expired Error
         */
        public static Integer TokenExpiredAuthError = 1003;

        /**
         * gateway api Token Signature Error
         */
        public static Integer TokenSignatureAuthError = 1004;

        /**
         * gateway api Token Malformed Jwt Error
         */
        public static Integer TokenMalformedJwtAuthError = 1005;

        /**
         * gateway api Token Unsupported Jwt Error
         */
        public static Integer TokenUnsupportedJwtAuthError = 1006;

        /**
         * gateway api Token Empty Claims Jwt Error
         */
        public static Integer TokenEmptyJwtClaimsAuthError = 1006;

        /**
         * gateway api Endpoint cannot be found Error
         */
        public static Integer EndpointNotFound = 1008;

        /**
         * gateway api auth headers is missing or incorrect
         */
        public static Integer MissingAuthHeaders = 1010;

        /**
         * gateway api Token Empty Claims Jwt Error
         */
        public static Integer UnauthorizedExceptionError = 1011;
    }

    /**
     * Auth related error codes 2xxx
     */
    public static class AuthApi {

        /**
         * Auth api unknown error
         */
        public static Integer Generic = 2000;

        /**
         * GSIS Authentication 100 error
         */
        public static Integer Handled_100 = 2001;

        /**
         * GSIS Authentication 101 error
         */
        public static Integer Handled_101 = 2002;

        /**
         * GSIS Authentication 102 error
         */
        public static Integer Handled_102 = 2003;

        /**
         * GSIS Authentication 103 error
         */
        public static Integer Handled_103 = 2004;

        /**
         * GSIS Authentication 104 error
         */
        public static Integer Handled_104 = 2005;

        /**
         * GSIS Authentication 105 error
         */
        public static Integer Handled_105 = 2006;

        /**
         * GSIS Authentication 106 error
         */
        public static Integer Handled_106 = 2007;
    }

    /**
     * PressboxApi related error codes 3xxx
     */
    public static class PressboxApi {

        /**
         * PressboxApi api unknown error
         */
        public static Integer Generic = 3000;

        /**
         * PressboxApi Invalid Parameter Values
         */
        public static Integer InvalidParamVal = 3001;
        /**
         * PressboxApi Unexpected Error during request process
         */
        public static Integer ProcessError = 3002;

        public static Integer ResourceNotFoundError = 3003;

        public static Integer NotAuthorized = 3004;

        public static Integer ValidationFail = 3005;

        public static Integer UnderConstruction = 3006;

        public static Integer Misconfiguration = 3007;

        public static Integer FileSizeExceeded = 3010;

    }


    /**
     * User related errors
     */
    public static class User {

        public static final ApiError Generic = new ApiError(4001, "Generic User operation related error");

        public static final ApiError UserNotFoundError = new ApiError(4002, "User cannot be found");

        public static final ApiError UserAlreadyExistsOnDatabase = new ApiError(4003, "User creation can't be performed, user already exists");
    }

    /**
     * Application Form related errors
     */
    public static class ApplicationForm {

        public static final ApiError Generic = new ApiError(6000, "Generic Application Form error");

        public static final ApiError ApplicationFormStatusOverJBPMActionError = new ApiError(6001, "Application Form status does not permit specific action");
    }

}