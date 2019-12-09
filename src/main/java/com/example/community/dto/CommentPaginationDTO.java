package com.example.community.dto;

import java.util.List;

/**
 * @author Amar_amo
 * @date 2019/12/8 22:22
 */
public class CommentPaginationDTO {
    private List<CommentDTO> commentDTOList;
    private int[] nums=new int[10];
    private boolean isFirstPage;
    private boolean isLastPage;
    private int pagenum;
    private int lastpage;
    private long total;

    public int getFirstclick() {
        return firstclick;
    }

    public void setFirstclick(int firstclick) {
        this.firstclick = firstclick;
    }

    private int firstclick = 0;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<CommentDTO> getCommentDTOList() {
        return commentDTOList;
    }

    public void setCommentDTOList(List<CommentDTO> commentDTOList) {
        this.commentDTOList = commentDTOList;
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

    public int getLastpage() {
        return lastpage;
    }

    public void setLastpage(int lastpage) {
        this.lastpage = lastpage;
    }
}
