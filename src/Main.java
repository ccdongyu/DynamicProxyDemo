
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class Main {
    public static void main(String[] args) throws IOException{
        Bussiness rb = new RealBussiness();
        System.out.println(Bussiness.class.getClassLoader());
        Bussiness b = (Bussiness) Proxy.newProxyInstance(Bussiness.class.getClassLoader(), rb.getClass().getInterfaces(), new BussinessProxy(rb));
        b.sell();
        b.add();

    }
}

interface Bussiness {
    void sell();

    void add();
}

class RealBussiness implements Bussiness {

    @Override
    public void sell() {
        System.out.println("==sell==");
    }

    @Override
    public void add() {
        System.out.println("==add==");
    }
}

class BussinessProxy implements InvocationHandler {
    Object bussiness;

    BussinessProxy(Bussiness bussiness) {
        this.bussiness = bussiness;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("==before==");
        Object ob = method.invoke(bussiness, args);
        System.out.println("invode: "+method.getName());
        System.out.println("==after==");
        return ob;
    }
}