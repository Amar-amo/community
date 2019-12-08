package com.example.community.model;


public class User {

  private long id;
  private String email;
  private String password;
  private String name;
  private String account_id;
  private String bio;
  private String token;
  private java.sql.Date create_time;
  private java.sql.Date modified_time;
  private String avatar_url;

  public String getAvatar_url() {
    return avatar_url;
  }

  public void setAvatar_url(String avatar_url) {
    this.avatar_url = avatar_url;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getAccount_id() {
    return account_id;
  }

  public void setAccount_id(String account_id) {
    this.account_id = account_id;
  }


  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }


  public java.sql.Date getCreate_time() {
    return create_time;
  }

  public void setCreate_time(java.sql.Date create_time) {
    this.create_time = create_time;
  }


  public java.sql.Date getModified_time() {
    return modified_time;
  }

  public void setModified_time(java.sql.Date modified_time) {
    this.modified_time = modified_time;
  }

}
