package com.example.community.model;


public class Comment {

  private long id;
  private long commentator;
  private String content;
  private long like_count;
  private java.sql.Timestamp create_time;
  private java.sql.Timestamp modified_time;
  private long page_id;
  private long read_mark;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCommentator() {
    return commentator;
  }

  public void setCommentator(long commentator) {
    this.commentator = commentator;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public long getLike_count() {
    return like_count;
  }

  public void setLike_count(long like_count) {
    this.like_count = like_count;
  }


  public java.sql.Timestamp getCreate_time() {
    return create_time;
  }

  public void setCreate_time(java.sql.Timestamp create_time) {
    this.create_time = create_time;
  }


  public java.sql.Timestamp getModified_time() {
    return modified_time;
  }

  public void setModified_time(java.sql.Timestamp modified_time) {
    this.modified_time = modified_time;
  }


  public long getPage_id() {
    return page_id;
  }

  public void setPage_id(long page_id) {
    this.page_id = page_id;
  }


  public long getRead_mark() {
    return read_mark;
  }

  public void setRead_mark(long read_mark) {
    this.read_mark = read_mark;
  }

}
