package com.spring.ai.firstproject;

import com.spring.ai.firstproject.service.DataLoader;
import com.spring.ai.firstproject.service.DataTransformer;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FirstProjectApplicationTests {

	@Autowired
	private DataLoader dataLoader;

	@Autowired
	private DataTransformer dataTransformer;

	@Autowired
	private VectorStore vectorStore;

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
		System.out.println("Readed:- Now going to transform");
		var transformedDocuments = this.dataTransformer.transform(documentList);
		System.out.println(transformedDocuments.size());

//		Going to save the data into Database
		vectorStore.add(transformedDocuments);
		System.out.println("Done");

	}

}
