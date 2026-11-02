package com.spring.ai.tool.calling.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class WeatherTool {

    private RestClient restClient;

    public WeatherTool(RestClient restClient){
        this.restClient =restClient;
    }

    @Value("${app.weather.api-key}")
    private String weatherApiKey;

    @Tool(description = "Get weather information of given city.")
    public String getWeather(@ToolParam(description = "which of which we want to get weather information") String city){
//      call external api to get the current city weather information
        var response = restClient
                .get()
                .uri(
                        uriBuilder -> uriBuilder.path("/current.json")
                                .queryParam("key",weatherApiKey)
                                .queryParam("q",city)
                                .build()
                )
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {
                });
        return response.toString();
    }

}
