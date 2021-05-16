package com.hankmew.benchmark.archetype.deploy.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Supported Exceptions Exception           HTTP Status Code
 * AsyncRequestTimeoutException             503 (SC_SERVICE_UNAVAILABLE)
 * ConversionNotSupportedException          500 (SC_INTERNAL_SERVER_ERROR)
 * MissingPathVariableException             500 (SC_INTERNAL_SERVER_ERROR)
 * HttpMessageNotWritableException          500 (SC_INTERNAL_SERVER_ERROR)
 * HttpMediaTypeNotSupportedException       415 (SC_UNSUPPORTED_MEDIA_TYPE)
 * HttpMediaTypeNotAcceptableException      406 (SC_NOT_ACCEPTABLE)
 * HttpRequestMethodNotSupportedException   405 (SC_METHOD_NOT_ALLOWED)
 * NoHandlerFoundException                  404 (SC_NOT_FOUND)
 * MissingServletRequestParameterException  400 (SC_BAD_REQUEST)
 * ServletRequestBindingException           400 (SC_BAD_REQUEST)
 * TypeMismatchException                    400 (SC_BAD_REQUEST)
 * HttpMessageNotReadableException          400 (SC_BAD_REQUEST)
 * MethodArgumentNotValidException          400 (SC_BAD_REQUEST)
 * MissingServletRequestPartException       400 (SC_BAD_REQUEST)
 * BindException                            400 (SC_BAD_REQUEST)
 */

@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleUnknown(Exception e) {
        return "500";
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Object handle503(Exception e) {
        return "503";
    }

    @ExceptionHandler({ConversionNotSupportedException.class,
            MissingPathVariableException.class,
            HttpMessageNotWritableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handle500(Exception e) {
        return "500";
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Object handle415(Exception e) {
        return "415";
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Object handle406(Exception e) {
        return "406";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Object handle405(Exception e) {
        return "405";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handle404(Exception e) {
        return "404";
    }

    @ExceptionHandler({MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestPartException.class,
            BindException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handle400(Exception e) {
        return "400";
    }
}
