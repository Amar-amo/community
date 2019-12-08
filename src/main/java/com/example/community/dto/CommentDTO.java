package com.example.community.dto;

import java.sql.Timestamp;

/**
 * @author Amar_amo
 * @date 2019/12/8 21:19
 */
public class CommentDTO {
    private long id;
    private long commentator;
    private String content;
    private long likeCount;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp modifiedTime;
    private long publishedId;
    private long readMark;
    private String avatar_url;
    private String name;
    private String bio;

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

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public long getPublishedId() {
        return publishedId;
    }

    public void setPublishedId(long publishedId) {
        this.publishedId = publishedId;
    }

    public long getReadMark() {
        return readMark;
    }

    public void setReadMark(long readMark) {
        this.readMark = readMark;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
