package com.ornates.cbic.model.response;

public class Zone {
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Zone(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
