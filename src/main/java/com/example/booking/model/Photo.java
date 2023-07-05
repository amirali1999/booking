package com.example.booking.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Table(name = "ut_photo",schema = "public",catalog = "booking")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE ut_hotel SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "url")
    private String url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residence_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Residence residence;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Train train;
    @Column(name = "deleted",nullable = false,columnDefinition = "boolean default false")
    private boolean deleted;
}
