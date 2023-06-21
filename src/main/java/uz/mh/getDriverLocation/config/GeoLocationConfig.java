package uz.mh.getDriverLocation.config;
import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Configuration
public class GeoLocationConfig {

    private static DatabaseReader reader = null;

    private final ResourceLoader resourceLoader;

    public GeoLocationConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public DatabaseReader databaseReader(){
        try {
            log.info("GeolocationConfig: Trying to load GeoLite2-Country database...");

            Resource resource = resourceLoader.getResource("classpath:GeoLite2-City_20230526/GeoLite2-City.mmdb");
            InputStream dbAsStream = resource.getInputStream();

            log.info("GeoLocationConfig: Trying was loaded successfully.");

            return reader = new DatabaseReader
                    .Builder(dbAsStream)
                    .fileMode(Reader.FileMode.MEMORY)
                    .build();

        }catch (IOException | NullPointerException e){
            log.error("Database reader could not be initialized.",e);
            return null;
        }
    }
}
