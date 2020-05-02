package pl.cekus.rssappserver.controller;

import org.springframework.web.bind.annotation.*;
import pl.cekus.rssappserver.model.Content;
import pl.cekus.rssappserver.model.Rss;
import pl.cekus.rssappserver.service.RssService;
import pl.cekus.rssappserver.service.SendGridService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rss")
class RssController {

    private RssService rssService;
    private SendGridService sendGridService;

    RssController(RssService rssService, SendGridService sendGridService) {
        this.rssService = rssService;
        this.sendGridService = sendGridService;
    }

    @GetMapping
    List<Rss> readAll() {
        return rssService.findAll();
    }

    @GetMapping("/content")
    Content readContent() {
        return new Content(rssService.parseRSS());
    }

    @PostMapping
    Rss create(@RequestBody Rss rss) {
        return rssService.add(rss);
    }

    @GetMapping("/send")
    void send() {
        sendGridService.sendMail();
    }
}
