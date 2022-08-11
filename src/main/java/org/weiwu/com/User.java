package org.weiwu.com;

public class User {

    public String nickname;
    private String name;

    private Person person;
    private Person person2;

    User(){
        System.out.println("第一步，无参构造器创建对象");
    }
    public void setPerson2(Person person2) {
        this.person2 = person2;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("第二步，set方法进行赋值");
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void print(){
        System.out.println(nickname + "::" + name + "::" + person.toString() + "::" + person2.toString());
    }

    public String printArgs(String str, String str2){
        return str + "::" + str2;
    }

    public void initMethod(){
        System.out.println("第三步，初始化方法");
    }

    public void destroyMethod(){
        System.out.println("第五步，执行销毁的方法");
    }
}
