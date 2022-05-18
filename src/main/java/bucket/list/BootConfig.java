package bucket.list;

import bucket.list.interceptor.TopMenuInterceptor;
import bucket.list.service.TopMenuService;
import org.aopalliance.intercept.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BootConfig implements WebMvcConfigurer {


    @Autowired
    TopMenuService topMenuService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);

        TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService);
        InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
        reg1.addPathPatterns("/**");
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
        res.setBasenames("/resources/errors");

        return res;
    }

}
