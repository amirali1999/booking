package com.example.booking.model;

import com.example.booking.enums.GenderType;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Table(name = "ut_passenger",schema = "public",catalog = "booking")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE ut_passenger SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "fist_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "national_code",nullable = false)
    private long nationalCode;
    @Column(name = "phone_number",nullable = false)
    private long phoneNumber;
    @Column(name = "gender",nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Airplane airplane;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trian_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Train train;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residence_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Residence residence;
    @Column(name = "deleted",nullable = false,columnDefinition = "boolean default false")
    private boolean deleted;

}
