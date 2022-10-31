package pe.edu.upc.Skincare.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.util.List;

@Entity
@Table(name = "userConsultants")
@Data
@NoArgsConstructor
public class UserConsultant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String FirstName;
    private String Lastname;
    private String Description;
    private String Contacto;
    private Number NumberPhone;

    @ManyToOne
    @JoinColumn(name="UserLogin_id")
    private UserLogin userlogin;

   @OneToMany(mappedBy = "userConsultant")
   private List<CommentProduct> commentProducts;


    public UserConsultant(String firstName, String lastname, String description, String contacto, Number numberPhone) {
        FirstName = firstName;
        Lastname = lastname;
        Description = description;
        Contacto = contacto;
        NumberPhone = numberPhone;
    }
}
