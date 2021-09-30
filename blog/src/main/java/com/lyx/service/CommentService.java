package com.lyx.service;

import com.lyx.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

    void deleteCommentById(Long id);

    List<Comment> getAllCommentByBlogId(Long id);

    void deleteComment(Long id);
}
