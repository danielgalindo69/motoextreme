package com.motoextreme.motoextreme.exeptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) { super(message); }
}
