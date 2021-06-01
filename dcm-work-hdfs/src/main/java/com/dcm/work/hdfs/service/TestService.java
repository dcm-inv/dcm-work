package com.dcm.work.hdfs.service;

import com.dcm.work.hdfs.model.TestModel;

import java.util.List;

public interface TestService {

    List<TestModel> queryMaster();

    List<TestModel> querySlave();
}
