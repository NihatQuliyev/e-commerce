package delivery.management.system.controller;

import common.exception.model.exception.ApplicationException;
import common.exception.model.service.ExceptionService;
import delivery.management.system.model.dto.response.RouteResponseDto;
import delivery.management.system.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RouteController {

    private final MapService mapService;
    private final ExceptionService exceptionService;

    @GetMapping("/calculate-route")
    public ResponseEntity<RouteResponseDto> calculateRoute(@RequestParam String origin,
                                                 @RequestParam String destination,
                                                 @RequestParam(required = false, defaultValue = "driving") String mode) {
        try {
            RouteResponseDto routeResponse = mapService.calculateRoute(origin, destination, mode);
            return ResponseEntity.ok(routeResponse);
        } catch (Exception e) {
            throw new ApplicationException(exceptionService.exceptionResponse("Internal",HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}

