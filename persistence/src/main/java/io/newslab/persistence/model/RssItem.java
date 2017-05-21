package io.newslab.persistence.model;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "rss_item",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"uid"}),
                @UniqueConstraint(columnNames = {"publisher_id", "guid_hash"}),
                @UniqueConstraint(columnNames = {"publisher_id", "content_hash"}),
        })
public class RssItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 11, max = 11)
    @Column(name = "uid", length = 11, nullable = false, unique = true)
    private String uid;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id", nullable = false)
    private Publisher publisher;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "rss_feed_id", referencedColumnName = "id", nullable = false)
    private RssFeed rssFeed;

    @NotNull
    @Column(name = "guid_hash", nullable = false, length = 20)
    private byte[] guidHash;

    @NotNull
    @Column(name = "content_hash", nullable = false, length = 20)
    private byte[] contentHash;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title", nullable = false)
    private String title;

    @Size(max = 255)
    @Column(name = "author")
    private String author;

    @NotNull
    @Size(min = 8, max = 255)
    @Column(name = "link", nullable = false)
    private String link;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "guid", nullable = false)
    private String guid;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publication_date", nullable = false)
    private Date publicationDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "adjusted_publication_date", nullable = false)
    private Date adjustedPublicationDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Size(max = 8192)
    @Column(name = "description")
    private String description;

    public long getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public RssFeed getRssFeed() {
        return rssFeed;
    }

    public void setRssFeed(RssFeed rssFeed) {
        this.rssFeed = rssFeed;
    }

    public byte[] getGuidHash() {
        return guidHash;
    }

    public byte[] getContentHash() {
        return contentHash;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        updateContentHash();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
        this.guidHash = DigestUtils.sha1(guid);
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getAdjustedPublicationDate() {
        return adjustedPublicationDate;
    }

    public void setAdjustedPublicationDate(Date adjustedPublicationDate) {
        this.adjustedPublicationDate = adjustedPublicationDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        updateContentHash();
    }

    private void updateContentHash() {
        // Todo, change it to soundex algorithm or so...
        this.contentHash = DigestUtils.sha1(
                getTitle() + "\0" + getDescription()
        );
    }
}
