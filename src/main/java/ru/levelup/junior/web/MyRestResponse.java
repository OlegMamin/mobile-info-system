package ru.levelup.junior.web;

import java.util.List;

/**
 * Created by otherz on 03.12.2019.
 */
public class MyRestResponse {
    private String name;
    private List<String> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
