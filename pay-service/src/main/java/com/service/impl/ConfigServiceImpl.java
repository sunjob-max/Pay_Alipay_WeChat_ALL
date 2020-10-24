package com.service.impl;

import com.constant.CacheConst;
import com.entity.Config;
import com.mapper.ConfigMapper;
import com.redis.RedisService;
import com.service.IConfigService;
import com.utils.ConfigDataUtils;
import com.utils.IdWorkUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统配置类实现类
 */
@Service
public class ConfigServiceImpl implements IConfigService
{
    @Autowired
    private ConfigMapper configDao;

    @Autowired
    private RedisService redisService;

    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    @Override
    public Config selectConfigById(Long configId)
    {
        Config config = new Config();
        config.setConfigId(configId);
        return configDao.selectConfig(config);
    }

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数名称
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey)
    {
        Config config = new Config();
        config.setConfigKey(configKey);
        Config retConfig = configDao.selectConfig(config);
        return null != retConfig? retConfig.getConfigValue() : "";
    }

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    @Override
    public List<Config> selectConfigList(Config config)
    {
        return configDao.selectConfigList(config);
    }

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int insertConfig(Config config)
    {
        redisService.removePattern(CacheConst.CONFIG + "*");
        config.setConfigId(new Long(IdWorkUtils.nextId()));
        return configDao.insertConfig(config);
    }

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int updateConfig(Config config)
    {
        redisService.removePattern(CacheConst.CONFIG + "*");
        return configDao.updateConfig(config);
    }

    /**
     * 批量删除参数配置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigByIds(String ids)
    {
        redisService.removePattern(CacheConst.CONFIG + "*");
        return configDao.deleteConfigByIds(StringUtils.split(ids, ","));
    }

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public String checkConfigKeyUnique(Config config)
    {
        Long configId = (null == config.getConfigId()) ? -1L : config.getConfigId();
        Config info = configDao.checkConfigKeyUnique(config.getConfigKey());
        if (null != info && info.getConfigId().longValue() != configId.longValue())
        {
            return "1";
        }
        return "0";
    }

    @Override
    public String selectConfigByKeyApi(String configKey) {
        String result = (String) redisService.get(CacheConst.CONFIG + configKey);
        if (StringUtils.isBlank(result)){
            Config config = new Config();
            config.setConfigKey(configKey);
            Config retConfig = configDao.selectConfig(config);
            if (null != retConfig){
                result = retConfig.getConfigValue();
                redisService.set(CacheConst.CONFIG + configKey, result);
            }
        }
        return result;
    }

/*    @Override
    public void clearCache()
    {
        CacheUtils.removeAll(getCacheName());
    }*/

    @Override
    public void initConfig(){
        List<Config> configs = configDao.selectConfigList(new Config());
        Map<String, String> configData = new HashMap<>(16);
        for (Config config : configs) {
            configData.put(config.getConfigKey(), config.getConfigValue());
        }
        //初始化系统参数
        ConfigDataUtils.configData = configData;
    }
}
