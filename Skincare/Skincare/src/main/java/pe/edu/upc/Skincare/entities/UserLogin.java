package pe.edu.upc.Skincare.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "userlogins")
@Data
@NoArgsConstructor
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @OneToMany(mappedBy = "userlogin")
    private List<UserConsultant> userConsultants;

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
