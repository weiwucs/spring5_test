package org.weiwu.com.staticproxy;

import org.weiwu.com.UserDao;
import org.weiwu.com.UserDaoImpl;

/**
 * Static proxy for interface
 * advantage:
 * 1. make business handled by the role more pure
 * 2. common business is processed by proxy
 * 3. common business expand is more centralized and convenient
 * disadvantage:
 * 1. class is more, work is more, efficiency is lower
 */
public class UserDaoProxy implements UserDao {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public int add(int a, int b) {
        log("add");
        int res = userDao.add(a, b);
        return res;
    }

    @Override
    public String update(String id) {
        log("update");
        String res = userDao.update(id);
        return res;
    }

    @Override
    public String print(String str) {
        return null;
    }

    public void log(String methodName){
        System.out.println("execute " + methodName);
    }
}
