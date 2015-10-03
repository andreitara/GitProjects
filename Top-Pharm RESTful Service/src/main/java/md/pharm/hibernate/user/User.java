package md.pharm.hibernate.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.connection.Connection;
import md.pharm.hibernate.message.Message;
import md.pharm.hibernate.task.Task;
import md.pharm.hibernate.user.permission.Permission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Andrei on 9/3/2015.
 */

@Entity
@Table(name="[TopPharm].[dbo].[User]", uniqueConstraints = {
        @UniqueConstraint(columnNames = "Login")})
public class User{

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

    @Column(name = "Login")
    private String login;

    @Column(name = "Password")
    private String password;

    @Column(name = "Email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Connection connection;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<UserComment> userComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<UserHistory> userHistories;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="users", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Task> tasks;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Permission permission;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "from", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Message> sendMessages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "to", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Message> receivedMessages;

    public User() {
    }

    public User(String type, String firstName, String lastName, String loginID, String password, String email) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = loginID;
        this.password = password;
        this.email = email;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Set<UserComment> getUserComments() {
        return userComments;
    }

    public void setUserComments(Set<UserComment> userComments) {
        this.userComments = userComments;
    }

    public Set<UserHistory> getUserHistories() {
        return userHistories;
    }

    public void setUserHistories(Set<UserHistory> userHistories) {
        this.userHistories = userHistories;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Message> getSendMessages() {
        return sendMessages;
    }

    public void setSendMessages(Set<Message> sendMessages) {
        this.sendMessages = sendMessages;
    }

    public Set<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(Set<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (type != null ? !type.equals(user.type) : user.type != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        return !(login != null ? !login.equals(user.login) : user.login != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public void createDefaultPermission(){
        Permission permission = new Permission(this,true,false,true,false,true,false);
        this.setPermission(permission);
    }

}
