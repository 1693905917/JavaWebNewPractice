package com.hzp.servlets;

import com.hzp.fruit.dao.FruitDAO;
import com.hzp.fruit.dao.impl.FruitDAOImpl;
import com.hzp.fruit.pojo.Fruit;
import com.hzp.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: webbegin
 * @BelongsPackage: com.hzp.servlets
 * @Author: ASUS
 * @CreateTime: 2023-06-03  09:33
 * @Description: TODO
 * @Version: 1.0
 */
@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet{
    private FruitDAO fruitDAO=new FruitDAOImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if(StringUtil.isNoEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Fruit fruitByFid = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit",fruitByFid);
            super.processTemplate("edit",request,response);
            //super.processTemplate("edit",request,response);相当于request.getRequestDispatcher("edit.html").forward(request,response);
        }
    }
}
