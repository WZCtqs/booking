package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author shahy123
 * @since 2020-03-14
 */
@TableName("client_menu")
public class ClientMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 父级菜单id
     */
    @TableField("parent_id")
    private Integer parentId;
    private String path;
    private String component;
    private String name;
    private String title;
    private String icon;
    @TableField("order_num")
    private Integer orderNum;
    private String redirect;
    private String hidden;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return "ClientMenu{" +
        "id=" + id +
        ", parentId=" + parentId +
        ", path=" + path +
        ", component=" + component +
        ", name=" + name +
        ", title=" + title +
        ", icon=" + icon +
        ", orderNum=" + orderNum +
        ", redirect=" + redirect +
        ", hidden=" + hidden +
        "}";
    }
}
