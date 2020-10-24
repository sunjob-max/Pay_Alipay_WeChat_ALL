package com.entity;

import lombok.Data;


/**
 * redis
 *
 */
@Data
public class Redis
{

	private String key;//

	private Object value;//

	private Long expireTime;//缓存时间

}