package com.huanglf.test16.repository;

import com.huanglf.test16.common.MessageEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Date: 2019/7/3
 * Author: huanglf
 * description: viewModel 详细返回体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int resultCode;
    private String desc;
}
