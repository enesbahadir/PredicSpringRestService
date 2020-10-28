package com.preschool;

import com.preschool.enums.UserType;
import com.preschool.model.Discount;
import com.preschool.model.DiscountValues;
import com.preschool.model.Preschool;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PredicApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PredicApplicationTests {

	@Autowired
	private TestRestTemplate template;

	private static String DISCOUNT_ENDPOINT = "http://localhost:8080/discounts/";
	private static String PRESCHOOL_ENDPOINT = "http://localhost:8080/preschools/";
	private static String DISCOUNTVALUES_ENDPOINT = "http://localhost:8080/values/";


	@Test
	public void whenSaveManyToManyRelationship_thenCorrect() {
		Discount discount1 = new Discount("Erken Kayıt Indirimi", "PERCENTAGE",
				new ArrayList<UserType>(Arrays.asList(UserType.IHVAN, UserType.PERSONEL,
						UserType.STANDART)),
				"NONE" );
		template.postForEntity(DISCOUNT_ENDPOINT, discount1, Discount.class);

		Preschool preschool1 = new Preschool("M Lalebahçesi",
				"10/01/2020", 1000);
		template.postForEntity(PRESCHOOL_ENDPOINT, preschool1, Preschool.class);

		DiscountValues discountValues1 = new DiscountValues(preschool1,20L);
		template.postForEntity(DISCOUNTVALUES_ENDPOINT, discountValues1, DiscountValues.class);

		/*
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-type", "text/uri-list");
		HttpEntity<String> httpEntity = new HttpEntity<>(
				BOOK_ENDPOINT + "/1\n" + BOOK_ENDPOINT + "/2", requestHeaders);
		template.exchange(AUTHOR_ENDPOINT + "/1/books",
				HttpMethod.PUT, httpEntity, String.class);

		String jsonResponse = template
				.getForObject(BOOK_ENDPOINT + "/1/authors", String.class);
		JSONObject jsonObj = new JSONObject(jsonResponse).getJSONObject("_embedded");
		JSONArray jsonArray = jsonObj.getJSONArray("authors");
		assertEquals("author is incorrect",
				jsonArray.getJSONObject(0).getString("name"), AUTHOR_NAME);
				*/
	}

}
