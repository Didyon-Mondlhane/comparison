package com.didyon.comparison_api.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
    
    public NotFoundException(String resource, Long id) {
        super(resource + " com ID " + id + " não encontrado");
    }
}