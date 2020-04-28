package pl.cekus.rssappserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cekus.rssappserver.model.Rss;
import pl.cekus.rssappserver.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RssRepository extends JpaRepository<Rss, Long> {

    Optional<Rss> findById(Long id);
    List<Rss> findAllByUser(User user);
    boolean existsByLinkAndUser(String link, User user);
}
