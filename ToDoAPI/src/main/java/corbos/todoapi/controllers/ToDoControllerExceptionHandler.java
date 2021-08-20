package corbos.todoapi.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice  //  indicates our class will assist all controllers in our project.
@RestController  // indicates our class is able to serve an HTTP response on behalf of a controller.
public class ToDoControllerExceptionHandler extends ResponseEntityExceptionHandler { // contains a bunch of exception handling code

    private static final String CONSTRAINT_MESSAGE = "Could not save your item. "
            + "Please ensure it is valid and try again.";

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)  // 무슨 exception 을 handling 할 것인지 알려줘야함. - -throws 같은 개념인가?
    // 아 이렇게 하면, 알아서 catch 해서 그 때에 맞게 아래 함수를 적용시켜 주네, crash 없이... 그러면 try catch 안쓰나??
    // 편리한 대신 exception 하나당 딱 하나의 함수만 사용가능.
    public final ResponseEntity<Error> handleSqlException(
            SQLIntegrityConstraintViolationException ex,
            WebRequest request) {
        // 이 안에서 어떻게 responseEntity 를 구성할 것인지 맘대로 정한다.

        Error err = new Error();
        err.setMessage(CONSTRAINT_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}