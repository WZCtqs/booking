package com.zih.booking.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zih.booking.dao.ClientMenuMapper;
import com.zih.booking.model.ClientMenu;
import com.zih.booking.response.MenuResponse;
import com.zih.booking.response.Meta;
import com.zih.booking.response.TreeMenuResponse;
import com.zih.booking.service.ClientMenuService;
import com.zih.booking.system.token.LoginUser;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.utils.ServletUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shahy123
 * @since 2020-03-14
 */
@Service
public class ClientMenuServiceImpl extends ServiceImpl<ClientMenuMapper, ClientMenu> implements ClientMenuService {

    @Autowired
    private ClientMenuMapper clientMenuMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public List<TreeMenuResponse> selectAllMenu() {
        List<ClientMenu> clientMenus = clientMenuMapper.selectList(getEw());

        List<Integer> idList=new ArrayList<>();
        for(int i=0;i<clientMenus.size();i++){
            idList.add(clientMenus.get(i).getId());
        }
        if(idList.contains(105)&&!idList.contains(106)){
            ClientMenu clientMenu=new ClientMenu();
            clientMenu.setId(106);
            clientMenus.add(clientMenuMapper.selectOne(clientMenu));
        }
        return revert(clientMenus);
    }

    @Override
    public List<MenuResponse> selectTopMenu() {
        List<MenuResponse> menuResponses = new ArrayList<>();
        List<ClientMenu> clientMenus = clientMenuMapper.selectList(
                getEw().eq("parent_id",0));
        clientMenus.forEach(clientMenu -> {
            menuResponses.add(revert(clientMenu));
        });
        return menuResponses;
    }

    @Override
    public List<MenuResponse> selectChildMenu(Integer id) {
        List<MenuResponse> menuResponses = new ArrayList<>();
        List<ClientMenu> clientMenus = clientMenuMapper.selectList(
                getEw().eq("parent_id",id));
        clientMenus.forEach(clientMenu -> {
            menuResponses.add(revert(clientMenu));
        });
        return menuResponses;
    }


    /**
     * 树状分级
     * @param clientMenus
     * @return
     */
    private List<TreeMenuResponse> revert(List<ClientMenu> clientMenus){
        // 最终返回
        List<TreeMenuResponse> parentMenus = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(clientMenus)){
            clientMenus.forEach(clientMenu -> {
                if (clientMenu.getParentId() == 0){
                    TreeMenuResponse treeMenuResponse = new TreeMenuResponse();
                    Meta meta = new Meta();
                    treeMenuResponse.setId(clientMenu.getId());
                    treeMenuResponse.setPath(clientMenu.getPath());
                    treeMenuResponse.setComponent(clientMenu.getComponent());
                    treeMenuResponse.setName(clientMenu.getName());
                    meta.setTitle(clientMenu.getTitle());
                    meta.setIcon(clientMenu.getIcon());
                    treeMenuResponse.setMeta(meta);
                    if (clientMenu.getHidden().equals("2")){
                        treeMenuResponse.setHidden(Boolean.TRUE);
                    }else {
                        treeMenuResponse.setHidden(Boolean.FALSE);
                    }
                    parentMenus.add(treeMenuResponse);
                }
            });
            parentMenus.forEach(parentMenu -> {
                List<MenuResponse> children = new ArrayList<>();
                clientMenus.forEach(clientMenu -> {
                    if (clientMenu.getParentId().equals(parentMenu.getId())){
                        children.add(revert(clientMenu));
                    }
                });
                parentMenu.setChildren(children);
            });
        }
        return parentMenus;
    }

    /**
     * 对象转换
     * @param clientMenu
     * @return
     */
    private MenuResponse revert(ClientMenu clientMenu){
        Meta meta = new Meta();
        meta.setTitle(clientMenu.getTitle());
        meta.setIcon(clientMenu.getIcon());
        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setId(clientMenu.getId());
        menuResponse.setPath(clientMenu.getPath());
        menuResponse.setComponent(clientMenu.getComponent());
        menuResponse.setName(clientMenu.getName());
        menuResponse.setMeta(meta);
        if (clientMenu.getHidden().equals("2")){
            menuResponse.setHidden(Boolean.TRUE);
        }else {
            menuResponse.setHidden(Boolean.FALSE);
        }
        return menuResponse;
    }

    /**
     * 获取条件构造器
     * @return
     */
    private EntityWrapper getEw(){
        LoginUser user = tokenService.getLoginUser(ServletUtils.getRequest());
        EntityWrapper<ClientMenu> ew = new EntityWrapper<>();
        if(user.getUserType().equals("1") && CollectionUtils.isNotEmpty(user.getPermissions())){
            ew.in("id",user.getPermissions());
        }
        ew.orderBy("order_num");
        return ew;
    }
}
