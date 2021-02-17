package com.zih.booking.service;

import com.baomidou.mybatisplus.service.IService;
import com.zih.booking.model.ClientMenu;
import com.zih.booking.response.MenuResponse;
import com.zih.booking.response.TreeMenuResponse;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shahy123
 * @since 2020-03-14
 */
public interface ClientMenuService extends IService<ClientMenu> {

    List<TreeMenuResponse> selectAllMenu();

    List<MenuResponse> selectTopMenu();

    List<MenuResponse> selectChildMenu(Integer id);
}
