package lt.bit.eshop.service;


import lt.bit.eshop.entity.Authority;
import lt.bit.eshop.form.AuthorityModel;
import lt.bit.eshop.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public void createAuthority(AuthorityModel model) {
        Authority entity = new Authority();
        entity.setName(model.getName());

        authorityRepository.save(entity);
    }

    public Set<AuthorityModel> getAllAuthorities() {
        Set<Authority> roles = (Set<Authority>) authorityRepository.findAll();

        return roles.stream().map(AuthorityModel::new).collect(Collectors.toSet());
    }
    public void deleteAuthority(List<Long> id) {
        for(Long i : id)
            authorityRepository.deleteById(i);
    }
}
