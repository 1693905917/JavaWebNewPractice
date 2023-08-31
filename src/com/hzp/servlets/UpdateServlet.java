package com.hzp.servlets;

import com.hzp.fruit.dao.impl.FruitDAOImpl;
import com.hzp.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: webbegin
 * @BelongsPackage: com.hzp.servlets
 * @Author: ASUS
 * @CreateTime: 2023-06-02  09:27
 * @Description: TODO
 * @Version: 1.0
 */
@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String fidStr = request.getParameter("fid");
        int fid= Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price= Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        int fcount= Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        FruitDAOImpl fruitDAO = new FruitDAOImpl();
        boolean b = fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        System.out.println(b?"修改成功":"修改失败");
        response.sendRedirect("index");
    }
}
