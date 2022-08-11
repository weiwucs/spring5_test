package org.weiwu.com;

public class Person {
    private String name;
    private String nickname;
    private String address;
    private String idCode;

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", address='" + address + '\'' +
                ", idCode='" + idCode + '\'' +
                '}';
    }

    public void print(){
        System.out.println(toString());
    }
}
