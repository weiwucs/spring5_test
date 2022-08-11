package org.weiwu.com;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.weiwu.com.staticproxy.UserDaoProxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * base package: java.lang.reflect
     * reflect: 1. read and modify class file; 2. operate code in class file
     * !important Class:
     * Class - represent total class file
     * Method - represent method code in class file
     * Constructor - constructor in class file
     * Field - static variable and instance variable
     */
    @Test
    public void testReflect() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //new instance, you should keep no args constructor
        Class<?> aClass = Class.forName("org.weiwu.com.User");
        Object o = aClass.newInstance();
        System.out.println(o);
    }

    @Test
    public void testClass() throws ClassNotFoundException {
        //Class.forName("entire class name with package name")
        //lead to class loader.when class loader, static code would execute but other code would not execute.
        Class<?> aClass = Class.forName("org.weiwu.com.User");

        //Type.class
        Class<User> userClass = User.class;

        //Type.getClass()
        User user = new User();
        Class<? extends User> aClass1 = user.getClass();

        System.out.println(aClass + "::" + userClass + "::" + aClass1);
    }

    @Test
    public void testRecompile() throws ClassNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();

        Class<?> aClass = Class.forName("java.lang.Class");
        stringBuilder.append(Modifier.toString(aClass.getModifiers()) + " class " + aClass.getSimpleName() + "{\n");
        Field[] declaredFields = aClass.getDeclaredFields();
        for( Field field : declaredFields ){
            stringBuilder.append("\t");
            stringBuilder.append(Modifier.toString(field.getModifiers()));
            if(field.getModifiers() != 0) stringBuilder.append(" ");
            stringBuilder.append(field.getType().getSimpleName()); //get field type name
            stringBuilder.append(" ");
            stringBuilder.append(field.getName());
            stringBuilder.append(";\n");
        }
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for(Method method : declaredMethods){
            stringBuilder.append("\t");
            stringBuilder.append(Modifier.toString(method.getModifiers()));
            stringBuilder.append(" ");
            stringBuilder.append(method.getReturnType().getSimpleName());
            stringBuilder.append(" ");
            stringBuilder.append(method.getName());
            stringBuilder.append("(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                stringBuilder.append(parameterTypes[i].getSimpleName());
                if(i != parameterTypes.length - 1) stringBuilder.append(", ");
            }
            stringBuilder.append("){}\n");
        }
        stringBuilder.append("}");
        System.out.println(stringBuilder);
    }

    @Test
    public void testAssign() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //not use reflect
        User user = new User();
        user.nickname = "nickname";
        System.out.println("user = " + user);

        //use reflect
        Class<?> aClass = Class.forName("org.weiwu.com.User");
        Object instance = aClass.newInstance();
        Field nickname = aClass.getDeclaredField("nickname"); // public field
        nickname.set(instance, "nickname2");
        Field name = aClass.getDeclaredField("name"); // private field
        name.setAccessible(true); //break the encapsulation
        name.set(instance, "name2");
        System.out.println("nickname.get(instance) = " + nickname.get(instance));
        System.out.println("name = " + name.get(instance));
    }

    @Test
    public void testMethod() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> aClass = Class.forName("org.weiwu.com.User");
        Object o = aClass.newInstance();
        Method print = aClass.getDeclaredMethod("print");
        print.invoke(o);
        Method printArgs = aClass.getDeclaredMethod("printArgs", String.class, String.class);
        Object res = printArgs.invoke(o, "string1", "string2");
        System.out.println(res);
    }

    @Test
    public void testPrint(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user = context.getBean("user", User.class);

        System.out.println(user);
        user.print();
    }

    @Test
    public void testScope(){
        //default condition: bean is singleton
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user1 = context.getBean("user", User.class);
        User user2 = context.getBean("user", User.class);

        System.out.println(user1);
        System.out.println(user2);
        user1.print();
        user2.print();
    }

    @Test
    public void testFactoryBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user = context.getBean("iFactoryBean", User.class);
        System.out.println(user);
//        user.print(); //bug code
    }

    @Test
    public void testLifecycle(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
        user.print();
        System.out.println("第四步，使用bean");
    }

    @Test
    public void testStaticProxy(){
        UserDao userDaoProxy = new UserDaoProxy();
        int res = userDaoProxy.add(1, 4);
        System.out.println("res = " + res);
    }
}
