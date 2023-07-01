package com.example.booking.model;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "ut_user",schema = "public",catalog = "booking")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE ut_user SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "phone_number",nullable = false)
    private long phoneNumber;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "address")
    private String address;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "deleted",nullable = false)
    private boolean deleted;
    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Passenger> passengers;
}
