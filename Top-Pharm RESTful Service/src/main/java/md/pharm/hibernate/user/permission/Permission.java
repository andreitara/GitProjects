package md.pharm.hibernate.user.permission;

import md.pharm.hibernate.user.User;

import javax.persistence.*;

/**
 * Created by Andrei on 9/28/2015.
 */

@Entity
@Table(name="[TopPharm].[dbo].[Permission]")
public class Permission{

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserID")
    private User user;

    @Column(name = "ReadUsers")
    private boolean readUsers;

    @Column(name = "WriteUsers")
    private boolean writeUsers;

    @Column(name = "ReadMedicalEntity")
    private boolean readMedicalEntity;

    @Column(name = "WriteMedicalEntity")
    private boolean writeMedicalEntity;

    @Column(name = "ReadTasks")
    private boolean readTasks;

    @Column(name = "WriteTasks")
    private boolean writeTasks;

    public Permission(){}

    public Permission(User user, boolean readUsers, boolean writeUsers, boolean readMedicalEntity, boolean writeMedicalEntity, boolean readTasks, boolean writeTasks) {
        this.user = user;
        this.readUsers = readUsers;
        this.writeUsers = writeUsers;
        this.readMedicalEntity = readMedicalEntity;
        this.writeMedicalEntity = writeMedicalEntity;
        this.readTasks = readTasks;
        this.writeTasks = writeTasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isReadUsers() {
        return readUsers;
    }

    public void setReadUsers(boolean readUsers) {
        this.readUsers = readUsers;
    }

    public boolean isWriteUsers() {
        return writeUsers;
    }

    public void setWriteUsers(boolean writeUsers) {
        this.writeUsers = writeUsers;
    }

    public boolean isReadMedicalEntity() {
        return readMedicalEntity;
    }

    public void setReadMedicalEntity(boolean readMedicalEntity) {
        this.readMedicalEntity = readMedicalEntity;
    }

    public boolean isWriteMedicalEntity() {
        return writeMedicalEntity;
    }

    public void setWriteMedicalEntity(boolean writeMedicalEntity) {
        this.writeMedicalEntity = writeMedicalEntity;
    }

    public boolean isReadTasks() {
        return readTasks;
    }

    public void setReadTasks(boolean readTasks) {
        this.readTasks = readTasks;
    }

    public boolean isWriteTasks() {
        return writeTasks;
    }

    public void setWriteTasks(boolean writeTasks) {
        this.writeTasks = writeTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        if (id != that.id) return false;
        if (readUsers != that.readUsers) return false;
        if (writeUsers != that.writeUsers) return false;
        if (readMedicalEntity != that.readMedicalEntity) return false;
        if (writeMedicalEntity != that.writeMedicalEntity) return false;
        if (readTasks != that.readTasks) return false;
        if (writeTasks != that.writeTasks) return false;
        return !(user != null ? !user.equals(that.user) : that.user != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (readUsers ? 1 : 0);
        result = 31 * result + (writeUsers ? 1 : 0);
        result = 31 * result + (readMedicalEntity ? 1 : 0);
        result = 31 * result + (writeMedicalEntity ? 1 : 0);
        result = 31 * result + (readTasks ? 1 : 0);
        result = 31 * result + (writeTasks ? 1 : 0);
        return result;
    }
}
