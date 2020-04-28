package pl.cekus.rssappserver.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridService {

    private UserService userService;
    private RssService rssService;

    SendGridService(UserService userService, RssService rssService) {
        this.userService = userService;
        this.rssService = rssService;
    }

    @Value("${send-grid-email}")
    private String fromEmail;

    @Value("${api-key}")
    private String apiKey;

    public void sendMail() {
        Email from = new Email(fromEmail);
        String subject = "Message from RSS App sending by SendGrid";
        Email to = new Email(userService.getCurrentLoggedInUser().getEmail());
        Content content = new Content("text/html", rssService.parseRSS());

        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
