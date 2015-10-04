package md.pharm.hibernate.product;

import javax.persistence.*;

/**
 * Created by Andrei on 10/4/2015.
 */

@Entity
@Table(name="[TopPharm].[dbo].[Objective]")
public class Objective {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "productID")
    private Product product;

    public Objective(){}

    public Objective(String name, String description, Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Objective objective = (Objective) o;

        if (id != objective.id) return false;
        if (name != null ? !name.equals(objective.name) : objective.name != null) return false;
        if (description != null ? !description.equals(objective.description) : objective.description != null)
            return false;
        return !(product != null ? !product.equals(objective.product) : objective.product != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }
}
