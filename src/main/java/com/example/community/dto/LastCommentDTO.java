package com.example.community.dto;

import com.example.community.model.Comment;
import com.example.community.model.User;

public class LastCommentDTO
{
    Comment comment;
    User user;

    public Comment getComment()
    {
        return comment;
    }

    public void setComment(Comment comment)
    {
        this.comment = comment;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
