package com.bolsadeideas.springboot.app.util.paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageRender<T> {

    private String url;
    private Page<T> page;

    private int totalPages;

    private int numberElementsByPages;

    private int pageActual;

    private List<PageItem> pages;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumberElementsByPages() {
        return numberElementsByPages;
    }

    public void setNumberElementsByPages(int numberElementsByPages) {
        this.numberElementsByPages = numberElementsByPages;
    }

    public int getPageActual() {
        return pageActual;
    }

    public void setPageActual(int pageActual) {
        this.pageActual = pageActual;
    }

    public List<PageItem> getPages() {
        return pages;
    }

    public void setPages(List<PageItem> pages) {
        this.pages = pages;
    }

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.pages = new ArrayList<PageItem>();

        numberElementsByPages = page.getSize();
        totalPages = page.getTotalPages();
        pageActual = page.getNumber() + 1;


        Map<String, Integer> items = getInitPage(numberElementsByPages, pageActual, totalPages);

        for(int i=0; i < items.get("end"); i++) {
            pages.add(new PageItem(items.get("init") + i, page.getNumber() == items.get("init")+i));
        }
    }

    public Map<String, Integer> getInitPage(int numItemsByPage, int currentPage, int totalPages) {

        Map<String, Integer> items = new HashMap<>();

        int start, end;

        if (totalPages <= numItemsByPage) {
            start = 1;
            end = totalPages;
        }else {
            if (currentPage <= numItemsByPage/2){
                start = 1;
                end = numItemsByPage;

            }else if (currentPage >= totalPages - numItemsByPage/2){
                start = totalPages - numItemsByPage + 1;
                end = numItemsByPage;
            }else {
                start = currentPage - numItemsByPage/2;
                end = numItemsByPage;
            }
        }


        items.put("init", start);
        items.put("end", end);

        return items;
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean isHasNext() {
        return page.hasNext();
    }

    public boolean isHasPrevious() {
        return page.hasPrevious();
    }
}
