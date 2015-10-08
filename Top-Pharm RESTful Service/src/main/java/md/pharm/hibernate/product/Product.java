package md.pharm.hibernate.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.doctor.DoctorComment;
import md.pharm.hibernate.doctor.DoctorHistory;
import md.pharm.hibernate.task.Task;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Andrei on 10/4/2015.
 */
@Entity
@Table(name="[TopPharm].[dbo].[Product]", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")})
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "boxQuantity")
    private String boxQuantity;

    @Column(name = "quantity")
    private String averagePrice;

    @Column(name = "message")
    private String message;

    @Column(name = "priority")
    private int priority;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Objective> objectives;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="products", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Task> tasks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ProductComment> productComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ProductHistory> productHistories;

    public Product(){}

    public Product(String name, String boxQuantity, String averagePrice, String message, int priority) {
        this.name = name;
        this.boxQuantity = boxQuantity;
        this.averagePrice = averagePrice;
        this.message = message;
        this.priority = priority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoxQuantity() {
        return boxQuantity;
    }

    public void setBoxQuantity(String boxQuantity) {
        this.boxQuantity = boxQuantity;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Set<Objective> getObjectives() {
        return objectives;
    }

    public void setObjectives(Set<Objective> objectives) {
        this.objectives = objectives;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (priority != product.priority) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (boxQuantity != null ? !boxQuantity.equals(product.boxQuantity) : product.boxQuantity != null) return false;
        if (averagePrice != null ? !averagePrice.equals(product.averagePrice) : product.averagePrice != null)
            return false;
        return !(message != null ? !message.equals(product.message) : product.message != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (boxQuantity != null ? boxQuantity.hashCode() : 0);
        result = 31 * result + (averagePrice != null ? averagePrice.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + priority;
        return result;
    }
}
