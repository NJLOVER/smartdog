package whm.core.web.Filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by thinkpad on 20/4/2016.
 */
public class SessionFilter implements Filter{

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取sessionId
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        Cookie[] cookies = httpRequest.getCookies();
        String jSessionId = "";
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("JSessionId")){
                jSessionId = cookie.getValue();
            }
        }
        //通过sessionId获取
    }

    public void destroy() {

    }
}
