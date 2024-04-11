package delivery.management.system.service;

import delivery.management.system.model.dto.response.RouteResponseDto;

public interface MapService {
    RouteResponseDto calculateRoute(String origin, String destination, String mode);
}

