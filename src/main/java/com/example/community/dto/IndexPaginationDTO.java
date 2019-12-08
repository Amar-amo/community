package com.example.community.dto;

import java.util.List;

/**
 * @author Amar_amo
 * @date 2019/12/8 2:56
 */
public class IndexPaginationDTO {
    List<PublishDTO> publishDTOList;
    private int[] nums=new int[10];
    private boolean isFirstPage;
    private boolean isLastPage;
    private int pagenum;
    private int lastpage;

    public int getLastpage() {
        return lastpage;
    }

    public void setLastpage(int lastpage) {
        this.lastpage = lastpage;
    }

    public List<PublishDTO> getPublishDTOList() {
        return publishDTOList;
    }

    public void setPublishDTOList(List<PublishDTO> publishDTOList) {
        this.publishDTOList = publishDTOList;
    }

    public int[] getNums() {
        return nums;
    }

    public void setNums(int[] nums) {
        this.nums = nums;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }
}
