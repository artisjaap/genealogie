package be.genealogie.controller.errorhandling;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@RequiredArgsConstructor
public class RestResponseEntityExceptionHandler {

//    private final ClientEventDispatcher clientEventDispatcher;
//    private final AuthenticatieService authenticatieService;

//    @ExceptionHandler({ValidatieException.class})
//    public ResponseEntity<Object> handleValidatieException(ValidatieException ex, WebRequest request) {
//        return ResponseEntity.badRequest().body(ex.getErrors());
//    }

    @ExceptionHandler({NoResourceFoundException.class})
    public Object handleValidatieException(NoResourceFoundException ex, WebRequest request) {
        if (((ServletWebRequest) request).getRequest().getMethod().equalsIgnoreCase(HttpMethod.GET.name())) {
            ((ServletWebRequest) request).getResponse().setStatus(HttpStatus.OK.value()); // optional.
            return "forward:/index.html";
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @ExceptionHandler({Throwable.class})
//    public void handleException(Throwable ex, WebRequest request) {
//        final String door = authenticatieService.aangemeldeGebruiker().map(gebruiker -> gebruiker.firstName() + " " + gebruiker.lastName()).orElse("");
//        clientEventDispatcher.dispatch(ErrorEvent.startBuilder().door(door).message(ex.getMessage()).stacktrace(ExceptionUtils.getStackTrace(ex)).build());
//
//    }
}
