package fr.carburant.api.controller;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/carburant")
@Log
public class CarburantController {

    @GetMapping("/import")
    String importCarburant() {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://donnees.roulez-eco.fr/opendata/instantane";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);

        log.info(response.toString());
        return response.getBody();
    }

}
