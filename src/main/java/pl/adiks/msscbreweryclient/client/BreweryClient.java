package pl.adiks.msscbreweryclient.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.adiks.msscbreweryclient.model.BeerDTO;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private final String BEER_PATH_V1 = "/api/v1/beer/";
    private String apiHost;
    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public BeerDTO getBeerById(UUID beerId) {
        return restTemplate.getForObject(apiHost + BEER_PATH_V1 + beerId.toString(), BeerDTO.class);
    }

    public URI saveNewBeer(BeerDTO beerDTO) {
        return restTemplate.postForLocation(apiHost + BEER_PATH_V1, beerDTO);
    }
}
