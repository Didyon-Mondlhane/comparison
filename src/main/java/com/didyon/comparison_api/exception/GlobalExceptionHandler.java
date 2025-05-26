package com.didyon.comparison_api.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(NotFoundException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", ex.getMessage());
        mav.addObject("status", 404);
        mav.setViewName("error");
        return mav;
    }
    
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Ocorreu um erro inesperado");
        mav.addObject("status", 500);
        mav.setViewName("error");
        return mav;
    }
}