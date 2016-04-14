package whm.core.base.spring;

/**
 * Created by thinkpad on 2015/12/1.
 */
public class ConstructTest {
    private String arg1;
    private String arg2;
    int a;

    public ConstructTest(int a,String arg1, String arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.a = a;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }
}
