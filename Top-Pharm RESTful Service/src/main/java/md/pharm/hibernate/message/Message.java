package md.pharm.hibernate.message;

import md.pharm.hibernate.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrei on 9/5/2015.
 */

@Entity
@Table(name="[TopPharm].[dbo].[Message]")
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FromID")
    private User from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ToID")
    private User to;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Message")
    private String message;

    public Message(){}

    public Message(User from, User to, Date date, String message) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (id != message1.id) return false;
        if (date != null ? !date.equals(message1.date) : message1.date != null) return false;
        return !(message != null ? !message.equals(message1.message) : message1.message != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
