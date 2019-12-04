package com.hust.hustsearch.entity;

import org.thymeleaf.spring5.processor.SpringErrorClassTagProcessor;

import java.util.List;

/**
 * @author :younghao
 * @ClassName: PageResultBean
 * @Description: pageresult
 * @date : 12/3/19  10:37 AM
 */
public class PageResultBean<T> {
    //current page
    private int pageNum;
    //the size of per page
    private int pageSize;
    //total num of results
    private long total;
    //total pages of pages
    private int pages;

    //result set
    private List<T> list;
    //navigate's nums
    private int navigatePages;
    //total nums of navigate pages
    private int[] navigatePageNums;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
        //calculate navigate nums
        this.calcNavigatepageNums();
    }

    public int[] getNavigatePageNums() {
        System.out.println("length = " + navigatePageNums.length);
        return navigatePageNums;
    }

    public void setNavigatePageNums(int[] navigatePageNums) {
        this.navigatePageNums = navigatePageNums;
    }

    /**
     * calculate the navigate page numbers
     */
    private void calcNavigatepageNums() {
        if (pages <= navigatePages) {
            navigatePageNums = new int[pages];
            for (int i = 0; i < pages; i++) {
                navigatePageNums[i] = i + 1;
            }
        } else {
            navigatePageNums = new int[navigatePages];
            int startNum = pageNum - navigatePages / 2;
            int endNum = pageNum + navigatePages / 2;

            if (startNum < 1) {
                startNum = 1;
                for (int i = 0; i < navigatePages; i++) {
                    navigatePageNums[i] = startNum++;
                }
            } else if (endNum > pages) {
                endNum = pages;
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatePageNums[i] = endNum--;
                }
            } else {
                for (int i = 0; i < navigatePages; i++) {
                    navigatePageNums[i] = startNum++;
                }

            }
        }
    }
}
