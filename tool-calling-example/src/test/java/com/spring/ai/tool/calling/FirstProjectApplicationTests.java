package com.spring.ai.tool.calling;

import com.spring.ai.tool.calling.tool.WeatherTool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FirstProjectApplicationTests {

	@Autowired
	private WeatherTool weatherTool;

	@Test
	void getWeatherTest() {
		var response = weatherTool.getWeather("Karachi, Pakistan");
		System.out.println(response);
	}

}
