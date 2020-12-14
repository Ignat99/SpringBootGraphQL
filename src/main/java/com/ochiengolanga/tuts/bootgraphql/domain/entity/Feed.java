package com.ochiengolanga.tuts.bootgraphql.domain.entity;

import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.DynamicUpdate;
//import org.hibernate.annotations.NaturalId;

@Data
@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(toBuilder = true)
@DynamicUpdate
public class Feed implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="feed_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

//    @NaturalId(mutable = false)
    @Column(name="feed_title", nullable = false, length = 512)
    private String title;

    @Column(length = 30000, name="feed_description", nullable = true)
    private String description;

    @Column(name="feed_itemCount", nullable = true)
    private int itemCount;

    @Column(name="feed_pubdate", nullable = true)
    private String pubDate;

    private transient String formattedDate;

    @Column(name="feed_image", nullable = true)
    private String image;

    @Column(name="feed_guid", nullable = false, unique = true)
    private String guid;

    @Column(name="feed_link", nullable = true)
    private String link;


    public Feed(String title, String description, int itemCount, String pubDate, String image, String guid, String link) {
        this.title = title;
        this.description = description;
        this.itemCount = itemCount;
        this.pubDate = pubDate;
        this.image = image;
        this.guid = guid;
        this.link = link;
    }

    // Getter and setter
    public String getFormattedDate() {
        return getPubDate().toString();
    }
}
