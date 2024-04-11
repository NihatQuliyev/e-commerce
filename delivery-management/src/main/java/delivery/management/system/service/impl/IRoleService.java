package delivery.management.system.service.impl;

import common.exception.model.exception.ApplicationException;
import common.exception.model.service.ExceptionService;
import delivery.management.system.mapper.RoleMapper;
import delivery.management.system.model.dto.request.RoleRequestDto;
import delivery.management.system.model.dto.response.RoleResponseDto;
import delivery.management.system.model.entity.Permission;
import delivery.management.system.model.entity.Role;
import delivery.management.system.repository.RoleRepository;
import delivery.management.system.service.PermissionService;
import delivery.management.system.service.RoleService;
import delivery.management.system.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class IRoleService implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    private final PermissionService permissionService;
    private final ExceptionService exceptionService;
    private final MessageUtil messagesUtil;

    @Override
    public ResponseEntity<Void> create(RoleRequestDto roleRequestDto) {
        Role role =  roleMapper.map(roleRequestDto);
        List<Permission> permissions = permissionService.permissions(roleRequestDto.getPermissions());
        if (permissions != null) {
            role.setPermission(permissions);
        }else {
            role.setPermission(new LinkedList<>());
        }
        roleRepository.save(role);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public Role findByRole(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(messagesUtil.getMessages("role.not.found"), HttpStatus.NOT_FOUND)));
    }

    @Override
    public ResponseEntity<Void> update(RoleRequestDto roleRequestDto) {
        Role role = findByRole(roleRequestDto.getName());
        List<Permission> permissions = permissionService.permissions(roleRequestDto.getPermissions());
        if (permissions != null) {
            role.setPermission(permissions);
        }
        roleRepository.save(role);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<RoleResponseDto>> findAllRoles() {
        return ResponseEntity.ok().body(roleRepository.findAll().stream()
                .map(roleMapper::map)
                .collect(Collectors.toList()));
    }
}
