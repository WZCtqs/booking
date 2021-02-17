package com.zih.booking.controller;


import com.zih.booking.response.MenuResponse;
import com.zih.booking.response.TreeMenuResponse;
import com.zih.booking.service.ClientMenuService;
import com.zih.booking.system.vo.ApiResultI18n;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shahy123
 * @since 2020-03-14
 */
@Api(tags = "客户端菜单接口")
@RestController
public class ClientMenuController {

    @Autowired
    private ClientMenuService clientMenuService;

    @ApiOperation(value = "获取全部菜单")
    @GetMapping("/allMenu/list")
    public ApiResultI18n allMenu(String language){
        List<TreeMenuResponse> list = clientMenuService.selectAllMenu();
        return ApiResultI18n.success(list,language);
    }

    @ApiOperation(value = "获取顶级菜单")
    @GetMapping("/topMenu/list")
    public ApiResultI18n topMenu(String language){
        List<MenuResponse> list = clientMenuService.selectTopMenu();
        return ApiResultI18n.success(list,language);
    }

    @ApiOperation(value = "获取子菜单")
    @GetMapping("/childMenu/list")
    public ApiResultI18n childMenu(Integer id,String language){
        List<MenuResponse> list = clientMenuService.selectChildMenu(id);
        return ApiResultI18n.success(list,language);
    }
}

