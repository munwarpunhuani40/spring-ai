package com.spring.ai.firstproject;

import com.spring.ai.firstproject.helper.Helper;
import com.spring.ai.firstproject.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FirstProjectApplicationTests {

	@Autowired
	private ChatService chatService;

	@Test
	void saveDataToVectorDatabase(){
		System.out.println("Saving Data to Database");
		this.chatService.saveData(Helper.getData());
		System.out.println("Data is saved Successfully");

	}
}
