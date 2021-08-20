package corbos.todoapi.controllers;

import corbos.todoapi.data.ToDoDao;
import corbos.todoapi.models.ToDo;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    private final ToDoDao dao;  // I don't need to autowire. Spring Boot assumes it.

    public ToDoController(ToDoDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<ToDo> all() {
        return dao.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToDo create(@RequestBody ToDo todo) {  // 원래 RequestBody의 짝은 ResposneBody. 근데 RestController에서는 자동적용됨.
        // 나중에 그냥 Controller에서는 입력해 줘야 한다.
        return dao.add(todo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> findById(@PathVariable int id) {
        // 자바 obj를 직접적으로 보낼 수 없으니 ResponseEntity를 써서 보낸다.
        // PathVariable은 주소에서 직접 id를 끌어오라는 소리. 그리고 type설정해주고.
        ToDo result = dao.findById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody ToDo todo) {
        // 왜 그냥 obj를 보내지 않느냐면, 주소변경없이 그냥 content만 보내기 싫기 때문. /api/to.do/id 에서 작업하는 것이 convention이다.

        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (id != todo.getId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (dao.update(todo)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (dao.deleteById(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}