package com.dcm.work.hive.dao;


import com.dcm.work.hive.model.TestModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TestDao {
    @Select("select * from result_data_upload")
    List<TestModel> queryMaster();
    @Select("select * from result_data_upload limit 1")
    List<TestModel> querySlave();
}
