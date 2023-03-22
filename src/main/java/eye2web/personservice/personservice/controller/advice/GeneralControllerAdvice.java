package eye2web.personservice.personservice.controller.advice;

import eye2web.personservice.personservice.service.exception.IllegalQueryParameterValue;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GeneralControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotYetImplementedException.class)
    public ResponseEntity<Object> handleNotYetImplementedException(final NotYetImplementedException ex, final WebRequest request) {
        log.error(ex.getLocalizedMessage());

        return new ResponseEntity<>("This REST endpoint is not implemented yet. But will be implemented in the future.", HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(IllegalQueryParameterValue.class)
    public ResponseEntity<Object> handleIllegalQueryParameterValue(final IllegalQueryParameterValue ex, final WebRequest request) {
        log.error(ex.getLocalizedMessage());

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<Object> handlePropertyReferenceException(final PropertyReferenceException ex, final WebRequest request) {
        log.error(ex.getLocalizedMessage());

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
