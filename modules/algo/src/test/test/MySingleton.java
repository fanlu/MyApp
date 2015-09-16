/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-4-30
 * Time: 下午10:21
 * To change this template use File | Settings | File Templates.
 */
public enum MySingleton {
    INSTANCE;
    private String s = "";
    MySingleton(){
        System.out.println("init");
        s = "1";
    }
    public String getS(){
        return s;
    }
    public static MySingleton getInstance(){
        return INSTANCE;
    }
}
