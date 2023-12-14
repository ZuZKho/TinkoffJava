package edu.project3.shared;

public final class StatusCodes {

    private StatusCodes() {
    }

    public static String getCode(Integer code) {
        StatusCode statusCode = StatusCode.fromInteger(code);

        return switch (statusCode) {
            case OK -> "OK";
            case NOT_MODIFIED -> "Not Modified";
            case NOT_FOUND -> "Not found";
            default -> "-";
        };
    }

}
