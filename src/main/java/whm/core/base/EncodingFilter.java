package whm.core.base;


import com.sun.net.httpserver.HttpExchange;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by thinkpad on 2015/11/25.
 */
public class EncodingFilter implements Filter {
    private String encode;

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /**
         * IE它的安全策略默认是会把iframe中的页面站点认为是不可信任的，它会阻止该站点传过来的cookie
         * （如果你在iframe中的URL跳转是用的localhost,则不会被阻挡），所以因为没法使用cookie了，session便失效了。
         * 解决的方法是在过滤器，或者被嵌入的页面内加入属性为P3P的header信息。
         * java为：response.addHeader("P3P","CP=CAO PSA OUR")
         */
        HttpServletResponse res=(HttpServletResponse) servletResponse;
        res.addHeader("P3P","CP=CAO PSA OUR");
        servletRequest.setCharacterEncoding("GBK");
    }

    public void destroy() {

    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }
}
