package com.constant;

/**
 * 通用常量信息
 * 
 * @author ruoyi
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";
    
    /**
     * 登录超时失效的异常
     */
    public static final String FAILTIME = "2";
    
    /**
     * 警告
     */
    public static final String WARN = "301";

    /**
     * 错误
     */
    public static final String ERROR = "500";

    /**
     * 登录状态码
     */
    /** 成功 **/
    public final static String LOGIN_MSG_CODE = "0000";
    /** 登陆失败，需要重新登陆 **/
    public final static String LOGIN_MSG_CODE1 = "0001";
    /** 登出失败 **/
    public final static String LOGIN_MSG_CODE2 = "0002";
    /** 操作异常 **/
    public final static String LOGIN_MSG_CODE3 = "0003";
    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 自动去除表前缀
     */
    public static final String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     *	 状态码 MSG
     */
    /** 操作异常 **/
    public final static String MSG_0000 = "成功";
    /** 操作异常 **/
    public final static String MSG_0003 = "操作异常";


    /**用户模块状态MSG**/
    public final static String USER_MSG001="不允许修改超级管理员用户";
    public final static String USER_MSG002="该用户手机号码已存在";
    public final static String USER_MSG003="该用户邮箱账号已存在";
    public final static String USER_MSG004="登录账号已存在";
    public final static String USER_MSG005="手机号码已存在";
    public final static String USER_MSG006="邮箱账号已存在";
    public final static String USER_MSG007="密码不能为空";
    public final static String USER_MSG008="密码长度过长";
    
     
    /**CAS登录状态MSG**/
    public final static String CASLOGIN_MSG001="需要登录";
    public final static String CASLOGIN_MSG002="账号或密码错误";
    public final static String CASLOGIN_MSG003="您的账号暂无权限，请您去统一管理平台授权。";
    public final static String CASLOGIN_MSG004="您的账号需要登录后再进行相关操作";
    
    
    /**部门模块状态MSG**/
    public final static String DEPT_MSG001="部门名称已存在";
    public final static String DEPT_MSG002="上级部门不能是自己";
    public final static String DEPT_MSG003="存在下级部门,不允许删除";
    public final static String DEPT_MSG004="部门存在用户,不允许删除";
    public final static String DEPT_MSG005="未查询到数据";

    public final static Integer STATUS_0 = 0;
    public final static Integer STATUS_1 = 1;

    public final static String DICT_DATA_VALUE = "system_menu";

    /** 删除标识（0未删除，1已删除） */
    public final static Long DELETEFALG_0 = 0L;
    public final static Long DELETEFALG_1 = 1L;

    /** 设备日志类型(1、设备报警，2、设备状态；3、实时报文、4、远程控制) */
    public final static Long LOG_DEVICE_LOG_TYPE_1 = 1L;
    public final static Long LOG_DEVICE_LOG_TYPE_2 = 2L;
    public final static Long LOG_DEVICE_LOG_TYPE_3 = 3L;
    public final static Long LOG_DEVICE_LOG_TYPE_4 = 4L;
    
    /**自定义表单模块**/
    public final static String FORM_VIEW_MSG001="自定义表单名称已存在";
    public final static String FORM_VIEW_MSG002="自定义表单标识键已存在";
    public final static String FORM_VIEW_MSG003="未查询到数据";

    /** 仅支持字母、数字、下划线、空格、逗号（支持多个字段排序）**/
    public final static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,]+";

    /**  日期类型：1、今日， 2、近一周，3、近30天 **/
    public final static int DATE_TYPE_1 = 1;
    public final static int DATE_TYPE_7 = 7;
    public final static int DATE_TYPE_30 = 30;

    /**  缓存 **/
    public final static String RABBIT_QUEUE = "rabbitQueue:";

    /** 百分比常量配置 **/
    public final static String PERCENT_2 = "#.##%";

}
