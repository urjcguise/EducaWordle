package app.TFGWordle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TfgWordleApplicationTests {

	@Value("${spring.datasource.url:NOTFOUND}")
	private String url;

	@Test
	void contextLoads() {
		System.out.println("Datasource URL: " + url);
	}

}
