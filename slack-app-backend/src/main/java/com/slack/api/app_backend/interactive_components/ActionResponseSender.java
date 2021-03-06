package com.slack.api.app_backend.interactive_components;

import com.slack.api.Slack;
import com.slack.api.app_backend.interactive_components.response.ActionResponse;
import com.slack.api.util.http.SlackHttpClient;
import com.slack.api.webhook.WebhookResponse;
import okhttp3.Response;

import java.io.IOException;

public class ActionResponseSender {

    private final Slack slack;

    public ActionResponseSender(Slack slack) {
        this.slack = slack;
    }

    public WebhookResponse send(String responseUrl, ActionResponse response) throws IOException {
        SlackHttpClient httpClient = slack.getHttpClient();
        Response httpResponse = httpClient.postJsonBody(responseUrl, response);
        String body = httpResponse.body().string();
        httpClient.runHttpResponseListeners(httpResponse, body);

        return WebhookResponse.builder()
                .code(httpResponse.code())
                .message(httpResponse.message())
                .body(body)
                .build();
    }
}
