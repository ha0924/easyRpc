package com.he.herpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 配置工具类，用于加载配置对象，支持根据不同环境加载不同的配置文件。
 */
public class ConfigUtils {

    /**
     * 加载配置对象的简化方法，默认不区分环境。
     *
     * @param tClass 配置对象的类类型，用于指定要加载的配置对象的类型。
     * @param prefix 配置项的前缀，用于筛选配置文件中以该前缀开头的配置项。
     * @param <T>    配置对象的泛型类型。
     * @return 返回加载好的配置对象实例。
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        // 调用带环境参数的加载配置对象方法，默认环境为空字符串
        return loadConfig(tClass, prefix, "");
    }

    /**
     * 加载配置对象的完整方法，支持区分环境。
     *
     * @param tClass      配置对象的类类型，用于指定要加载的配置对象的类型。
     * @param prefix      配置项的前缀，用于筛选配置文件中以该前缀开头的配置项。
     * @param environment 环境标识，用于指定加载哪个环境的配置文件，如 "dev"、"prod" 等。
     * @param <T>         配置对象的泛型类型。
     * @return 返回加载好的配置对象实例。
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        // 创建一个 StringBuilder 对象，用于构建配置文件的名称
        StringBuilder configFileBuilder = new StringBuilder("application");

        // 如果环境标识不为空，则在配置文件名中添加环境标识
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }

        // 在配置文件名后添加 .properties 后缀
        configFileBuilder.append(".properties");

        // 根据构建好的配置文件名创建 Props 对象，用于加载配置文件
        Props props = new Props(configFileBuilder.toString());

        // 将配置文件中的配置项转换为指定类型的配置对象实例，并返回
        return props.toBean(tClass, prefix);
    }
}