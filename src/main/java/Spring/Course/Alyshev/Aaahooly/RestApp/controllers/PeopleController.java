package Spring.Course.Alyshev.Aaahooly.RestApp.controllers;

import Spring.Course.Alyshev.Aaahooly.RestApp.models.People;
import Spring.Course.Alyshev.Aaahooly.RestApp.services.PeopleService;
import Spring.Course.Alyshev.Aaahooly.RestApp.util.PeopleErrorResponse;
import Spring.Course.Alyshev.Aaahooly.RestApp.util.PeopleNotCreatedException;
import Spring.Course.Alyshev.Aaahooly.RestApp.util.PeopleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public List<People> findAll() {
        return peopleService.findAll();//jackson конвертирует объекты в json
    }

    @GetMapping("/{id}")
    public People getPerson(@PathVariable("id") int id) {
        return peopleService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid People people,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PeopleNotCreatedException(errorMsg.toString());
        }
        peopleService.save(people);
        //отправляем http с пустым телом и со статусом 200
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    //Этот метод предназначен для внутреннего использования
    //Метод нужен для обработки ошибок, т.к. если в json прилетит null приложение крашнется
    @ExceptionHandler
    private ResponseEntity<PeopleErrorResponse> handleException(PeopleNotFoundException e) {
        PeopleErrorResponse response = new PeopleErrorResponse( "Person with this id wasn't found!",
                System.currentTimeMillis()
        );
        //В HTTP ответе - тело ответа
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);//404 статуч
     }

    @ExceptionHandler
    private ResponseEntity<PeopleErrorResponse> handleException(PeopleNotCreatedException e) {
        PeopleErrorResponse response = new PeopleErrorResponse( e.getMessage() ,
                System.currentTimeMillis()
        );
        //В HTTP ответе - тело ответа
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);//404 статуч
    }

}
