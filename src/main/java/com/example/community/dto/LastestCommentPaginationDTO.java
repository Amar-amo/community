package com.example.community.dto;

import com.example.community.model.Comment;
import com.example.community.model.User;

import java.util.List;

public class LastestCommentPaginationDTO
{
    List<LastCommentDTO> lastCommentDTOList;

    public List<LastCommentDTO> getLastCommentDTOList()
    {
        return lastCommentDTOList;
    }

    public void setLastCommentDTOList(List<LastCommentDTO> lastCommentDTOList)
    {
        this.lastCommentDTOList = lastCommentDTOList;
    }

    private int[] nums=new int[10];
    private boolean isFirstPage;
    private boolean isLastPage;
    private int pagenum;
    private int lastpage;


    public int[] getNums()
    {
        return nums;
    }

    public void setNums(int[] nums)
    {
        this.nums = nums;
    }

    public boolean isFirstPage()
    {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage)
    {
        isFirstPage = firstPage;
    }

    public boolean isLastPage()
    {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage)
    {
        isLastPage = lastPage;
    }

    public int getPagenum()
    {
        return pagenum;
    }

    public void setPagenum(int pagenum)
    {
        this.pagenum = pagenum;
    }

    public int getLastpage()
    {
        return lastpage;
    }

    public void setLastpage(int lastpage)
    {
        this.lastpage = lastpage;
    }
}
