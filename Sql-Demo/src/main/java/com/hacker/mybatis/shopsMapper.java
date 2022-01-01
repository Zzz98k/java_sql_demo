package com.hacker.mybatis;

import org.apache.ibatis.annotations.Param;
public interface shopsMapper {

    shops findById(String id);
    shops findById2(@Param(value = "id") String id);
}