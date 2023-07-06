package com.example.booking.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "ut_role",schema = "public",catalog = "booking")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
