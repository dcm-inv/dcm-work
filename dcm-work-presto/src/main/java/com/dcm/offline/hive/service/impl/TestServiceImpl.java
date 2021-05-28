package com.dcm.offline.hive.service.impl;

import com.dcm.offline.hive.config.TargetDataSource;
import com.dcm.offline.hive.dao.TestDao;
import com.dcm.offline.hive.model.TestModel;
import com.dcm.offline.hive.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    @TargetDataSource(name = "master")
    public List<TestModel> queryMaster(){
        return testDao.queryMaster();
    }

    @Override
    @TargetDataSource(name = "slave")
    public List<TestModel> querySlave(){
        return testDao.querySlave();
    }
}
