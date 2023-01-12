package Spring.Course.Alyshev.Aaahooly.RestApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController// каждый метод в этом классе будет отмечен как @ResponseBody
@RequestMapping("/api")
public class FirstRestController {

    @ResponseBody// <-- в ресте теперь мы возвращаем данные а не представления
    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello world";
    }


}
