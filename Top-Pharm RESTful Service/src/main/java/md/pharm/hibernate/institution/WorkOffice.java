package md.pharm.hibernate.institution;

import md.pharm.hibernate.doctor.Doctor;

import javax.persistence.*;

/**
 * Created by Andrei on 10/4/2015.
 */

@Entity
@Table(name="[TopPharm].[dbo].[WorkOffice]")
public class WorkOffice {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "block")
    private String block;

    @Column(name = "floor")
    private int floor;

    @Column(name = "officeNumber")
    private String officeNumber;

    @Column(name = "descripiton")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "institutionID")
    private Institution institution;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctorID")
    private Doctor doctor;

    public WorkOffice(){}
}
