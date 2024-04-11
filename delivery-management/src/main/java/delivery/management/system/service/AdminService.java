package delivery.management.system.service;

import delivery.management.system.model.dto.response.DashboardResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    ResponseEntity<DashboardResponseDto> dashboard();
}
