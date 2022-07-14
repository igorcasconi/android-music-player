package com.example.testapplicationjava.models;

import com.google.gson.annotations.SerializedName;

public class Artists {
  @SerializedName("id")
  String id;
  @SerializedName("name")
  String name;
  @SerializedName("type")
  String type;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
