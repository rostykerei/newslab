package io.newslab.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "rss_feed", uniqueConstraints = @UniqueConstraint(columnNames = "url"))
public class RssFeed {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id",  referencedColumnName = "id", nullable = false)
    private Publisher publisher;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "url", nullable = false)
    private String url;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "link", nullable = false)
    private String link;

    @Size(max = 1024)
    @Column(name = "description")
    private String description;

    @Size(max = 255)
    @Column(name = "author")
    private String author;

    @Size(max = 255)
    @Column(name = "copyright")
    private String copyright;

    @Size(max = 255)
    @Column(name = "image_url")
    private String imageUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_check")
    private Date lastCheck;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "planned_check")
    private Date plannedCheck;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "in_progress_since")
    private Date inProgressSince;

    @Size(max = 255)
    @Column(name = "http_last_etag")
    private String httpLastEtag;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "http_last_modified")
    private Date httpLastModified;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(Date lastCheck) {
        this.lastCheck = lastCheck;
    }

    public Date getPlannedCheck() {
        return plannedCheck;
    }

    public void setPlannedCheck(Date plannedCheck) {
        this.plannedCheck = plannedCheck;
    }

    public Date getInProgressSince() {
        return inProgressSince;
    }

    public void setInProgressSince(Date inProgressSince) {
        this.inProgressSince = inProgressSince;
    }

    public String getHttpLastEtag() {
        return httpLastEtag;
    }

    public void setHttpLastEtag(String httpLastEtag) {
        this.httpLastEtag = httpLastEtag;
    }

    public Date getHttpLastModified() {
        return httpLastModified;
    }

    public void setHttpLastModified(Date httpLastModified) {
        this.httpLastModified = httpLastModified;
    }
}
