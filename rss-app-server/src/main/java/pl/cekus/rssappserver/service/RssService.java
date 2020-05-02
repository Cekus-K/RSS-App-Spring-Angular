package pl.cekus.rssappserver.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.cekus.rssappserver.model.Rss;
import pl.cekus.rssappserver.model.User;
import pl.cekus.rssappserver.repository.RssRepository;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class RssService {

    private UserService userService;
    private RssRepository rssRepository;

    RssService(UserService userService, RssRepository rssRepository) {
        this.userService = userService;
        this.rssRepository = rssRepository;
    }

    public List<Rss> findAll() {
        return rssRepository.findAllByUser(userService.getCurrentLoggedInUser());
    }

    public Rss add(Rss rss) {
        User current = userService.getCurrentLoggedInUser();
        if (!rssRepository.existsByLinkAndUser(rss.getLink(), current)) {
            rss.setUser(current);
            return rssRepository.save(rss);
        }
        return null;
    }

    public String parseRSS() {
        StringBuilder result = new StringBuilder();
        for (Rss rss : findAll()) {
            try {
                URL feedUrl = new URL(rss.getLink());

                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(feedUrl));

                for (SyndEntry entry : feed.getEntries()) {
                    result.append("<b>")
                            .append(entry.getTitle())
                            .append("</b><br>")
                            .append(entry.getDescription().getValue())
                            .append("<br>")
                            .append("<a href='")
                            .append(entry.getLink())
                            .append("' target='_blank'>")
                            .append(entry.getLink())
                            .append("'</a><br>--------------</br>");
                }

            } catch (NullPointerException | IOException | FeedException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return result.toString();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addTestRss() {
        if (userService.findByUsername("admin") == null) {
            userService.createUser(new User("admin", "password", "password", "example@email.com"));
        }
        User user = userService.findByUsername("admin");
        if (!rssRepository.existsByLinkAndUser("https://tvn24.pl/najnowsze.xml", user)) {
            rssRepository.save(new Rss("https://tvn24.pl/najnowsze.xml", user));
        }
    }
}
