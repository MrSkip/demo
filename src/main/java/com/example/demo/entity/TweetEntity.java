package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "Tweet")
@Getter
@Setter
public class TweetEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    // up to 2 000 characters
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity createdBy;

    @ManyToMany
    private Set<HashTagEntity> tags;

    private String createdAt;
    private String updatedAt;
}
