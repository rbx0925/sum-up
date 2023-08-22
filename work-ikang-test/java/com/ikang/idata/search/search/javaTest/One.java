package com.ikang.idata.search.search.javaTest;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/12/14
 */
public class One {

    private Integer ID;
    //private Float Score;
    String name;

    public One(String name,int ID) {//传入数据，进行初始化
        this.name = name;
        this.ID = ID;
    }

    public String toString() {//如果方法名不是toString的话，在直接对class进行输出的时候，将会出错
        return "   name" + ":" + this.name +" " + this.ID;
    }

    public void SetName(String name) {
        this.name = name;
    }
}
