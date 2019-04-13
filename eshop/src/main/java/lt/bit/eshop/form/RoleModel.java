package lt.bit.eshop.form;

import lt.bit.eshop.entity.RoleEntity;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleModel {

    private Long id;

    @NotBlank(message="Name is required!")
    private String name;

    private Set<AuthorityModel> authorities;

    public RoleModel(String name) {
        this.name = name;
    }

    public RoleModel(RoleEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.authorities = entity.getAuthorities().stream().map(AuthorityModel::new).collect(Collectors.toSet());
    }

    public RoleModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AuthorityModel> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityModel> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleModel roleModel = (RoleModel) o;
        return id.equals(roleModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
