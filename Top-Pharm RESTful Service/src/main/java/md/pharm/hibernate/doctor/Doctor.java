package md.pharm.hibernate.doctor;

import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.task.Task;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Andrei on 9/4/2015.
 */

@Entity
@Table(name="[TopPharm].[dbo].[Doctor]")
public class Doctor {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "Type")
    private String type;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InstitutionID")
    private Institution institution;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor", cascade = CascadeType.ALL)
    private Set<DoctorComment> doctorComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor", cascade = CascadeType.ALL)
    private Set<DoctorHistory> doctorHistories;

    @ManyToMany(mappedBy="doctors")
    //@ManyToMany(cascade=CascadeType.ALL)
    //@JoinTable(name="DoctorTask", joinColumns=@JoinColumn(name="DoctorID"), inverseJoinColumns=@JoinColumn(name="TaskID"))
    private Set<Task> tasks;

    @Column(name = "Description")
    private String description;

    public Doctor(){}

    public Doctor(String type, String firstName, String lastName, Institution institution, String description) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.institution = institution;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (type != null ? !type.equals(doctor.type) : doctor.type != null) return false;
        if (firstName != null ? !firstName.equals(doctor.firstName) : doctor.firstName != null) return false;
        if (lastName != null ? !lastName.equals(doctor.lastName) : doctor.lastName != null) return false;
        return !(description != null ? !description.equals(doctor.description) : doctor.description != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
