package com.huanglf.test16.repository;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

/**
 * Date: 2019/7/4
 * Author: JinYue
 * description:
 */
public interface IUserRepository {
    /**
     * 用户通过账号密码登录
     *
     * @param account
     * @param password
     * @return
     */
    BmobUser loginWithPassword(String account, String password) throws Exception;


    /**
     * 用户通过微信登录
     */
    BmobUser loginWithWeChat() throws BmobException;
}
