package com.hzp.servlets;

import com.hzp.fruit.dao.FruitDAO;
import com.hzp.fruit.dao.impl.FruitDAOImpl;
import com.hzp.fruit.pojo.Fruit;
import com.hzp.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @BelongsProject: webbegin
 * @BelongsPackage: com.hzp.servlets
 * @Author: ASUS
 * @CreateTime: 2023-06-05  09:56
 * @Description: TODO
 * @Version: 1.0
 */
@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet{
    private Integer PageCountSum=3;
    private FruitDAO fruitDAO=new FruitDAOImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String operate = request.getParameter("operate");
        //默认是index页面
        if(StringUtil.isEmpty(operate)){
            operate="index";
        }
        switch (operate){
            case "index":
                index(request,response);
                break;
            case "delete":
                delete(request,response);
                break;
            case "edit":
                edit(request,response);
                break;
            case "update":
                update(request,response);
                break;
            case "add":
                add(request,response);
                break;
            default:
                throw new RuntimeException("operate值非法");
        }

    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("utf-8");
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price= Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        int fcount= Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");
        boolean b = fruitDAO.addFruit(new Fruit(null, fname, price, fcount, remark));
        System.out.println(b?"添加成功":"添加失败");
        response.sendRedirect("fruit.do?operate=index");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        FruitDAO fruitDAO=new FruitDAOImpl();
        String fidStr = request.getParameter("fid");
        if(StringUtil.isNoEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            boolean b = fruitDAO.deleteFruitById(fid);
            System.out.println(b?"删除成功":"删除失败");
            response.sendRedirect("index");
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //默认PageNum=1
        Integer PageNum=1;
        String keyword=null;
        HttpSession session = request.getSession();
        FruitDAOImpl fruitDAO = new FruitDAOImpl();
        //区分是查询操作还是非查询操作
        String search = request.getParameter("oper");
        //区分是查询操作还是非查询操作
        if(StringUtil.isNoEmpty(search)&& "search".equals(search)){
            //保证查询时不为空，为空就还是当前页面效果
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为1 ， keyword应该从请求参数中获取
            PageNum = 1 ;
            //如是查询操作：我们要获取用户输入关键字
            keyword = request.getParameter("keyword");
            //存储用户输入的关键字：
            if(StringUtil.isEmpty(keyword)){
                keyword = "" ;
            }
            session.setAttribute("keyword",keyword);
        }else {//当不是查询操作时
            String pageStr = request.getParameter("page");
            if(StringUtil.isNoEmpty(pageStr)){
                PageNum = Integer.parseInt(pageStr);
            }
            Object keywordObject = session.getAttribute("keyword");
            if(keywordObject!=null){
                keyword=(String) keywordObject;
            }else {
                //注意""与Null的区别
                keyword="";
            }
        }
        session.setAttribute("PageNum",PageNum);

        List<Fruit> fruitList = fruitDAO.getFruitListByKeyword(keyword,PageNum);
        //保持到session作用域
        session.setAttribute("fruitList",fruitList);
        //总记录条数
        Integer fruitCount = fruitDAO.getFruitCountByKeyword(keyword);
        //总页数
        int pageCount=(fruitCount+PageCountSum-1)/PageCountSum;
        session.setAttribute("pageCount",pageCount);
        //此时视图名称是  index
        //那么thymeleaf会将这个 逻辑视图名称  对应到 物理视图名词上去
        //逻荐视图名称：index
        //物理视图名称：view-prefix+逻辑视图名称+view-suffix
        //所以真实的视图名称是：/+    index     +   .html
        super.processTemplate("index",request,response);

    }

    //点击加入编辑页面
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if(StringUtil.isNoEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Fruit fruitByFid = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit",fruitByFid);
            //request.getRequestDispatcher("fruit.do?operate=edit").forward(request,response);
            super.processTemplate("edit",request,response);
            //super.processTemplate("edit",request,response);相当于request.getRequestDispatcher("edit.html").forward(request,response);
        }
    }

    //进行编辑修改操作
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //request.setCharacterEncoding("utf-8");
        String fidStr = request.getParameter("fid");
        int fid= Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price= Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        int fcount= Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");
        //FruitDAOImpl fruitDAO = new FruitDAOImpl();
        boolean b = fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        System.out.println(b?"修改成功":"修改失败");
        //request.getRequestDispatcher("fruit.do?operate=index").forward(request,response);
        response.sendRedirect("fruit.do?operate=index");
    }




}
