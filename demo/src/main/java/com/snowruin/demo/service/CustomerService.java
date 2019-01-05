package com.snowruin.demo.service;

import com.snowruin.web4j.framework.common.annotation.Service;
import com.snowruin.web4j.framework.common.annotation.Transaction;
import com.snowruin.web4j.framework.common.bean.FileParam;
import com.snowruin.web4j.framework.common.hepler.DatabaseHelper;
import com.snowruin.demo.model.Customer;
import com.snowruin.web4j.framework.common.hepler.UploadHelper;

import java.io.File;
import java.util.List;

/**
 * @ClassName CustomerService
 * @Description TODO  客户service
 * @Author zxm
 * @Date 2018/12/14 16:15
 * @Version 1.0
 **/
@Service
public class CustomerService {

    /**
     * 获取客户列表
     * @return
     */
    @Transaction
    public List<Customer> getCustomerList(){
        String sql = "select * from customer";
        return  DatabaseHelper.queryEntityList(Customer.class,sql);
    }


    /**
     * 获取客户
     */
    public Customer getCustomer(long id ){
        return DatabaseHelper.queryEntity(Customer.class,"select * from customer where id = ?",id);
    }

    /**
     * 创建客户
     * @param fieldMap
     * @return
     */
    @Transaction
    public boolean createCustomer(java.util.Map<String,Object> fieldMap){
        return DatabaseHelper.insertEntity(Customer.class,fieldMap);
    }


    public boolean createCustomer(java.util.Map<String,Object> fieldMap, FileParam fileParam){
       // boolean b = DatabaseHelper.insertEntity(Customer.class, fieldMap);

      //  if(b){
            UploadHelper.uploadFile(System.getProperty("java.io.tmpdir")+File.separator,fileParam);
       // }
        return false;
    }


    /**
     * 更新客户信息
     * @param id
     * @param fieldMap
     * @return
     */
    @Transaction
    public boolean updateCustomer(long id,java.util.Map<String,Object> fieldMap){
        return DatabaseHelper.updateEntity(Customer.class,id,fieldMap);
    }

    /**
     * 删除客户信息
     * @param id
     * @return
     */
    @Transaction
    public boolean deleteCustomer(long id){
        return DatabaseHelper.deleteEntity(Customer.class,id);
    }
}
