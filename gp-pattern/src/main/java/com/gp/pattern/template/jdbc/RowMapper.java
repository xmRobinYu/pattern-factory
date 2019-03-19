package com.gp.pattern.template.jdbc;

import java.sql.ResultSet;

/**
 * ORM映射定制化的接口
 * Created by Tom on 2019/3/16.
 */
public interface RowMapper<T> {

    public T mapRow(ResultSet rs, int rowNum) throws Exception;
}
