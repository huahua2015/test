package com.example.mybuider;

public class Person {
    private String name;
    private String sex;
    private int age;

    /**
     * 构造方法，传入builder对象
     * @param builder 对象
     */
    public Person(Builder builder) {
        this.name = builder.name;
        this.sex = builder.sex;
        this.age = builder.age;
    }

    /**
     *
     * get  set 方法
     */
    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }


    /**
     *
     * 静态内部类 Builder类  包含所有Person属性。
     */
    public static class Builder{
        private String name;
        private String sex;
        private int age;

        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Builder sex(String sex){
            this.sex=sex;
            return this;
        }
        public Builder age(int age){
            this.age=age;
            return this;
        }

        public Person builder(){
            return new Person(this);

        }
    }
}
