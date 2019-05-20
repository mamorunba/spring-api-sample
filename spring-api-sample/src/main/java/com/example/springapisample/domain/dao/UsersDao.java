package com.example.springapisample.domain.dao;
import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.springapisample.domain.model.UsersEntity;

@ConfigAutowireable
@Dao
public interface UsersDao {

    @Select
    List<UsersEntity> selectAll();

}
