package edu.project3.shared;

public enum StatusCode {
    OK,
    NOT_MODIFIED,
    NOT_FOUND;

    @SuppressWarnings("MagicNumber")
    public static StatusCode fromInteger(int x) {
        return switch (x) {
            case 200 -> OK;
            case 304 -> NOT_MODIFIED;
            case 404 -> NOT_FOUND;
            default -> null;
        };
    }
}
