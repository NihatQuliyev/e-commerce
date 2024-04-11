package delivery.management.system.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PasswordRequestDto {

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "PASSWORD_REGEX"
    )
    private String updatePassword;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "PASSWORD_REGEX"
    )
    private String repeatPassword;


    public PasswordRequestDto(String updatePassword, String repeatPassword) {
        differPassword(updatePassword,repeatPassword);
        this.updatePassword = updatePassword;
        this.repeatPassword = repeatPassword;
    }

    private void differPassword(String updatePassword, String repeatPassword) {

        if (!updatePassword.equals(repeatPassword)) throw new RuntimeException("Differ password");
    }

    public String getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(String updatePassword) {
        this.updatePassword = updatePassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
