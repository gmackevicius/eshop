package lt.bit.eshop.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserModel {

    @NotBlank(message = "Name is required!")
    private String name;

    @NotBlank(message = "Username is required!")
    private String username;

    @NotEmpty(message = "Password is required!")
    @Size(min=6, max=10)
    private String password;


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
}
