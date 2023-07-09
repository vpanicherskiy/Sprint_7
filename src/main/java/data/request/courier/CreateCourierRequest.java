package data.request.courier;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCourierRequest {
    private String login;
    private String password;
    private String firstName;

    public CreateCourierRequest(String firstName) {
        this.firstName = firstName;
    }
}
