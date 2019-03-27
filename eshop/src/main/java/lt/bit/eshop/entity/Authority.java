package lt.bit.eshop.entity;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name="authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Authority(String name) {
        this.name = name;
    }

    public Authority() {
    }

    @Override
    public String getAuthority() {
        return name;
    }

}
