package delivery.management.system.service.impl;

import delivery.management.system.model.entity.Permission;
import delivery.management.system.repository.PermissionRepository;
import delivery.management.system.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPermissionService implements PermissionService {

    private final PermissionRepository permissionRepository;

    public IPermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }


    @Override
    public List<Permission> permissions(List<Long> permissionIds) {

        if (permissionIds != null) {
            return permissionRepository.findAllById(permissionIds);
        }
        return null;
    }
}
