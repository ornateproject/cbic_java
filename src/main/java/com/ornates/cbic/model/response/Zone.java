package com.ornates.cbic.model.response;

public class Zone {
    private String name;
    private int code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Zone(String name, int code) {
        this.name = name;
        this.code = code;
    }
}
