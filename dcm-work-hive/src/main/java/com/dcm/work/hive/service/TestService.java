package com.dcm.work.hive.service;

import com.dcm.work.hive.model.TestModel;

import java.util.List;

public interface TestService {

    List<TestModel> queryMaster();

    List<TestModel> querySlave();
}
