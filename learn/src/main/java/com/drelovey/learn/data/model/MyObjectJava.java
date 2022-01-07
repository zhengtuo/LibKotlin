package com.drelovey.learn.data.model;

@Deprecated
class MyObjectJava {

    int name;
    public int id = 0;

    public MyObjectJava(int name) {
        this.name = name;
    }

    public String getName() {
        return String.valueOf(name);
    }

}
