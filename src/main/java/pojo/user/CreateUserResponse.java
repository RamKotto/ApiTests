package pojo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import utils.DataDeserializer;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class CreateUserResponse extends UserRequest {

    @JsonProperty("id")
    private String id;
    @JsonProperty("createdAt")
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonDeserialize(using = DataDeserializer.class)
    private LocalDateTime createdAt;
}
