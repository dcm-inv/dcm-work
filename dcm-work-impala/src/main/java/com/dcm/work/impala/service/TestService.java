package com.dcm.work.impala.service;

import com.dcm.work.impala.model.TestModel;

import java.util.List;

public interface TestService {

    List<TestModel> queryMaster();

    List<TestModel> querySlave();
}
