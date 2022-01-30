package com.example.Metadata.Configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_YAML = MediaType.valueOf("application/x-yaml");
    private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMessageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorParameter(false)
                .ignoreAcceptHeader(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType(MediaType.APPLICATION_JSON.getSubtype(),
                        MediaType.APPLICATION_JSON)
                .mediaType(MEDIA_TYPE_YML.getSubtype(), MEDIA_TYPE_YML)
                .mediaType(MEDIA_TYPE_YAML.getSubtype(), MEDIA_TYPE_YAML);
    }
}
