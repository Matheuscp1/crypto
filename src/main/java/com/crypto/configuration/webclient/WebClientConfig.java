package com.crypto.configuration.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient webClient() {

		final int size = 10 * 1024 * 1024; // 10mb
		final ExchangeStrategies strategies = ExchangeStrategies.builder()
				.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size)).build();

		return WebClient.builder().exchangeStrategies(strategies).build();
	}
	
	@Bean
	public WebClient webClientNoEncoding() {

		final int size = 10 * 1024 * 1024; // 10mb
		final ExchangeStrategies strategies = ExchangeStrategies.builder()
				.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size)).build();
		
		DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
		factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

		return WebClient.builder().exchangeStrategies(strategies).uriBuilderFactory(factory).build();
	}
}
