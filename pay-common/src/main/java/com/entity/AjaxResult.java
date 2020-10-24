package com.entity;


import com.constant.Constants;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 操作消息提醒
 *
 * @author wlluo
 */
public class AjaxResult extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult() {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg  返回内容
     * @param data 数据对象
     */
    public AjaxResult(Type type, String msg, Object data) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (null!=data&&!"".equals(data.toString())) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg  返回内容
     */
    public AjaxResult(Type type, String msg) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * 返回错误消息
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult errorApi(String msg)
    {
        AjaxResult json = new AjaxResult();
        json.put("code", Constants.FAIL);
        json.put("msg", msg);
        return json;
    }


    /**
     * 状态类型
     */
    public enum Type {
        /**
         * 成功
         */
        SUCCESS(Constants.SUCCESS),
        /**
         * 失败
         */
        FAIL(Constants.FAIL),
        /**
         * 警告
         */
        WARN(Constants.WARN),
        /**
         * 错误
         */
        ERROR(Constants.ERROR);
        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult warn(String msg) {
        return AjaxResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult warn(String msg, Object data) {
        return new AjaxResult(Type.WARN, msg, data);
    }


    /**
     * 返回成功消息 LIST
     *
     * @param a
     * @return
     */
    public static AjaxResult successList(List<?> a) {
        AjaxResult json = new AjaxResult();
        json.put("msg", "操作成功");
        json.put("code", Constants.SUCCESS);
        json.put("data", a);
        return json;
    }


    /**
     * 返回成功消息
     *
     * @param obj 内容
     * @return 成功消息
     */
    public static AjaxResult success(Object obj) {
        AjaxResult json = new AjaxResult();
        json.put("data", obj);
        json.put("code", Constants.SUCCESS);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success() {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg) {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", Constants.SUCCESS);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object obj) {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", Constants.SUCCESS);
        json.put("data", obj);
        return json;
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static AjaxResult error() {
        return error(Constants.FAIL, "操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult error(String msg) {
        return error(Constants.FAIL, msg);
    }


    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult errorLogin(String msg) {
        return error(Constants.FAILTIME, msg);
    }


    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult error(String code, String msg) {
        AjaxResult json = new AjaxResult();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @param key   键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static AjaxResult successPage(String pages,String totals, Object obj) {
        AjaxResult json = new AjaxResult();
        json.put("pages", pages);
        json.put("totals", totals);
        json.put("code", Constants.SUCCESS);
        json.put("data", obj);
        return json;
    }
}
