package com.example.booking.model;

import com.example.booking.enums.AirplaneType;
import com.example.booking.enums.AirplaneClassType;
import com.example.booking.enums.AirplaneTicketType;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "ut_airplane",schema = "public",catalog = "booking")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE ut_airplane SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "flight_number",nullable = false)
    private Integer flightNumber;
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
    @Column(name = "price",nullable = false)
    private long price;
    @Column(name = "terminal_number")
    private Integer terminalNumber;
    @Column(name = "amount_of_load",nullable = false)
    private Integer amountOfLoad;
    @Enumerated(EnumType.STRING)
    @Column(name = "airplane_type")
    private AirplaneType airplaneType;
    @Enumerated(EnumType.STRING)
    @Column(name = "flight_class_type",nullable = false)
    private AirplaneClassType airplaneClassType;
    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_type",nullable = false)
    private AirplaneTicketType airplaneTicketType;
    @Column(name = "logo",nullable = false)
    private String logo;
    @Column(name = "cancel",nullable = false,columnDefinition = "boolean default false")
    private boolean cancel;
    @Column(name = "deleted",nullable = false,columnDefinition = "boolean default false")
    private boolean deleted;
    @OneToMany(mappedBy = "airplane",
            cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Passenger> passengers;
}
