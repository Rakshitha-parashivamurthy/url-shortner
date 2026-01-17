import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/url")
public class RedirectController {

    @PostMapping("/shorten")
    public UrlMapping shortenUrl(@RequestParam String originalUrl) {
        ...
    }
}
