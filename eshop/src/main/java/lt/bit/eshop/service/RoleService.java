package lt.bit.eshop.service;


import lt.bit.eshop.entity.Authority;
import lt.bit.eshop.entity.RoleEntity;
import lt.bit.eshop.form.RoleModel;
import lt.bit.eshop.repository.AuthorityRepository;
import lt.bit.eshop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityRepository authRepository;

    public void createRole(RoleModel model) {
        RoleEntity entity = new RoleEntity(model);

        roleRepository.save(entity);
    }

    public RoleModel getById(Long id) {

        if(roleRepository.findById(id).isPresent()) {
            return new RoleModel(roleRepository.findById(id).get());
        }

        return null; // dead end
    }

    public List<RoleModel> getAllRoles() {
        List<RoleEntity> roles = (List<RoleEntity>) roleRepository.findAll();

        return roles.stream().map(RoleModel::new).collect(Collectors.toList());
    }
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }


    public void giveAuthorities(Long ID, List<Long> id) {
        RoleEntity role = null;
        Optional<RoleEntity> roleOptional = roleRepository.findById(ID);
        Iterable<Authority> authorities =  authRepository.findAllById(id);
        Set<Authority> authToSet = new HashSet<>();
        authorities.forEach(authToSet::add);

        if(roleOptional.isPresent()) {
            role = roleOptional.get();
        }
        role.setAuthorities(authToSet);

        roleRepository.save(role);
    }

}
