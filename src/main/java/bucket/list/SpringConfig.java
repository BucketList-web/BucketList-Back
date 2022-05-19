package bucket.list;

import bucket.list.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

    /**
     * Interceptor를 Spring에 추가해주는 작업
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/menu/3");
        /*
            addPathPatterns("") // 요청 주소의 "패턴"
                                   ex. "/service/**", "/login/**"
            excludePathPatterns() // 위 "패턴" 중에 제외할 페이지
         */
    }
}
