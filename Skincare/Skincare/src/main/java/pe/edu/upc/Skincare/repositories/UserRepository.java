package pe.edu.upc.Skincare.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.Skincare.entities.UserLogin;

public interface UserRepository extends JpaRepository<UserLogin, Long> {

UserLogin findByEmail (String Email);


}
