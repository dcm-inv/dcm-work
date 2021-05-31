package com.dcm.work.impala.service.impl;

import com.dcm.work.impala.config.TargetDataSource;
import com.dcm.work.impala.dao.TestDao;
import com.dcm.work.impala.model.TestModel;
import com.dcm.work.impala.service.TestService;
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
