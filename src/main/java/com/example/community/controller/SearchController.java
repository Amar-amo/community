package com.example.community.controller;

import com.example.community.dto.IndexPaginationDTO;
import com.example.community.mapper.PublishMapper;
import com.example.community.model.Publish;
import com.example.community.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController
{
    @Value("${pagesize}")
    private int pagesize;

    @Autowired
    private PaginationService paginationService;

    @PostMapping("/search")
    public String Searchresult(HttpServletRequest request,
                               @RequestParam(name = "page",defaultValue = "1")int pagenum ){
        String keyword = request.getParameter("keyword");
        IndexPaginationDTO indexPaginationDTO = paginationService.list(pagenum,pagesize,keyword);
        request.getSession().setAttribute("pagenum",pagenum);
        request.getSession().setAttribute("pagination", indexPaginationDTO);
        return "searchresult";


    }
}
