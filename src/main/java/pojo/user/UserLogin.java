package pojo.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLogin {

    @JsonProperty("email")
    String email;
    @JsonProperty("password")
    String password;
}
