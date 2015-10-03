package md.pharm.hibernate.task;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.training.Training;
import md.pharm.hibernate.user.User;

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
    @Column(name = "ID")
    private int id;

    @Column(name = "Type")
    private String type;

    @Column(name = "Status")
    private String status;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="[TopPharm].[dbo].[UserTask]", joinColumns=@JoinColumn(name="TaskID"), inverseJoinColumns=@JoinColumn(name="UserID"))
    private Set<User> users;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Duration")
    private Date duration;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="[TopPharm].[dbo].[DoctorTask]", joinColumns=@JoinColumn(name="TaskID"), inverseJoinColumns=@JoinColumn(name="DoctorID"))
    //@ManyToMany(cascade=CascadeType.ALL, mappedBy="tasks")
    private Set<Doctor> doctors;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.ALL)
    private Set<TaskComment> taskComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.ALL)
    private Set<TaskHistory> taskHistories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Training> trainings;


    public Task(){}

    public Task(String type, String status, Set<User> users, Date date, Date duration, Set<Doctor> doctors) {
        this.type = type;
        this.status = status;
        this.users = users;
        this.date = date;
        this.duration = duration;
        this.doctors = doctors;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
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

    public Set<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (type != null ? !type.equals(task.type) : task.type != null) return false;
        if (status != null ? !status.equals(task.status) : task.status != null) return false;
        if (date != null ? !date.equals(task.date) : task.date != null) return false;
        return !(duration != null ? !duration.equals(task.duration) : task.duration != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }
}
