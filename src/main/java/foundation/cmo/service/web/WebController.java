package foundation.cmo.service.web;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Configuration
public class WebController {

	@Autowired
	private MessageSource messages;

	@ResponseBody
	@GetMapping(value = "/", produces = "text/html; charset=utf-8")
	public String index() throws IOException {
		return StreamUtils.copyToString(new ClassPathResource("index.html").getInputStream(), StandardCharsets.UTF_8)
				.replace("${text.page.title}", getString("text.page.title"))
				.replace("${text.loading}", getString("text.loading")).replace("${text.api}", getString("text.api"))
				.replace("${text.contact.info}", getString("text.contact.info"));
	}

	@Bean
	void status() {
		log.info("Starting default page...");
	}

	public String getString(String code, Object... args) {
		try {
			return messages.getMessage(code, args, Locale.forLanguageTag("pt-BR"));
		} catch (Exception e) {
			return code;
		}
	}

}
