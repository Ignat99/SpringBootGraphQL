package com.ochiengolanga.tuts.bootgraphql.domain.entity;

import java.io.Serializable;
import lombok.*;

import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@DynamicUpdate
public class Author implements Serializable {
    @Id
    @Column(name="book_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="author_first_name", nullable = false)
    private String firstName;

    @Column(name="author_last_name", nullable = false)
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
