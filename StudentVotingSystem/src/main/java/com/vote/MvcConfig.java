package com.vote;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/system_admin_home").setViewName("system_admin_home");
		registry.addViewController("/voting_admin_home").setViewName("voting_admin_home");
		registry.addViewController("/voter_home").setViewName("voter_home");
	}
}
