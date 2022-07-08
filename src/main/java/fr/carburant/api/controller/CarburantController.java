package fr.carburant.api.controller;

import carburant.api.model.ObjectFactory;
import fr.carburant.api.model.PdvListe;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/carburant")
@Log
public class CarburantController {


    @GetMapping("/import")
    String importCarburant() {

        ObjectFactory test = new ObjectFactory();

        PdvListe resp = test.createPdvListe();

        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.ALL);
        headers.setAccept(Arrays.asList(MediaType.ALL));



//        HttpEntity<PdvListe> entity = new HttpEntity<PdvListe>(headers);
        HttpEntity entity = new HttpEntity(headers);

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://donnees.roulez-eco.fr/opendata/instantane";
//        ResponseEntity<PdvListe> response
//                = restTemplate.getForEntity(fooResourceUrl, PdvListe.class);


//        restTemplate.getMessageConverters()
//                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
//        ResponseEntity<PdvListe> response2 = restTemplate.exchange(fooResourceUrl, HttpMethod.GET, entity,
//                PdvListe.class);

        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        ResponseEntity<byte[]> response3 = restTemplate
                .exchange(fooResourceUrl, HttpMethod.GET, entity,
                        byte[].class);


        log.info(response3.toString());
        return response3.getBody().toString();
    }



}
