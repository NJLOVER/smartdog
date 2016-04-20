package whm.core.base.struts2.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by thinkpad on 2015/11/25.
 */
public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    public HttpServletRequest request;
    public HttpServletResponse response;

    public void result2Json(Object obj){
        String content = JSON.toJSONString(obj);
        response.setCharacterEncoding("UTF-8");
        this.response.setContentType("text/json; charset=utf-8");
        try{
            this.response.getWriter().write(content);
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void restResponse(Object obj){
        String content = JSON.toJSONString(obj);
        response.setCharacterEncoding("UTF-8");
        this.response.setContentType("text/html; charset=utf-8");
        try{
            this.response.getWriter().write(content);
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String findCookie(String key){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(key)){
                return cookie.getValue();
            }
        }
        return null;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }
}
