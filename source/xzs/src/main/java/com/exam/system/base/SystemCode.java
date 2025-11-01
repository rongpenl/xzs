package com.exam.system.base;

public enum SystemCode {
    /**
     * OK
     */
    OK(1, "Success"),
    /**
     * AccessTokenError
     */
    AccessTokenError(400, "Access token expired"),
    /**
     * UNAUTHORIZED
     */
    UNAUTHORIZED(401, "User not logged in"),
    /**
     * UNAUTHORIZED
     */
    AuthError(402, "Username or password incorrect"),
    /**
     * InnerError
     */
    InnerError(500, "Internal server error"),
    /**
     * ParameterValidError
     */
    ParameterValidError(501, "Parameter validation error"),

    /**
     * AccessDenied
     */
    AccessDenied(502, "Access denied");

    /**
     * The Code.
     */
    int code;
    /**
     * The Message.
     */
    String message;

    SystemCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
