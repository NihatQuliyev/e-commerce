package delivery.management.system.service.impl;

import delivery.management.system.config.GoogleMapsConfig;
import delivery.management.system.model.dto.response.DirectionsApiResponse;
import delivery.management.system.model.dto.response.RouteResponseDto;
import delivery.management.system.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class IMapService implements MapService {
    @Autowired
    private GoogleMapsConfig googleMapsConfig;

    @Autowired
    private final RestTemplate restTemplate;


    public IMapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public RouteResponseDto calculateRoute(String origin, String destination, String mode) {
        String apiKey = googleMapsConfig.getKey();
        String url = UriComponentsBuilder
                .fromUriString("https://maps.googleapis.com/maps/api/directions/json")
                .queryParam("origin", origin)
                .queryParam("destination", destination)
                .queryParam("mode", mode)
                .queryParam("key", apiKey)
                .build()
                .toUriString();

        DirectionsApiResponse response = restTemplate.getForObject(url, DirectionsApiResponse.class);

        return mapToRouteResponse(response);

    }

    private RouteResponseDto mapToRouteResponse(DirectionsApiResponse response) {
        RouteResponseDto routeResponse = new RouteResponseDto();

        if (response != null && response.getRoutes() != null && !response.getRoutes().isEmpty()) {
            DirectionsApiResponse.Route route = response.getRoutes().get(0);
            DirectionsApiResponse.Leg leg = route.getLegs().get(0);

            String distance = leg.getDistance().getText();
            String duration = leg.getDuration().getText();

            routeResponse.setDistance(distance);
            routeResponse.setDuration(duration);
        }
        return routeResponse;
    }
}

