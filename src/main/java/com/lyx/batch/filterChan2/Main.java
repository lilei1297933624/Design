package com.lyx.batch.filterChan2;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链模式，一堆链条执行过滤
 * 怎么终止链式调用？ 用boolean类型终止流程
 */
public class Main {

    public static void main(String[] args) {

//        javax.servlet.Filter filter = new javax.servlet.Filter() {
//            @Override
//            public void doFilter(ServletRequest request, ServletResponse response, javax.servlet.FilterChain chain) throws IOException, ServletException {
//
//            }
//        };

        FilterChain msg = new FilterChain();
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new HtmlFilter());
        FilterChain fc2 = new FilterChain();
        fc2.addFilter(new KeyFilter()).addFilter(new HtmlFilter());
        filterChain.addFilter(fc2);
        Request request = new Request();
        request.str = "大家好： <script>,欢迎访问 abc.com, 大家都是996 ";
        Response response = new Response();
        filterChain.doFilter(request,response,filterChain);
        System.out.println(request.str);
        System.out.println(response.str);
    }
}

class Request{
    String str;
}

class Response{
    String str;
}


    interface Filter {
        boolean doFilter(Request request,Response response, FilterChain msg);
    }

    class HtmlFilter implements Filter {

        @Override
        public boolean doFilter(Request request, Response response, FilterChain m) {
            request.str = request.str.replace("<", "[").replace(">", "]");
            m.doFilter(request,response,m);
            response.str += "-HtmlFilter";
            return true;
        }
    }

    class KeyFilter implements Filter {

        @Override
        public boolean doFilter(Request request, Response response, FilterChain m) {
            request.str = request.str.replace("996", "955");
            m.doFilter(request,response,m);
            response.str += "-keyFilter";
            return true;
        }
    }

    class FilterChain implements Filter {
        int index = 0;
        List<Filter> filters = new ArrayList<>();

        public FilterChain addFilter(Filter filter) {
            this.filters.add(filter);
            return this;
        }

        public void removeFilter(Filter filter) {
            this.filters.remove(filter);
        }

        @Override
        public boolean doFilter(Request request, Response response, FilterChain msg) {
            if(index == filters.size()){
                return false;
            }
            Filter filter = filters.get(index);
            index++;
            return filter.doFilter(request,response,this);
        }
    }
