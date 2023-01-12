package Spring.Course.Alyshev.Aaahooly.RestApp.repositories;


import Spring.Course.Alyshev.Aaahooly.RestApp.models.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {

}