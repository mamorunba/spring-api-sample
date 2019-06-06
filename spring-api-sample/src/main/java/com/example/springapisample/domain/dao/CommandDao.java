package com.example.springapisample.domain.dao;
import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.example.springapisample.domain.model.CommandEntity;

@ConfigAutowireable
@Dao
public interface CommandDao {

    @Select
    List<CommandEntity> selectAll();

    @Select
    CommandEntity selectById(int id);

    @Insert
    int insert(CommandEntity entity);

    @Update
    int update(CommandEntity entity);
}
