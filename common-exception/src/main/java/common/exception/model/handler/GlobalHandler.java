package common.exception.model.handler;

import common.exception.model.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalHandler extends DefaultErrorAttributes {


    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Map<String, Object>> handler(ApplicationException exception,
                                                      WebRequest webRequest) {
        return of(exception, webRequest);
    }


//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, Object>> handler(RuntimeException exception,
//                                                       WebRequest webRequest) {
//        return of(exception, webRequest);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handle(MethodArgumentNotValidException ex,
                                                      WebRequest request) {

        Map<String, Object> errorAttributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());

        Map<String, Object> invalidFields = new HashMap<>();

        ex.getFieldErrors()
                .forEach(fieldError -> invalidFields.put(fieldError.getField(),
                        fieldError.getDefaultMessage()));

        errorAttributes.put("status", HttpStatus.BAD_REQUEST.value());
        errorAttributes.put("error", invalidFields);
        errorAttributes.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());

        return new ResponseEntity<>(errorAttributes, HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> errorAttributes(HttpStatus httpStatus, String message, WebRequest webRequest) {

        Map<String, Object> errorAttributes = getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());

        errorAttributes.put("error", message);
        errorAttributes.put("status", httpStatus.value());
        errorAttributes.put("path", ((ServletRequestAttributes)webRequest).getRequest().getServletPath());

        return errorAttributes;
    }


    private ResponseEntity<Map<String, Object>> of (ApplicationException exception, WebRequest webRequest) {

        Map<String, Object> errorAttributes  = errorAttributes(exception.getExceptionResponse().getHttpStatus(), exception.getMessage(), webRequest);

        return new ResponseEntity<>(errorAttributes, exception.getExceptionResponse().getHttpStatus());
    }

    private ResponseEntity<Map<String, Object>> of (Exception exception, WebRequest webRequest) {

        Map<String, Object> errorAttributes  = errorAttributes(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), webRequest);

        return new ResponseEntity<>(errorAttributes, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
