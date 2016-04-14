package whm.business.login.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;
import whm.core.base.struts2.action.BaseAction;

/**
 * Created by thinkpad on 2015/11/25.
 */
@Namespace("/login")
@ParentPackage("struts-default")
@Controller
public class LoginAction extends BaseAction{
    private String name;
    private String pwd;

    @Action(value = "/doLogin",results = {
            @Result(name="sucess",location = "/business/login/index.html")
    })
    public String doLogin(){
        return "sucess";
    }

    @Action(value = "/index",results = {
            @Result(name="sucess",location = "/business/login/index.html")
    })
    public String toIndex(){
        return "sucess";
    }
}
