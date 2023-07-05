package com.example.booking.model;

import com.example.booking.enums.ResidenceFacilities;
import com.example.booking.enums.ResidenceType;
import com.example.booking.enums.StarType;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "ut_residence",schema = "public",catalog = "booking")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE ut_residence SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Residence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "residence_number",nullable = false)
    private Integer residenceNumber;
    @Column(name = "name",nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "residence_type",nullable = false)
    private ResidenceType residenceType;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "city_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private City city;
    @Column(name = "depart_date",nullable = false)
    private Date departDate;
    @Column(name = "return_date")
    private Date returnDate;
    @Column(name = "num_of_passenger",nullable = false)
    private Integer numOfPassenger;
    @ElementCollection(targetClass = ResidenceFacilities.class)
    @JoinTable(name = "ut_residence_facilities", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "residence_facilities", nullable = false)
    @Enumerated(EnumType.STRING)
    private Collection<ResidenceFacilities> residenceFacilities;
    @Enumerated(EnumType.STRING)
    @Column(name = "star_type",nullable = false)
    private StarType starType;
    @Column(name = "logo",nullable = false)
    private String logo;
    @OneToMany(mappedBy = "residence",
            cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Photo> photos;
    @Column(name = "cancel",nullable = false,columnDefinition = "boolean default false")
    private boolean cancel;
    @Column(name = "price",nullable = false)
    private long price;
    @Column(name = "deleted",nullable = false,columnDefinition = "boolean default false")
    private boolean deleted;
    @OneToMany(mappedBy = "residence",
            cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Passenger> passengers;
}
