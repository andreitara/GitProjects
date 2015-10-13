package md.pharm.hibernate.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.product.Product;
import md.pharm.hibernate.user.User;
//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Andrei on 9/5/2015.
 */
@Entity
@Table(name="[TopPharm].[dbo].[Task]")
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "visitNumbers")
    private int visitNumbers;

    @Column(name = "date")
    private Date date;

    @Column(name = "duration")
    private Date duration;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentTask")
    @JsonIgnore
    private Task parentTask;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentTask")
    @JsonIgnore
    private Set<Task> childTasks;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="[TopPharm].[dbo].[UserTask]", joinColumns=@JoinColumn(name="taskID"), inverseJoinColumns=@JoinColumn(name="userID"))
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="[TopPharm].[dbo].[DoctorTask]", joinColumns=@JoinColumn(name="taskID"), inverseJoinColumns=@JoinColumn(name="doctorID"))
    private Set<Doctor> doctors;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "institutionID")
    private Institution institution;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private Set<TaskComment> taskComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private Set<TaskHistory> taskHistories;

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.ALL)
    //private Set<Training> trainings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="[TopPharm].[dbo].[ProductTask]", joinColumns=@JoinColumn(name="taskID"), inverseJoinColumns=@JoinColumn(name="productID"))
    private Set<Product> products;

    public Task(){}

    public Task(String name, String type, String status, int visitNumbers, Date date, Date duration, String description) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.visitNumbers = visitNumbers;
        this.date = date;
        this.duration = duration;
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVisitNumbers() {
        return visitNumbers;
    }

    public void setVisitNumbers(int visitNumbers) {
        this.visitNumbers = visitNumbers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public Set<Task> getChildTasks() {
        return childTasks;
    }

    public void setChildTasks(Set<Task> childTasks) {
        this.childTasks = childTasks;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Set<TaskComment> getTaskComments() {
        return taskComments;
    }

    public void setTaskComments(Set<TaskComment> taskComments) {
        this.taskComments = taskComments;
    }

    public Set<TaskHistory> getTaskHistories() {
        return taskHistories;
    }

    public void setTaskHistories(Set<TaskHistory> taskHistories) {
        this.taskHistories = taskHistories;
    }

   // public Set<Training> getTrainings() {
   //     return trainings;
   // }

   // public void setTrainings(Set<Training> trainings) {
   //     this.trainings = trainings;
   // }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (visitNumbers != task.visitNumbers) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (type != null ? !type.equals(task.type) : task.type != null) return false;
        if (status != null ? !status.equals(task.status) : task.status != null) return false;
        if (date != null ? !date.equals(task.date) : task.date != null) return false;
        if (duration != null ? !duration.equals(task.duration) : task.duration != null) return false;
        return !(description != null ? !description.equals(task.description) : task.description != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + visitNumbers;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
