package com.zhengqing.demo.service.impl;

import com.zhengqing.demo.mapper.BaseMapper;
import com.zhengqing.demo.service.impl.BaseServiceImpl;
import com.zhengqing.demo.domain.${Domain};
import com.zhengqing.demo.mapper.${Domain}Mapper;
import com.zhengqing.demo.service.I${Domain}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ${Domain}ServiceImpl extends BaseServiceImpl<${Domain}> implements I${Domain}Service {

    @Autowired
    private ${Domain}Mapper ${domain}Mapper;


    @Override
    protected BaseMapper<${Domain}> getMapper() {
        return ${domain}Mapper;
    }

}
