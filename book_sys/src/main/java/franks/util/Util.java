package franks.util;

import franks.model.Page;

import javax.servlet.http.HttpServletRequest;


public class Util {

    //解析搜索和分页的数据：searchText=&sortOrder=asc&pageSize=7&pageNumber=1
    //req.getParameter("searchText")获取到空字符串，如果key“searchText”没有的化，返回null
    public static Page parse(HttpServletRequest req){
        Page p=new Page();
        p.setSearchText(req.getParameter("searchText"));
        p.setSortOrder(req.getParameter("sortOrder"));
        p.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
        p.setPageNumber(Integer.parseInt(req.getParameter("pageNumber")));
        return p;
    }
}
