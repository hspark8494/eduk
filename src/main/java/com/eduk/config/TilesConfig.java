package com.eduk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class TilesConfig {

	@Bean
	public TilesViewResolver viewResolver() {
		TilesViewResolver tilesView = new TilesViewResolver();
		tilesView.setViewClass(TilesView.class);
		tilesView.setOrder(0);
		return tilesView;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[] {"/WEB-INF/tiles/tiles.xml"});
		tiles.setCheckRefresh(true);
		return tiles;
	}
}