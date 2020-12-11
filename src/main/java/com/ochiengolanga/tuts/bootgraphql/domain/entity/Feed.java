package com.ochiengolanga.tuts.bootgraphql.domain.entity;

import java.util.Objects;

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
import org.hibernate.annotations.NaturalId;

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

    @NaturalId(mutable = false)
    @Column(name="feed_title", nullable = false, unique = true, length = 512)
    private String title;

    // @NaturalId(mutable = false)
    // @Column(nullable = false, updatable = false, unique = true)
    // private Long sku;
    /*
    public Long getSku() {
        return sku;
    }
    public void setSku(Long sku) {
        this.sku = sku;
    }
    */

    @Column(length = 30000, name="feed_description", nullable = true)
    private String description;

    @Column(name="feed_itemCount", nullable = true)
    private int itemCount;

    @Column(name="feed_pubdate", nullable = true)
    private String pubDate;

    private transient String formattedDate;

    @Column(name="feed_image", nullable = true)
    private String image;

    public Feed(String title, String description, int itemCount, String pubDate, String image) {
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


    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        
        if (getClass() != o.getClass()) {
            return false;
        }
        
        Feed other = (Feed) o;
        return Objects.equals(title, other.getTitle());
        // including sku 
        // return Objects.equals(title, other.getTitle())
        //        && Objects.equals(sku, other.getSku());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
        // including sku
        // return Objects.hash(title, sku);
    }

    @Override
    public String toString() {
        return "Feed{" + "id=" + id + ", title=" + title + ", pubDate=" + pubDate + ", itemCount=" + itemCount + '}';
        // including sku
        //return "Feed{" + "id=" + id + ", title=" + title 
        //         + ", pubDate=" + pubDate + ", itemCount=" + itemCount + ", sku=" + sku + '}';
    }

}
