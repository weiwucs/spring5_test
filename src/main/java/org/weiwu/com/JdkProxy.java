package org.weiwu.com;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 代理设计模式：
 * 代理是一种常用的设计模式，其目的就是为其他对象提供一个代理以控制对某个对象的访问。代理类负责为委托类预处理
 * 消息、过滤消息并转发消息，以及进行消息被委托类执行后的后续处理。
 * 1. 为了保持行为的一致性，代理类和委托类通常会实现相同的接口；
 * 2. 引入代理能够控制对委托对象的直接访问，可以很好的隐藏和保护委托对象，也更加具有灵活性
 *
 * ClassLoader: 类加载器，负责将类的字节码装载到Java虚拟机（JVM）中并为其定义类对象，然后该类才能被使用。
 * Proxy静态方法生成代理类同样需要通过类装载器来进行装载才能使用，它与普通类的唯一区别就是其字节码是由JVM
 * 在运行时动态生成的而非预存于任何一个.class文件中。每次生成动态代理对象都需要指定一个类装载器对象。
 *
 * Proxy类的设计用到了代理模式的设计思想，Proxy类对象实现了代理目标的所有接口，并代替目标对象进行实际的操作。
 * 但这种替代不是一种简单的替代，那样是没有意义的，代理的目的是在目标对象方法的基础上做增强，这种增强的本质就是
 * 对目标对象的方法进行拦截。所以，Proxy应该包括一个方法拦截器，来指示当拦截到方法调用时做何种处理。InvocationHandler
 * 就是拦截器的接口。
 *
 * JDK代理和CGLIB代理
 * 我们先讲一下这两种代理方式最大的区别：
 * JDK动态代理是基于接口的方式，换句话来说就是代理类和目标类都实现同一个接口，那么代理类和目标类的方法名就一样了；
 * CGLIB动态代理是代理类去继承目标类，然后重写其中目标类的方法，这样也可以保证代理类拥有目标类的同名方法。
 */
public class JdkProxy {
    public static void main(String[] args){
        Class[] interfaces = { UserDao.class, Car.class };
        Class[] interfaces2 = { UserDao.class };
        UserDaoImpl userDao = new UserDaoImpl();
        CarImpl car = new CarImpl();
        //the generated proxy class be cached
//        Car o = (Car) Proxy.newProxyInstance(JdkProxy.md.class.getClassLoader(), interfaces, new UserDaoProxy(car));
//        String res = o.printInfo("print info");
//        System.out.println(res);

        UserDao user = (UserDao) Proxy.newProxyInstance(JdkProxy.class.getClassLoader(), interfaces, new UserDaoProxy(userDao));
        String update = user.print("update");
        System.out.println(update);
        System.out.println(user.toString());
//
//        UserDao user2 = (UserDao) Proxy.newProxyInstance(JdkProxy.md.class.getClassLoader(), interfaces2, new UserDaoProxy(userDao));
//        String update2 = user2.print("update");
//        System.out.println(update2);
//        System.out.println(uo);
//        Object proxyInstance = Proxy.newProxyInstance(JdkProxy.md.class.getClassLoader(), interfaces, new UserDaoProxy(userDao));
//        System.out.println(proxyInstance);
    }
}

class  UserDaoProxy implements InvocationHandler{

    private Object object;
    public UserDaoProxy(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method before
        System.out.println("method before:" + method.getName() + " -args: " + Arrays.toString(args));

        Object res = method.invoke(object, args);

        //method after
        System.out.println("method after: " + object);

        return res;
    }
}
