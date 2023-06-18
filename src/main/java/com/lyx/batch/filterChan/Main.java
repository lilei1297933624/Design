package com.lyx.batch.filterChan;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链模式，一堆链条执行过滤
 * 怎么终止链式调用？ 用boolean类型终止流程
 */
public class Main {

    public static void main(String[] args) {
        
        Msg msg = new Msg();
        msg.setMsg("大家好： <script>,欢迎访问 abc.com, 大家都是996 ");
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new HtmlFilter());
        FilterChain fc2 = new FilterChain();
        fc2.addFilter(new KeyFilter()).addFilter(new HtmlFilter());
        filterChain.addFilter(fc2);
        filterChain.doFilter(msg);
        System.out.println(msg.getMsg());
    }
}

class Msg{
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

interface Filter{
    boolean doFilter(Msg msg);
}

class HtmlFilter implements Filter{

    @Override
    public boolean doFilter(Msg m) {
        String msg = m.getMsg();
        m.setMsg(msg.replace("<","[").replace(">","]"));
        return false;
    }
}

class KeyFilter implements Filter{

    @Override
    public boolean doFilter(Msg m) {
        m.setMsg(m.getMsg().replace("996","955"));
        return false;
    }
}

class FilterChain implements Filter{
    List<Filter> filters = new ArrayList<>();

    public FilterChain addFilter(Filter filter){
        this.filters.add(filter);
        return this;
    }

    public void removeFilter(Filter filter){
        this.filters.remove(filter);
    }

    @Override
    public boolean doFilter(Msg msg) {
        for(Filter filter : filters){
            if(!filter.doFilter(msg)){
                return false;
            }
        }
        return true;
    }
}
