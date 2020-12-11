package com.ochiengolanga.tuts.bootgraphql.domain.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.DynamicUpdate;

@Data
@EqualsAndHashCode
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@DynamicUpdate
public class Feed implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="feed_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="feed_title", nullable = true)
    private String title;

    @Column(length = 30000, name="feed_description", nullable = true)
    private String description;

    @Column(name="feed_itemCount", nullable = true)
    private int itemCount;

    @Column(name="feed_pubdate", nullable = true)
    private LocalDate pubDate;

    private transient String formattedDate;

    @Column(name="feed_image", nullable = true)
    private String image;

    public Feed(String title, String description, int itemCount, LocalDate pubDate, String image) {
        this.title = title;
        this.description = description;
        this.itemCount = itemCount;
        this.pubDate = pubDate;
        this.image = image;
    }

    // Getter and setter
    public String getFormattedDate() {
        return getPubDate().toString();
    }
}
