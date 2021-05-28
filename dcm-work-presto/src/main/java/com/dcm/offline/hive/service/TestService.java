package com.dcm.offline.hive.service;

import com.dcm.offline.hive.model.TestModel;

import java.util.List;

public interface TestService {

    List<TestModel> queryMaster();

    List<TestModel> querySlave();
}
