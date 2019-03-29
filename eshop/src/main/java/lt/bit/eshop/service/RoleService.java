package lt.bit.eshop.service;


import lt.bit.eshop.entity.RoleEntity;
import lt.bit.eshop.form.RoleModel;
import lt.bit.eshop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void createRole(RoleModel model) {
        RoleEntity entity = new RoleEntity(model);

        roleRepository.save(entity);
    }

    public List<RoleModel> getAllRoles() {
        List<RoleEntity> roles = (List<RoleEntity>) roleRepository.findAll();

        return roles.stream().map(RoleModel::new).collect(Collectors.toList());
    }
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

}
