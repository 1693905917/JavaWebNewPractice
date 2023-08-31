package com.hzp.fruit.dao;


import com.hzp.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    //查询库存列表
    List<Fruit> getFruitList();

    //提供page查询列表
    List<Fruit> getFruitListByPage(Integer page);

    //提供page查询列表
    List<Fruit> getFruitListByKeyword(String keyword,Integer page);

    Integer getFruitCount();

    Integer getFruitCountByKeyword(String keyword);

    //新增库存
    boolean addFruit(Fruit fruit);

    //修改库存
    boolean updateFruit(Fruit fruit);

    //根据名称查询特定库存
    Fruit getFruitByFname(String fname);

    //删除特定库存记录
    boolean delFruit(String fname);

    Fruit getFruitByFid(Integer fid);

    boolean deleteFruitById(Integer fid);
}
