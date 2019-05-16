package com.zhengqing.demo.mapper;

import com.zhengqing.demo.domain.Menu;
import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> treeMenu(Long id);

}