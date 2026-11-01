package com.spring.ai.firstproject;

import com.spring.ai.firstproject.service.DataLoader;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FirstProjectApplicationTests {

	@Autowired
	private DataLoader dataLoader;

	@Test
	void testDataLoader(){
		var documents = dataLoader.loadDocumentsFromJson();
		documents.forEach(System.out::println);
		System.out.println(documents.size());
	}

	@Test
	void testPdfDataLoader(){
		List<Document> documentList  = dataLoader.loadDocumentsFromPDF();
		System.out.println(documentList.size());
		documentList.forEach(item ->{
			System.out.println(item);
			System.out.println("_____________________");
		});
	}
}
