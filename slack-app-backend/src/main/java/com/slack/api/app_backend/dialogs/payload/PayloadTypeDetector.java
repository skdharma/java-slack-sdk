package com.slack.api.app_backend.dialogs.payload;

import com.slack.api.util.json.GsonFactory;
import lombok.Data;

public class PayloadTypeDetector {

    @Data
    public static class Payload {
        private String type;
    }

    public String detectType(String json) {
        Payload payload = GsonFactory.createSnakeCase().fromJson(json, Payload.class);
        return payload.getType();
    }

}
