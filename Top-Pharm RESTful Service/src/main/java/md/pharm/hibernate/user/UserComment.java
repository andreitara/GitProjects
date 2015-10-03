package md.pharm.hibernate.user;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrei on 9/4/2015.
 */

@Entity
@Table(name="[TopPharm].[dbo].[UserComment]")
public class UserComment {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int ID;

    @Column(name = "Date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    private User user;

    @Column(name = "Comment")
    private String comment;


    public UserComment(){
    }

    public UserComment(Date date, User user, String comment) {
        this.date = date;
        this.user = user;
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserComment that = (UserComment) o;

        if (ID != that.ID) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return !(comment != null ? !comment.equals(that.comment) : that.comment != null);

    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
