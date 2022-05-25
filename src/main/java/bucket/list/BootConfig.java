package bucket.list;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BootConfig implements WebMvcConfigurer {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
        res.setBasenames("/resources/errors");

        return res;
    }
}
