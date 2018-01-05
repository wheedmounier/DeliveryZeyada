package com.ahmed.deliveryzeyada.data.Remote.api.maps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed Kamal on 05/01/2018.
 */

public class ResturantMapResponse {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("lang")
    @Expose
    private Object lang;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("id")
    @Expose
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getLang() {
        return lang;
    }

    public void setLang(Object lang) {
        this.lang = lang;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
