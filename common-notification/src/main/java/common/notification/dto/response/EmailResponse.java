package common.notification.dto.response;

import lombok.Getter;

@Getter
public class EmailResponse {

    private String to;

    public void setTo(String to) {
        this.to = to;
    }
}
