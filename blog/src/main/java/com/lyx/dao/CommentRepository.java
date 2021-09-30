package com.lyx.dao;

import com.lyx.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {


    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

    @Query("select c from t_comment c where c.blog.id = ?1")
    List<Comment> findCommentByBlogId(Long id);

}
