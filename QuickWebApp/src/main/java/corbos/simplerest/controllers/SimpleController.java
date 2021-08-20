package corbos.simplerest.controllers;

// Spring MVC types. Comes with spring-boot-starter-web
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController  // Tells Spring MVC to register it with Spring application context + methods for REST requests.
@RequestMapping("/api")  // Mapping. Given a URL, HTTP method... triggers a specific controller method.
public class SimpleController {

    @GetMapping  // Tells the Spring MVC that this is for handling get requests.
    public String[] helloWorld() {
        String[] result = {"Hello", "World", "!"};
        return result;
    }

    @PostMapping("/calculate")
    public String calculate(int operand1, String operator, int operand2) {
        int result = 0;
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
            default:
                String message = String.format("operator '%s' is invalid", operator);
                throw new IllegalArgumentException(message);
        }
        return String.format("%s %s %s = %s", operand1, operator, operand2, result);
    }

    @DeleteMapping("/resource/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        // This is where we would use our id to delete.
    }


}