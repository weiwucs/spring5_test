package org.weiwu.com;

public class UserDaoImpl implements UserDao{
    @Override
    public int add(int a, int b) {
        System.out.println("add method execute");
        return a + b;
    }

    @Override
    public String update(String id) {
        System.out.println("update method execute");
        id += "_expand";
        return id;
    }

    @Override
    public String print(String str) {
        return str;
    }
}
