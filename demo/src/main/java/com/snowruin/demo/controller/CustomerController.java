package com.snowruin.demo.controller;


import com.snowruin.demo.service.CustomerService;
import com.snowruin.web4j.framework.common.annotation.Action;
import com.snowruin.web4j.framework.common.annotation.Controller;
import com.snowruin.web4j.framework.common.annotation.Inject;
import com.snowruin.web4j.framework.common.bean.Data;
import com.snowruin.web4j.framework.common.bean.FileParam;
import com.snowruin.web4j.framework.common.bean.Param;
import com.snowruin.web4j.framework.common.bean.View;

import java.util.Map;


/**
 * @ClassName Controller
 * @Description TODO
 * @Author zxm
 * @Date 2018/12/17 11:55
 * @Version 1.0
 **/

@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;

    @Action("get:/customerList")
    public Data customerList(){
        return new Data(customerService.getCustomerList());
    }


    @Action("get:/getCustomer")
    public View getCustomer(Param param){
        return new View("customer.jsp").addModel("data",customerService.getCustomer(param.getLong("id")));
    }


    @Action("post:/customerUpload")
    public Data customerUpload(Param param){
       // Map<String, Object> paramsMap = param.getParamsMap();
        //param.get
        Map<String, Object> fieldMap = param.getFieldMap();

        FileParam file = param.getFile("file");

        boolean customer = customerService.createCustomer(fieldMap, file);

        return new Data(customer);
    }

    @Action("get:/customerCreate")
    public View customerCreate(){
        return new View("customer_create.jsp");
    }

}
