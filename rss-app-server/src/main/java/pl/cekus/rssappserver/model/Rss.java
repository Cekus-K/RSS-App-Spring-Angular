package pl.cekus.rssappserver.model;

import javax.persistence.*;

@Entity
public class Rss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Rss(){}

    public Rss(String link, User user) {
        this.link = link;
        this.user = user;
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
