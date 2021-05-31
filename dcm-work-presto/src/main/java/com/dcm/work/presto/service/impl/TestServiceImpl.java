package com.dcm.work.presto.service.impl;

import com.dcm.work.presto.config.TargetDataSource;
import com.dcm.work.presto.dao.TestDao;
import com.dcm.work.presto.model.TestModel;
import com.dcm.work.presto.service.TestService;
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
