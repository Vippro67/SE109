package com.techshopbe.controller.webhookRequest;

import java.util.Map;

public class QueryResult {
    private String queryText;
    private Intent intent;
    private Map<String, Object> parameters;

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}
