package com.lyx.service;

import com.lyx.dao.CommentRepository;
import com.lyx.pojo.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1){
            comment.setParentComment(commentRepository.findById(parentCommentId).get());
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void deleteCommentById(Long id) {
       commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getAllCommentByBlogId(Long id) {
        return commentRepository.findCommentByBlogId(id);
    }

    @Transactional
    @Override
    public void deleteComment(Long id){
        List<Comment> comments = getAllCommentByBlogId(id);
        List<Comment> commentList = new ArrayList<>();
        List<Comment> commentList1 = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getParentComment() != null){
                commentList.add(comment);
            }else {
                commentList1.add(comment);
            }
        }
        deleteParentIsNotNull(commentList);
        for (Comment comment : commentList1) {
            deleteCommentById(comment.getId());
        }
    }

    public void deleteParentIsNotNull(List<Comment> commentList){
        Collections.reverse(commentList);
        for (Comment comment : commentList){
            deleteCommentById(comment.getId());
        }
    }


    /**
     * ?????????????????????????????????
     * @param comments
     * @return
     */
    public List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments){
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //??????????????????????????????????????????????????????
        combineChildren(commentsView);
        return commentsView;
    }

    //??????????????????????????????????????????
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     *
     * @param comments root????????????blog????????????????????????
     */
    public void combineChildren(List<Comment> comments){
        for (Comment comment : comments){
            List<Comment> replys1 = comment.getReplyComments();
            for (Comment reply1 : replys1){
                //???????????????????????????????????????tempReplys???
                recursively(reply1);
            }
            //?????????????????????reply?????????????????????????????????
            comment.setReplyComments(tempReplys);
            //?????????????????????
            tempReplys = new ArrayList<>();
        }
    }

    /**
     * ????????????????????????
     * @param comment ???????????????
     */
    public void recursively(Comment comment){
        tempReplys.add(comment);//????????????????????????????????????
        if (comment.getReplyComments().size() > 0){
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys){
                tempReplys.add(reply);
                if (reply.getReplyComments().size() > 0){
                    recursively(reply);
                }
            }
        }
    }
}
