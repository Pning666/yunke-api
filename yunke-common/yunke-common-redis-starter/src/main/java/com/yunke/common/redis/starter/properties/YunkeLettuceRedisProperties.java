package com.yunke.common.redis.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Lettuce Redis Properties
 *
 * @author chachae
 * @since 2020/05/11 22:31
 */
@ConfigurationProperties(prefix = "yunke.lettuce.redis")
public class YunkeLettuceRedisProperties {

  /**
   * 是否开启Lettuce Redis
   */
  private Boolean enable = true;

  public Boolean getEnable() {
    return enable;
  }

  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  @Override
  public String toString() {
    return "YunkeLettuceRedisProperties{" +
        "enable=" + enable +
        '}';
  }
}
