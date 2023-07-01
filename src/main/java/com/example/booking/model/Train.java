package com.example.booking.model;

import com.example.booking.enums.ResidenceFacilities;
import com.example.booking.enums.TrainFacilities;
import com.example.booking.enums.TrainType;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "ut_train",schema = "public",catalog = "booking")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE ut_train SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "train_number",nullable = false)
    private Integer trainNumber;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "source_city_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private City sourceCity;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "target_city_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private City targetCity;
    @Column(name = "depart_date",nullable = false)
    private Date departDate;
    @Column(name = "return_date")
    private Date returnDate;
    @Column(name = "num_of_passenger",nullable = false)
    private Integer numOfPassenger;
    @ElementCollection(targetClass = TrainFacilities.class)
    @JoinTable(name = "ut_train_facilities", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "train_facilities", nullable = false)
    @Enumerated(EnumType.STRING)
    private Collection<TrainFacilities> trainFacilities;
    @Enumerated(EnumType.STRING)
    @Column(name = "train_type", nullable = false)
    private TrainType trainType;
    @Column(name = "logo",nullable = false)
    private String logo;
    @OneToMany(mappedBy = "train",
            cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Photo> photos;
    @Column(name = "cancel",nullable = false,columnDefinition = "boolean default false")
    private boolean cancel;
    @Column(name = "deleted",nullable = false,columnDefinition = "boolean default false")
    private boolean deleted;
    @OneToMany(mappedBy = "train",
            cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Passenger> passengers;
}
