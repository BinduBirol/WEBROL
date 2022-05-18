package com.birol.spring;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import ua_parser.Parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class LoginNotificationConfig {

    @Bean
    public Parser uaParser() throws IOException {
        return new Parser();
    }
    
    /*
    @Bean(name="GeoIPCity")
    public DatabaseReader databaseReader() throws IOException {
        File database = ResourceUtils.getFile("classpath:maxmind/GeoLite2-City.mmdb");    	
        return new DatabaseReader.Builder(database)
                .build();
    }
    */
    
    @Bean(name="GeoIPCity")
    public DatabaseReader databaseReader() throws IOException, GeoIp2Exception {
        //final File resource = new File("src/main/resources/maxmind/GeoLite2-Country.mmdb");
    	final InputStream resource= new ClassPathResource("maxmind/GeoLite2-City.mmdb").getInputStream();
        return new DatabaseReader.Builder(resource).build();
    }
}
