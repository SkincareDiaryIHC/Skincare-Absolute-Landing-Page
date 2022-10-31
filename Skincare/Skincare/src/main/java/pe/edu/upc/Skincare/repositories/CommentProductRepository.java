package pe.edu.upc.Skincare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.Skincare.entities.CommentProduct;

public interface CommentProductRepository extends JpaRepository<CommentProduct, Long> {

    CommentProduct findByPoints (Number Points);

}
