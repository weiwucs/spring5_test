package org.weiwu.com.classproxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB（Code Generation Library）是一个开源项目，是一个强大的，高性能，高质量的Code生成类库，它可以在运行期扩展Java类与
 * 实现Java接口。Hibernate用它来实现PO(Persistent Object 持久化对象)字节码的动态生成。CGLIB是一个强大的高性能的代码生成包。
 * 它广泛的被很多AOP的框架使用，例如Spring AOP为它们提供方法的interception（拦截）。CGLIB的底层是通过一个小而快的字节码吃力框架
 * ASM，来转换字节码并生成新的类。除了CGLIB包，脚本语言例如Groovy和BeanShell，也是使用ASM来生成java字节码。当然不鼓励直接使用
 * ASM，因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉。
 */
public class CglibProxy {
    public static void main(String[] args){
        PersonDao personDao = new PersonDao();
        PersonDao o =(PersonDao) new MyMethodInterceptor(personDao).getProxyInstance();
        System.out.println(o.getClass());
        o.save();
        o.update();
    }
}

class MyMethodInterceptor implements MethodInterceptor{

    private Object target;
    public MyMethodInterceptor(Object target){
        this.target = target;
    }

    public Object getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass()); //setting parent class
        enhancer.setCallback(this); //set callback
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("start action");
        Object invoke = method.invoke(target, objects);
        System.out.println("finish action");
        return invoke;
    }
}
