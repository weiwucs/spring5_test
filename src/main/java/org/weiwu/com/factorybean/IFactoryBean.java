package org.weiwu.com.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.weiwu.com.User;

public class IFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        //need object has public no-args constructor
//        User user = new User();
//        user.setName("name1");
//        user.setNickname("nickname1");
//        return user;
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
