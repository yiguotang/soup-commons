package com.soup.common.entity;

import lombok.Getter;

/**
 * 错误码
 *
 * @author zhaoyi
 */
@Getter
public enum ErrorCode {

    /**
     * 系统错误码
     */
    SUCCESS(0, "success"),
    FAILURE(400, "fail"),
    AUTH_FAIL(403, "Authentication failed！"),
    NOT_FOUND(404, "not found！"),
    METHOD_NOT_ALLOWED(405, "Method not allowed！"),
    SERVER_FAIL(500, "server fail！"),
    NOT_SUPPORT_FILE_EXTENSION(10000, "不支持的文件格式，请重新选择！"),
    FILE_TO_LARGE(10001, "文件过大！"),
    PARAM_ERROR(10002, "请求参数错误！"),
    AUTH_TOKEN_NOT_EXISTED(10003, "登录失效，请重新登录！"),
    AUTH_TOKEN_IS_EXPIRED(10004, "登录已过期，请重新登录！"),
    NO_AUTH_ACCESS(10005, "没有权限访问该菜单或功能，请联系管理员！"),
    ADMIN_DELETED(10006, "您已经被管理员移除，请联系管理员！"),
    EXCLE_DATA_ERROR(10007, "Excle数据错误，请重新选择文件！"),

    /**
     * sys
     */
    USER_NOT_FOUND(20000, "用户不存在！"),
    USER_PWD_ERROR(20001, "密码错误！"),
    USER_FORBIDDEN(20002, "用户已被禁用，请联系管理员！"),
    USER_LOGINNAME_EXSITED(20003, "登录名已存在，请求重新输入！"),
    USER_KAPTCHA_ERROR(20004, "验证码错误！"),
    USER_PASSWORD_ERROR(20005, "当前密码错误！"),
    USER_PASSWORD_INCONFORMITY(20006, "新密码和重复密码不一致，请重新输入！"),
    USER_DEL_NOT_SUPPORT(20007, "默认用户无法删除！"),

    DICT_PARENT_TYPE_PARAM_ERROR(30000, "新增字典失败，父级字典的分类不能为空！"),
    DICT_PARENT_TYPE_NAME_PARAM_ERROR(30001, "新增字典失败，父级字典的分类名称不能为空！"),
    DICT_PARENT_TYPE_EXISTED(30002, "新增字典失败，父级字典的分类类型已存在！"),
    DICT_ADD_ERROR(30003, "新增字典失败，父级字典不存在或已被删除！"),
    DICT_ADD_DUPLICATED(30004, "字典名称已存在！"),
    DICT_NOT_FOUND(30005, "字典不存在或已被删除！"),

    ROLE_ADD_DUPLICATED(40000, "新增角色失败，角色已存在！"),
    ROLE_UPDATE_ERROR(40001, "修改角色失败，角色名称已存在！"),
    ROLE_NOT_FOUND(40002, "角色不存在或已被删除！"),
    ROLE_DEL_ERROR(40003, "角色删除失败，角色下还有用户使用，无法删除！"),
    ROLE_DEL_NOT_SUPPORT(40004, "默认角色无法删除！"),
    ROLE_PRIVILEGE_NOT_SUPPORT(40005, "默认角色无法修改权限菜单！"),

    MENU_ADD_DUPLICATED(50000, "操作失败，菜单名称已存在！"),
    MENU_NOT_FOUND(50001, "操作失败，菜单不存在或已被删除！"),
    MENU_PARENT_NOT_FOUND(50002, "操作失败，父级菜单不存在或已被删除！"),
    MENU_PARENT_NOT_CORRECT(50003, "操作失败，方法菜单上级必须是分组菜单或控制器菜单！"),
    MENU_TYPE_NOT_CORRECT(50004, "操作失败，顶级菜单不可以方法菜单或控制器菜单！"),
    MENU_HAS_CHILD(50005, "操作失败，菜单下还有子级菜单！"),

    /**
     * platform
     */
    CLIENT_ADD_DUPLICATED(50000, "新增客户失败，已存在客户无法添加！"),
    CLIENT_NOT_FOUND(50001, "客户不存在或已被删除！"),
    CLIENT_UPDATE_ERROR(50002, "修改用户失败，客户名称已存在！"),

    EMPLOYEE_IDCARD_DUPLICATED(60000, "员工身份证号已存在，请重新修改！"),
    EMPLOYEE_NOT_FOUND(60001, "员工信息未找到或已被删除！"),

    STORAGE_ADD_DUPLICATED(70000, "操作失败，库存已存在！"),
    STORAGE_NOT_FOUND(70001, "操作失败，库存不存在或已被删除！"),
    STORAGE_HAS_RECORDS(70002, "操作失败，该库存还有入库记录！"),
    STORAGE_AVAILABLEQTY(70003, "操作失败，该库存可用数量大于0！"),
    STORAGE_ADD_ERROR(70004, "操作失败，入库记录与选定的库存不符！"),
    STORAGE_EXISTED(70005, "操作失败，入库记录已有库存！"),

    ORDER_NOT_FOUND(80001, "订单信息未找到！"),
    ORDER_OPER_ERRRO(80002, "该订单状态不允许此操作！"),
    ORDER_UPDATE_DESIGN_ERRRO(80003, "上传文件不能为空！"),
    ORDER_MATERIAL_NO_ENOUGH(80004, "材料已被删除或库存不足，请补充库存！"),
    ORDER_DESIGN_FILE_UPLOAD_ERROR(80005, "上传设计文件失败！"),
    ORDER_TASK_ADD_DUPLICATED(80006, "订单任务不可以重复添加！"),
    ORDER_NO_DUPLICATED(80007, "订单编号已存在，不可以重复添加！"),
    ORDER_MATERIAL_ADD_DUPLICATED(80008, "该备料已经存在，不可以重复添加，请删除后再添加！"),
    ORDER_EXPORT_NOT_EMPTY(80009, "请至少选择一条订单进行导出！"),
    ORDER_EXPORT_ERROR(80010, "订单导出错误！"),

    ;

    private int code;

    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
