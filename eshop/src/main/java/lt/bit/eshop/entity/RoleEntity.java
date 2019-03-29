package lt.bit.eshop.entity;


import lt.bit.eshop.form.RoleModel;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_authorities", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "authority_id")} )
    private Set<Authority> authorities;

    public RoleEntity(RoleModel model) {
        this.name = model.getName();
    }

    public RoleEntity() {
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
