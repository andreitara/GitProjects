package md.pharm.hibernate.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.institution.Office;
import md.pharm.hibernate.task.Task;

import javax.persistence.*;
import java.util.Set;
import java.util.Date;

/**
 * Created by Andrei on 9/4/2015.
 */

@Entity
@Table(name="[TopPharm].[dbo].[Doctor]")
public class Doctor {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "phone1")
    private String phone1;

    @Column(name = "phone2")
    private String phone2;

    @Column(name = "email")
    private String email;

    @Column(name = "loyaltyCategory")
    private String loyaltyCategory;

    @Column(name = "saleCategory")
    private String saleCategory;

    @Column(name = "generalCategory")
    private String generalCategory;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Office> offices;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<DoctorComment> doctorComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<DoctorHistory> doctorHistories;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="doctors", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Task> tasks;

    public Doctor(){}

    public Doctor(String firstName, String lastName, String specialty, Date birthDate, String phone1, String phone2, String email, String loyaltyCategory, String saleCategory, String generalCategory, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.birthDate = birthDate;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.loyaltyCategory = loyaltyCategory;
        this.saleCategory = saleCategory;
        this.generalCategory = generalCategory;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoyaltyCategory() {
        return loyaltyCategory;
    }

    public void setLoyaltyCategory(String loyaltyCategory) {
        this.loyaltyCategory = loyaltyCategory;
    }

    public String getSaleCategory() {
        return saleCategory;
    }

    public void setSaleCategory(String saleCategory) {
        this.saleCategory = saleCategory;
    }

    public String getGeneralCategory() {
        return generalCategory;
    }

    public void setGeneralCategory(String generalCategory) {
        this.generalCategory = generalCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Office> getOffices() {
        return offices;
    }

    @JsonIgnore
    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }

    public Set<DoctorComment> getDoctorComments() {
        return doctorComments;
    }

    public void setDoctorComments(Set<DoctorComment> doctorComments) {
        this.doctorComments = doctorComments;
    }

    public Set<DoctorHistory> getDoctorHistories() {
        return doctorHistories;
    }

    public void setDoctorHistories(Set<DoctorHistory> doctorHistories) {
        this.doctorHistories = doctorHistories;
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

        Doctor doctor = (Doctor) o;

        if (id != doctor.id) return false;
        if (firstName != null ? !firstName.equals(doctor.firstName) : doctor.firstName != null) return false;
        if (lastName != null ? !lastName.equals(doctor.lastName) : doctor.lastName != null) return false;
        if (specialty != null ? !specialty.equals(doctor.specialty) : doctor.specialty != null) return false;
        if (birthDate != null ? !birthDate.equals(doctor.birthDate) : doctor.birthDate != null) return false;
        if (phone1 != null ? !phone1.equals(doctor.phone1) : doctor.phone1 != null) return false;
        if (phone2 != null ? !phone2.equals(doctor.phone2) : doctor.phone2 != null) return false;
        if (email != null ? !email.equals(doctor.email) : doctor.email != null) return false;
        if (loyaltyCategory != null ? !loyaltyCategory.equals(doctor.loyaltyCategory) : doctor.loyaltyCategory != null)
            return false;
        if (saleCategory != null ? !saleCategory.equals(doctor.saleCategory) : doctor.saleCategory != null)
            return false;
        if (generalCategory != null ? !generalCategory.equals(doctor.generalCategory) : doctor.generalCategory != null)
            return false;
        return !(description != null ? !description.equals(doctor.description) : doctor.description != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (specialty != null ? specialty.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (phone1 != null ? phone1.hashCode() : 0);
        result = 31 * result + (phone2 != null ? phone2.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (loyaltyCategory != null ? loyaltyCategory.hashCode() : 0);
        result = 31 * result + (saleCategory != null ? saleCategory.hashCode() : 0);
        result = 31 * result + (generalCategory != null ? generalCategory.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
