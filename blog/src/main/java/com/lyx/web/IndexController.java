package com.lyx.web;

import com.lyx.service.BlogService;
import com.lyx.service.TagService;
import com.lyx.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.PushBuilder;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)
                        Pageable pageable,
                        Model module){
        module.addAttribute("page", blogService.listBlog(pageable));
        module.addAttribute("types", typeService.listTypeTop(6));
        module.addAttribute("tags", tagService.listTagTop(10));
        module.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        return "index";
    }

    @GetMapping("/search")
    public String search(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)
                         Pageable pageable,
                         Model module,
                         @RequestParam String query){
        module.addAttribute("page", blogService.listBlog(query, pageable));
        module.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        System.out.println(id);
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newBlogs(Model model){
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        System.out.println("----------------------");
        return "_fragments :: newblogList";
    }
}
