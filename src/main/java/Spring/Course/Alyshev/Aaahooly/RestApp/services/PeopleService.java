package Spring.Course.Alyshev.Aaahooly.RestApp.services;

import Spring.Course.Alyshev.Aaahooly.RestApp.models.People;
import Spring.Course.Alyshev.Aaahooly.RestApp.repositories.PeopleRepository;
import Spring.Course.Alyshev.Aaahooly.RestApp.util.PeopleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<People> findAll() {
        return peopleRepository.findAll();
    }

    public People findOne(int id) {
        return peopleRepository.findById(id).orElseThrow(PeopleNotFoundException::new);
    }

    @Transactional
    public  void save(People people) {
        peopleRepository.save(people);
    }
}
