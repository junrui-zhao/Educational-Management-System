package com.crtvu.dao;

import com.crtvu.entity.Admin;
import com.crtvu.entity.AdminEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Phoenix on 2017/4/11.
 */
public interface AdminDAO {

    /**
     * 添加管理员实体
     * @param adminId
     * @param password
     */
    void insertAdmin(@Param("adminId") int adminId, @Param("password") String password);

    /**
     * 根据adminId删除管理员
     * @return
     */
    int deleteAdmin(@Param("adminId") int adminId);

    /**
     * 修改管理员密码
     * @param adminId
     * @param password
     * @return
     */
    int updateAdminPassword(@Param("adminId") int adminId, @Param("password") String password);

    /**
     * 通过adminId查找某一个管理员信息
     * @param adminId
     * @return 返回管理员的实体对象
     */
    AdminEntity selectAdmin(@Param("adminId") int adminId);

    /**
     * 查找所有管理员
     * @return
     */
    List<AdminEntity> selectAllAdmin();

    /**
     * 对片段进行模糊查询并且分页
     * @param adminProperty
     * @param index
     * @param count
     * @return
     */
    List<AdminEntity> selectAdminByLimit(@Param("adminProperty") String adminProperty, @Param("index") int index, @Param("count") int count);
    /**
     * 查询管理员数量
     * @return
     */
    int countAllAdmin(String adminProperty);

    /**
     * 通过管理员Id查询
     * @param adminId
     * @return
     */
    Admin queryAdminById(int adminId);
}
