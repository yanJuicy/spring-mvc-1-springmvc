package com.example.springmvc.basic.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestBodyStringController {

	@PostMapping("/request-body-string-v1")
	public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		log.info("messageBody={}", messageBody);
		response.getWriter().write("ok");
	}

	@PostMapping("/request-body-string-v2")
	public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		log.info("messageBody={}", messageBody);
		responseWriter.write("ok");
	}

	@PostMapping("/request-body-string-v3")
	public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
		String messageBody = httpEntity.getBody();
		log.info("messageBody={}", messageBody);
		return new HttpEntity<>("ok");
	}

	@ResponseBody
	@PostMapping("/request-body-string-v4")
	public String requestBodyStringV4(@RequestBody String messageBody) {
		log.info("messageBody={}", messageBody);
		return "ok";
	}

}
