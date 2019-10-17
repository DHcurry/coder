package com.deng.coder.dto;

import java.util.ArrayList;
import java.util.List;

public class PageShowDTO {
    private ArrayList<ArticleListDTO> articleListDTOS;
    private boolean showPrevious = true;
    private boolean showFirst = true;
    private boolean showNext = true;
    private boolean showLast = true;
    private int page;
    private ArrayList<Integer> pages;
    private int pageCount;

    public ArrayList<ArticleListDTO> getArticleListDTOS() {
        return articleListDTOS;
    }

    public void setArticleListDTOS(ArrayList<ArticleListDTO> articleListDTOS) {
        this.articleListDTOS = articleListDTOS;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowFirst() {
        return showFirst;
    }

    public void setShowFirst(boolean showFirst) {
        this.showFirst = showFirst;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowLast() {
        return showLast;
    }

    public void setShowLast(boolean showLast) {
        this.showLast = showLast;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Integer> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Integer> pages) {
        this.pages = pages;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }


    public void pageManage(int page, int size, int totalCount) {
        this.page = page;
        if(totalCount % size == 0)
            pageCount = (totalCount / size);
        else
            pageCount = (totalCount / size)+1;

        // 向pages中添加数据
        // 要求：每次展示5个，不足向后取
        pages = new ArrayList<>();
        for(int i = page-2,count=0; count <= 5 && i <= pageCount ; i++){
            if(i <= 0)
                continue;
            pages.add(i);
            count++;
        }

        // 当第一页的时候不展示两个向前按钮
        if(pages.contains(1)){
            setShowPrevious(false);
            if(page == 1){
                setShowPrevious(false);
            }
        }

        // 当最后一页的时候不展示两个向后按钮
        if(pages.contains(pageCount)){
            setShowLast(false);
            if(page == pageCount)
                setShowNext(false);
        }

    }
}
