package lt.bit.eshop.form;

import lt.bit.eshop.entity.RoleEntity;
import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.validation.ExistUsername;
import lt.bit.eshop.validation.PasswordMatches;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@PasswordMatches
public class UserModel {

    private Long id;

    @NotBlank(message = "Name is required!")
    private String name;

    @NotBlank(message = "Username is required!")
    @ExistUsername
    private String username;

    @NotBlank(message = "Password is required!")
    private String password;

    @NotBlank(message = "Enter matching password!")
    private String matchPassword;

    private List<RoleModel> roles = new ArrayList<>();

    public UserModel(UserEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.username = entity.getUsername();
        this.roles = entity.getRoles().stream().map(RoleModel::new).collect(Collectors.toList());
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

    public List<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatchPassword() {
        return matchPassword;
    }

    public void setMatchPassword(String matchPassword) {
        this.matchPassword = matchPassword;
    }
}
