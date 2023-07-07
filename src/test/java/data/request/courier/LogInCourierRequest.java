package data.request.courier;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LogInCourierRequest {
    private String login;
    private String password;
}
