package delivery.management.system.service;

import delivery.management.system.model.entity.Permission;

import java.util.List;


public interface PermissionService {

    List<Permission> permissions(List<Long> permissionIds);
}
