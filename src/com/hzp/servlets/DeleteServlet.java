package com.hzp.servlets;

import com.hzp.fruit.dao.FruitDAO;
import com.hzp.fruit.dao.impl.FruitDAOImpl;
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
 * @CreateTime: 2023-06-03  20:14
 * @Description: TODO
 * @Version: 1.0
 */
@WebServlet("/del.do")
public class DeleteServlet extends ViewBaseServlet{
   private FruitDAO fruitDAO=new FruitDAOImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        FruitDAO fruitDAO=new FruitDAOImpl();
        String fidStr = request.getParameter("fid");
        if(StringUtil.isNoEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            boolean b = fruitDAO.deleteFruitById(fid);
            System.out.println(b?"删除成功":"删除失败");
            response.sendRedirect("index");
        }
    }
}
