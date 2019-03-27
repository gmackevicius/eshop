package lt.bit.eshop.form;

import lt.bit.eshop.entity.RoleEntity;
import lt.bit.eshop.entity.UserEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class UserModel {

    @NotBlank(message = "Name is required!")
    private String name;

    @NotBlank(message = "Username is required!")
    private String username;

    @NotBlank(message = "Password is required!")
    @Size(min=6, max=10)
    private String password;

    private List<String> roles = new ArrayList<>();

    public UserModel(UserEntity entity) {
        this.name = entity.getName();
        this.username = entity.getUsername();

        for(RoleEntity r : entity.getRoles() ) {
            this.roles.add(r.getName());
        }
    }

    public UserModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
