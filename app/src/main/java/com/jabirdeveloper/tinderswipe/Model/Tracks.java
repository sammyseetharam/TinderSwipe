package com.jabirdeveloper.tinderswipe.Model;

import java.util.ArrayList;

public class Tracks {
    private String href;
    private ArrayList<Item> items;
    private int limit;
    private String next;
    private int offset;
    private String previous;
    private int total;

    public Tracks(String href, ArrayList<Item> items, int limit, String next, int offset, String previous, int total) {
        this.href = href;
        this.items = items;
        this.limit = limit;
        this.next = next;
        this.offset = offset;
        this.previous = previous;
        this.total = total;
    }

    public Tracks(){
        this.href = null;
        this.items = null;
        this.limit = 0;
        this.next = null;
        this.offset = 0;
        this.previous = null;
        this.total = 0;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
