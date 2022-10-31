package pe.edu.upc.Skincare.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ComentProducts")
@Data
@NoArgsConstructor
public class CommentProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user;
    private Number point;
    private String Comment;

    @ManyToOne
    @JoinColumn(name="userConsultant")
    private UserConsultant userConsultant;


    public CommentProduct(String user, Number point, String comment) {
        this.user = user;
        this.point = point;
        Comment = comment;
    }
}
