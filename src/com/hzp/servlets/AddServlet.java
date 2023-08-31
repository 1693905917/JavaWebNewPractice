package com.hzp.servlets;

import com.hzp.fruit.dao.FruitDAO;
import com.hzp.fruit.dao.impl.FruitDAOImpl;
import com.hzp.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: webbegin
 * @BelongsPackage: com.hzp.servlets
 * @Author: ASUS
 * @CreateTime: 2023-06-03  20:39
 * @Description: TODO
 * @Version: 1.0
 */
@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet{
    private FruitDAO fruitDAO=new FruitDAOImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price= Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        int fcount= Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");
        boolean b = fruitDAO.addFruit(new Fruit(null, fname, price, fcount, remark));
        System.out.println(b?"添加成功":"添加失败");
        response.sendRedirect("index");
    }
}
