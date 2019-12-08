package com.example.community.model;


public class Publish {

  private long id;
  private String title;
  private String description;
  private long creator;
  private java.sql.Date create_time;
  private java.sql.Date modified_time;
  private long comment_count;
  private long view_count;
  private long like_count;
  private String tag;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public long getCreator() {
    return creator;
  }

  public void setCreator(long creator) {
    this.creator = creator;
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


  public long getComment_count() {
    return comment_count;
  }

  public void setComment_count(long comment_count) {
    this.comment_count = comment_count;
  }


  public long getView_count() {
    return view_count;
  }

  public void setView_count(long view_count) {
    this.view_count = view_count;
  }


  public long getLike_count() {
    return like_count;
  }

  public void setLike_count(long like_count) {
    this.like_count = like_count;
  }


  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

}
