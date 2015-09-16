/**
 * Created by Lonica on 15/9/15.
 */
public class MySingletonDoubleCheck {

    private static MySingletonDoubleCheck instance;
//    private static Object o = new Object(); synchronized(o);

    private MySingletonDoubleCheck(){

    }

    public static MySingletonDoubleCheck getInstance(){
        if (instance == null){
            synchronized(MySingletonDoubleCheck.class){
                if(instance == null){
                    instance = new MySingletonDoubleCheck();
                }
            }
        }
        return instance;
    }


}
