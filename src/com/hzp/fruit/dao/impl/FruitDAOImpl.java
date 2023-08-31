package com.hzp.fruit.dao.impl;

import com.hzp.fruit.dao.FruitDAO;
import com.hzp.fruit.dao.base.BaseDAO;
import com.hzp.fruit.pojo.Fruit;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    private Integer PageCount=3;
    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }

    @Override
    public List<Fruit> getFruitListByPage(Integer page) {
        String sql="select * from t_fruit limit ?,?";
        return super.executeQuery(sql,PageCount*(page-1),PageCount);
    }

    @Override
    public List<Fruit> getFruitListByKeyword(String keyword, Integer page) {
        String sql="select * from t_fruit where fname like ? or remark like ? limit ?,?";
        return super.executeQuery(sql,"%"+keyword+"%","%"+keyword+"%",PageCount*(page-1),PageCount);
    }

    @Override
    public Integer getFruitCount() {
        //注意select count(*) from t_fruit查询返回的是long类型
        return ((Long)super.executeComplexQuery("select count(*) from t_fruit")[0]).intValue();
    }

    @Override
    public Integer getFruitCountByKeyword(String keyword) {
        return ((Long)super.executeComplexQuery(
                "select count(*) from t_fruit where fname like ? or remark like ?","%"+keyword+"%","%"+keyword+"%")[0]
        ).intValue();
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        int count = super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark()) ;
        //insert语句返回的是自增列的值，而不是影响行数
        //System.out.println(count);
        return count>0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname=?, price=?,fcount=?,remark=? where fid = ? " ;
        return super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid())>0;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        return super.load("select * from t_fruit where fname like ? ",fname);
    }

    @Override
    public boolean delFruit(String fname) {
        String sql = "delete from t_fruit where fname like ? " ;
        return super.executeUpdate(sql,fname)>0;
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        String sql="select * from t_fruit where fid=?";
        return super.load(sql,fid);
    }

    @Override
    public boolean deleteFruitById(Integer fid) {
        String sql="delete from t_fruit where fid=?";
        return super.executeUpdate(sql,fid)>0;
    }
}