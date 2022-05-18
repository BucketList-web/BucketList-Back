package bucket.list.interceptor;

import bucket.list.domain.BoardInfo;
import bucket.list.service.TopMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TopMenuInterceptor implements HandlerInterceptor {

    private final TopMenuService topMenuService;

    @Autowired
    public TopMenuInterceptor(TopMenuService topMenuService) {
        this.topMenuService = topMenuService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        List<BoardInfo> boardInfo = topMenuService.allInfoName();
        request.setAttribute("boardInfo", boardInfo);

        return true;
    }
}
