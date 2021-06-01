package com.dcm.work.hdfs.controller;

import com.dcm.work.hdfs.model.TestModel;
import com.dcm.work.hdfs.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;
    @RequestMapping(value = "master", method = RequestMethod.POST)
    @ResponseBody
    public List<TestModel> testMaster(){
        return testService.queryMaster();
    }
    @RequestMapping(value = "slave", method = RequestMethod.POST)
    @ResponseBody
    public List<TestModel> testSlave(){
        return testService.querySlave();
    }
}
