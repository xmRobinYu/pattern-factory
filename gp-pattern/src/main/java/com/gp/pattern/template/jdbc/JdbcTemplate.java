package com.gp.pattern.template.jdbc;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public List<?> execQry(String sql, RowMapper<?> rowMapper, Object[] objects) {
        List result = null;
        try {
            //1、获取连接
            Connection connection = this.getConntection();
            //2、创建语句集
            PreparedStatement pstm = this.createPreparedStatement(connection, sql);
            //3、执行语句集
            ResultSet rs = this.executeQuery(pstm, objects);
            //4、处理结果集
            result = this.paresResultSet(rs, rowMapper);
            //5、关闭结果集
            this.closeResultSet(rs);
            //6、关闭语句集
            this.closePreparedStatement(pstm);
            //7、关闭连接
            this.closeConntion(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void closeConntion(Connection connection) throws SQLException {
        connection.close();
    }

    private void closePreparedStatement(PreparedStatement pstm) throws SQLException {
        pstm.close();
    }

    private void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }

    private List paresResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<>();
        int rowNum = 1;
        while ((rs.next())) {
            result.add(rowMapper.mapRow(rs, rowNum++));
        }
        return result;
    }

    private ResultSet executeQuery(PreparedStatement pstm, Object[] objects) throws SQLException {
        if (objects != null) {
            for (int i = 0; i < objects.length; ++i) {
                pstm.setObject(i, objects[i]);
            }
        }
        return pstm.executeQuery();
    }


    private PreparedStatement createPreparedStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    //1、获取连接
    private Connection getConntection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
