package md.pharm.hibernate.institution;

import md.pharm.hibernate.doctor.Doctor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Andrei on 9/4/2015.
 */

@Entity
@Table(name="[TopPharm].[dbo].[Institution]", uniqueConstraints = {
        @UniqueConstraint(columnNames = "Name")})
public class Institution {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Country")
    private String country;

    @Column(name = "City")
    private String city;

    @Column(name = "Street")
    private String street;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "institution", cascade = CascadeType.ALL)
    private Set<Doctor> doctors;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "institution", cascade = CascadeType.ALL)
    private Set<InstitutionComment> institutionComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "institution", cascade = CascadeType.ALL)
    private Set<InstitutionHistory> institutionHistories;

    public Institution(){}

    public Institution(String name, String country, String city, String street) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Set<InstitutionComment> getInstitutionComments() {
        return institutionComments;
    }

    public void setInstitutionComments(Set<InstitutionComment> institutionComments) {
        this.institutionComments = institutionComments;
    }

    public Set<InstitutionHistory> getInstitutionHistories() {
        return institutionHistories;
    }

    public void setInstitutionHistories(Set<InstitutionHistory> institutionHistories) {
        this.institutionHistories = institutionHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Institution that = (Institution) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        return !(street != null ? !street.equals(that.street) : that.street != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        return result;
    }
}
