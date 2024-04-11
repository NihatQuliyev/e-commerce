package delivery.management.system.service;

import delivery.management.system.model.dto.request.RoleRequestDto;
import delivery.management.system.model.dto.response.RoleResponseDto;
import delivery.management.system.model.entity.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {

    ResponseEntity<Void> create(RoleRequestDto roleRequestDto);

    Role findByRole(String name);


    ResponseEntity<Void> update(RoleRequestDto roleRequestDto);

    ResponseEntity<List<RoleResponseDto>> findAllRoles();
}
