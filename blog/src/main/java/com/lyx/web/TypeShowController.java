package com.lyx.web;

import com.lyx.pojo.Type;
import com.lyx.service.BlogService;
import com.lyx.service.TypeService;
import com.lyx.vo.BlogQuery;
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
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 2,sort = {"id"},direction = Sort.Direction.DESC)
                        Pageable pageable,
                        Model module,
                        @PathVariable Long id){
        List<Type> types = typeService.listTypeTop(10000);
        if (id == -1) {
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        module.addAttribute("types", types);
        module.addAttribute("page", blogService.listBlog(pageable,blogQuery));
        module.addAttribute("activeTypeId", id);
        return "types";
    }
}
