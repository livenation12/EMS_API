package jrd.projects.ems202506.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jrd.projects.ems202506.api.storage.StorageProperties;
import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final StorageProperties storageProps;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**")
		// "file:///" for file system path
		.addResourceLocations("file:///" + storageProps.getLocation().replace("\\", "/") + "/");
	}
}
