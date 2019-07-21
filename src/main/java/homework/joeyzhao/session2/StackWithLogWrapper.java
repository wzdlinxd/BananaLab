package homework.joeyzhao.session2;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StackWithLogWrapper implements InvocationHandler {
    /**
     * https://stackoverflow.com/questions/30344715/automatically-delegating-all-methods-of-a-java-class
     */
    private final StackImpl delegate;

    public StackWithLogWrapper(StackImpl delegate) {
        this.delegate = delegate;
    }

    public static Stack wrap(StackImpl wrapped) {
        return (Stack) Proxy.newProxyInstance(StackImpl.class.getClassLoader(), new Class[] { Stack.class }, new StackWithLogWrapper(wrapped));
    }


    private Method findMethod(Class<?> clazz, Method method) throws Throwable {
        try {
            return clazz.getDeclaredMethod(method.getName(), method.getParameterTypes());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m = findMethod(delegate.getClass(), method);
        if (m != null) {
            System.out.println(String.format("start %s",method.getName()));
            try{
                return m.invoke(delegate, args);
            }catch (InvocationTargetException e){
                //https://stackoverflow.com/questions/10214525/re-throw-an-invocationtargetexception-target-exception
                throw (Exception)e.getCause();
            }finally {
                System.out.println(String.format("end %s",method.getName()));
            }
        }
        return null;
    }
}

