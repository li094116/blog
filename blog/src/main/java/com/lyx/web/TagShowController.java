package com.lyx.web;

import com.lyx.pojo.Tag;
import com.lyx.service.BlogService;
import com.lyx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String Tags(@PageableDefault(size = 2,sort = {"id"},direction = Sort.Direction.DESC)
                        Pageable pageable,
                        Model module,
                        @PathVariable Long id){
        List<Tag> tags = tagService.listTagTop(10000);
        if (id == -1) {
            id = tags.get(0).getId();
        }
        module.addAttribute("tags", tags);
        module.addAttribute("page", blogService.listBlog(id, pageable));
        module.addAttribute("activeTagId", id);
        return "tags";
    }
}
