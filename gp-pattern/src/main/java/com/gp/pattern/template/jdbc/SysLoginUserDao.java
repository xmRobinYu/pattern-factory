package com.gp.pattern.template.jdbc;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public class SysLoginUserDao extends JdbcTemplate {

    public SysLoginUserDao(DataSource dataSource) {
        super(dataSource);
    }


    public List<?> selectAll(){
        String sql = "select * from sys_login_user";
        return super.execQry(sql, new RowMapper<SysLoginUser>() {
            @Override
            public SysLoginUser mapRow(ResultSet rs, int rowNum) throws Exception {
                SysLoginUser member = new SysLoginUser();
                member.setSluId(rs.getString("SLU_ID"));
                member.setSluName(rs.getString("SLU_NAME"));
                return member;
            }
        },null);
    }

}
