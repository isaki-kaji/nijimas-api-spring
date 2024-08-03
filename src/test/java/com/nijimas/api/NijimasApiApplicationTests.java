package com.nijimas.api;

import com.nijimas.api.config.FirebaseConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class NijimasApiApplicationTests {

	@MockBean
	private FirebaseConfig firebaseConfig;

	@Test
	void contextLoads() {
	}
}
