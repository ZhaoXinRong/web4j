package com.snowruin.web4j.framework.common.hepler;

import com.google.common.collect.Lists;
import com.snowruin.web4j.framework.common.lang.ThreadLocalCustom;
import com.snowruin.web4j.framework.common.utils.PropsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * @ClassName DatabaseHelper
 * @Description TODO 数据库操作类
 * @Author zxm
 * @Date 2018/12/15 9:52
 * @Version 1.0
 **/
@Slf4j
public class DatabaseHelper {
    private static  final String DRIVERCLASSNAME;

    private static  final String URL;

    private static  final String USERNAME;

    private static  final String PASSWORD;

    private static  final QueryRunner QUERY_RUNNER;

    private static  final ThreadLocalCustom<Connection> CONNECTION_THREAD_LOCAL;

    private static  final BasicDataSource BASIC_DATA_SOURCE;

    static {
        QUERY_RUNNER = new QueryRunner();

        CONNECTION_THREAD_LOCAL = new ThreadLocalCustom<>();

        Properties properties = PropsUtils.loadProps("web4j.properties");
        DRIVERCLASSNAME = properties.getProperty("web4j.framework.jdbc.driverClassName");
        URL = properties.getProperty("web4j.framework.jdbc.url");
        USERNAME = properties.getProperty("web4j.framework.jdbc.username");
        PASSWORD = properties.getProperty("web4j.framework.jdbc.password");

        BASIC_DATA_SOURCE = new BasicDataSource();
        BASIC_DATA_SOURCE.setDriverClassName(DRIVERCLASSNAME);
        BASIC_DATA_SOURCE.setUrl(URL);
        BASIC_DATA_SOURCE.setUsername(USERNAME);
        BASIC_DATA_SOURCE.setPassword(PASSWORD);
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        try {
            if(Objects.isNull(connection)){
               connection = BASIC_DATA_SOURCE.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CONNECTION_THREAD_LOCAL.set(connection);
        }
        return connection;
    }

    public static  void closeConnection(){
        Connection connection = CONNECTION_THREAD_LOCAL.get();

        if(Objects.nonNull(connection)){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                CONNECTION_THREAD_LOCAL.remove();
            }
        }
    }

    public static DataSource getDataSource(){
        return BASIC_DATA_SOURCE;
    }

    /**
     * 开启事务
     */
    public static void beginTransaction(){
        Connection connection = getConnection();
        if(Objects.nonNull(connection)){
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }
    }

    /**
     * 提交事务
     */
    public static  void  commitTransaction(){
        Connection connection = getConnection();
        if(Objects.nonNull(connection)){
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                CONNECTION_THREAD_LOCAL.remove();
            }
        }
    }


    /**
     * 回滚事务
     */
    public static void rollbackTransaction(){
        Connection connection = getConnection();
        if(Objects.nonNull(connection)){

            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                CONNECTION_THREAD_LOCAL.remove();
            }
        }
    }


    /**
     * 查询实体类列表
     * @param clazz
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> clazz,String sql,Object ...params){
        try {
            List<T> list = QUERY_RUNNER.query(getConnection(), sql, new BeanListHandler<T>(clazz), params);
            return list;
        } catch (SQLException e) {
           throw  new RuntimeException("查询实体类列表发生异常");
        }
    }


    /**
     * 查询实体
     * @param clazz
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> clazz,String sql,Object ...params){
        try {
            T t  =  QUERY_RUNNER.query(getConnection(),sql,new BeanHandler<T>(clazz),params);
            return t;
        } catch (SQLException e) {
            throw  new RuntimeException("查询实体时发生异常");
        }
    }

    /**
     * 查询并返回单个列值
     */
    public static <T> T query(String sql, Object... params) {
        T obj;
        try {
            Connection conn = getConnection();
            obj = QUERY_RUNNER.query(conn, sql, new ScalarHandler<T>(), params);
        } catch (SQLException e) {

            throw new RuntimeException("查询时发生异常");
        }
        return obj;
    }

    /**
     * 查询并返回多个列值
     */
    public static <T> List<T> queryList(String sql, Object... params) {
        List<T> list;
        try {
            Connection conn = getConnection();
            list = QUERY_RUNNER.query(conn, sql, new ColumnListHandler<T>(), params);
        } catch (SQLException e) {

            throw new RuntimeException("查询时发生异常");
        }
        return list;
    }

    /**
     * 查询并返回多个列值（具有唯一性）
     */
    public static <T> Set<T> querySet(String sql, Object... params) {
        Collection<T> valueList = queryList(sql, params);
        return new LinkedHashSet<T>(valueList);
    }

    /**
     * 查询并返回数组
     */
    public static Object[] queryArray(String sql, Object... params) {
        Object[] resultArray;
        try {
            Connection conn = getConnection();
            resultArray = QUERY_RUNNER.query(conn, sql, new ArrayHandler(), params);
        } catch (SQLException e) {
            throw new RuntimeException("查询时发生异常");
        }
        return resultArray;
    }

    /**
     * 查询并返回数组列表
     */
    public static List<Object[]> queryArrayList(String sql, Object... params) {
        List<Object[]> resultArrayList;
        try {
            Connection conn = getConnection();
            resultArrayList = QUERY_RUNNER.query(conn, sql, new ArrayListHandler(), params);
        } catch (SQLException e) {
            throw new RuntimeException("查询时发生异常");
        }
        return resultArrayList;
    }


    /**
     * 查询并返回结果集映射（列名 => 列值）
     */
    public static Map<String, Object> queryMap(String sql, Object... params) {
        Map<String, Object> resultMap;
        try {
            Connection conn = getConnection();
            resultMap = QUERY_RUNNER.query(conn, sql, new MapHandler(), params);
        } catch (SQLException e) {
            throw new RuntimeException("查询时发生异常");
        }
        return resultMap;
    }


    /**
     * 查询并返回结果集映射列表（列名 => 列值）
     */
    public static List<Map<String, Object>> queryMapList(String sql, Object... params) {
        List<Map<String, Object>> resultMapList;
        try {
            Connection conn = getConnection();
            resultMapList = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
        } catch (SQLException e) {
            throw new RuntimeException("查询时发生异常");
        }
        return resultMapList;
    }


    /**
     * 执行查询语句
     * @param sql
     * @param params
     * @return
     */
    public static  List<Map<String,Object>> executeQuery(String sql,Object ...params){
        try {
            List<Map<String, Object>> queryList = QUERY_RUNNER.query(getConnection(), sql, new MapListHandler(), params);
            return queryList;
        } catch (SQLException e) {
            throw  new RuntimeException("发生异常");
        }
    }


    /**
     * 执行 更新、删除、插入 操作
     * @param sql
     * @param params
     * @return
     */
    public static  int executeUpdate(String sql,Object ...params){
        try {
            int update = QUERY_RUNNER.update(getConnection(), sql, params);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException("发生异常");
        }
    }

    /**
     * 插入
     * @param clazz
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean insertEntity(Class<T> clazz,Map<String,Object> fieldMap){
        if(MapUtils.isEmpty(fieldMap)){
            log.info("fieldMap 为空");
            return false;
        }

        String sql = "insert into "+ getTableName(clazz);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fieldMap.keySet()){
            columns.append(fieldName).append(" , ");
            values.append("? , ");
        }
        columns.replace(columns.lastIndexOf(", "),columns.length(),")");
        values.replace(values.lastIndexOf(", "),values.length(),")");
        sql += columns +" VALUES " + values;

        Object[] params = fieldMap.values().toArray();

        return executeUpdate(sql,params) == 1;
    }


    /**
     * 更新
     * @param clazz
     * @param id
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static  <T> boolean updateEntity(Class<T> clazz,long id,Map<String,Object> fieldMap){
        if(MapUtils.isEmpty(fieldMap)){
            log.info("fieldMap 为空");
            return false;
        }

        String sql = "update " + getTableName(clazz) +" set ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName : fieldMap.keySet()){
            columns.append(fieldName).append("=?, ");
        }

        sql += columns.substring(0,columns.lastIndexOf(", ")) + " where id = ?";
        List<Object> paramList =  Lists.newArrayList();
        paramList.addAll(fieldMap.values());
        paramList.add(id);

        Object[] params = paramList.toArray();

        return executeUpdate(sql,params) == 1;
    }


    /**
     * 删除
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    public static <T> boolean deleteEntity(Class<T> clazz,long id){
        String sql = "delete from " + getTableName(clazz) +" where id = ?";
        return executeUpdate(sql,id) == 1;
    }


    public static  void executeFileSql(String sqlFilePath){
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(sqlFilePath),
                            "UTF-8"));

            String sql = "";

            while ((sql=reader.readLine()) != null){
                DatabaseHelper.executeUpdate(sql);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static <T>  String getTableName(Class<T> clazz){
        return clazz.getSimpleName();
    }


}
