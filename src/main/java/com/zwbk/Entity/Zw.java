package com.zwbk.Entity;

import com.google.gson.JsonObject;

import net.sf.json.JSONObject;

public class Zw {
    private String name;

    private String local;

    private String startTem;

    private String endTem;

    private String landform;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local == null ? null : local.trim();
    }

    public String getStartTem() {
        return startTem;
    }

    public void setStartTem(String startTem) {
        this.startTem = startTem == null ? null : startTem.trim();
    }

    public String getEndTem() {
        return endTem;
    }

    public void setEndTem(String endTem) {
        this.endTem = endTem == null ? null : endTem.trim();
    }

    public String getLandform() {
        return landform;
    }

    public void setLandform(String landform) {
        this.landform = landform == null ? null : landform.trim();
    }

	
}