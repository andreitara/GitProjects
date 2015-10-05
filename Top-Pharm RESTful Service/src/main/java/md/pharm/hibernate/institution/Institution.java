package md.pharm.hibernate.institution;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.common.Address;
import md.pharm.hibernate.doctor.Doctor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Andrei on 9/4/2015.
 */

@Entity
@Table(name="[TopPharm].[dbo].[Institution]", uniqueConstraints = {
        @UniqueConstraint(columnNames = "longName")})
public class Institution {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "longName")
    private String longName;

    @Column(name = "shortName")
    private String shortName;

    @Column(name = "phone1")
    private String phone1;

    @Column(name = "phone2")
    private String phone2;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<WorkOffice> workOffices;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<InstitutionComment> institutionComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<InstitutionHistory> institutionHistories;

    public Institution(){}

    public Institution(String longName, String shortName, String phone1, String phone2, Address address) {
        this.longName = longName;
        this.shortName = shortName;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<WorkOffice> getWorkOffices() {
        return workOffices;
    }

    public void setWorkOffices(Set<WorkOffice> workOffices) {
        this.workOffices = workOffices;
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
        if (longName != null ? !longName.equals(that.longName) : that.longName != null) return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;
        if (phone1 != null ? !phone1.equals(that.phone1) : that.phone1 != null) return false;
        if (phone2 != null ? !phone2.equals(that.phone2) : that.phone2 != null) return false;
        return !(address != null ? !address.equals(that.address) : that.address != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (longName != null ? longName.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (phone1 != null ? phone1.hashCode() : 0);
        result = 31 * result + (phone2 != null ? phone2.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
