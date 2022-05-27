package bucket.list;

import bucket.list.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BootConfig implements WebMvcConfigurer {

    @Value("${connect.path}")
    private String connectPath;
    @Value("${resource.path}")
    private String resourcePath;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourcePath);
    }

    /**
     * Interceptor를 Spring에 추가해주는 작업
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/*/write")
                .addPathPatterns("/community/create");
    }


    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
        res.setBasenames("/resources/errors");

        return res;
    }
}
