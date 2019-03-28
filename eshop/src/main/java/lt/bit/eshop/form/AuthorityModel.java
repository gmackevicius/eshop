package lt.bit.eshop.form;

import lt.bit.eshop.entity.Authority;

import javax.validation.constraints.NotBlank;

public class AuthorityModel {


    private Long id;

    @NotBlank(message="Name is required!")
    private String name;

    public AuthorityModel(@NotBlank(message = "Name is required!") String name) {
        this.name = name;
    }

    public AuthorityModel(Authority entity) {
        this.id = entity.getId();
        this.name = entity.getAuthority();
    }

    public AuthorityModel() {
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
}
