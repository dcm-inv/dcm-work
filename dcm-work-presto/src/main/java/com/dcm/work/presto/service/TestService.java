package com.dcm.work.presto.service;

import com.dcm.work.presto.model.TestModel;

import java.util.List;

public interface TestService {

    List<TestModel> queryMaster();

    List<TestModel> querySlave();
}
