package es.udc.paproject.backend.model.entities;
import javax.persistence.*;

@Entity
public class Category {

    private Long categoryId;
    private String categoryName;


    public Category() {}
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return categoryId;
    }

    public void setId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getName() {
        return categoryName;
    }
    public void setName(String categoryName) {
        this.categoryName = categoryName;
    }
}
