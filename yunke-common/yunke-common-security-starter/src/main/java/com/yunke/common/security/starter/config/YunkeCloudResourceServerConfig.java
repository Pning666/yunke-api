package com.yunke.common.security.starter.config;

import cn.hutool.core.util.StrUtil;
import com.yunke.common.security.starter.handler.YunkeAccessDeniedHandler;
import com.yunke.common.security.starter.handler.YunkeAuthExceptionEntryPoint;
import com.yunke.common.security.starter.properties.YunkeCloudSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author chachae
 */
@EnableResourceServer
@EnableAutoConfiguration(exclude = UserDetailsServiceAutoConfiguration.class)
public class YunkeCloudResourceServerConfig extends ResourceServerConfigurerAdapter {

  private YunkeCloudSecurityProperties properties;
  private YunkeAccessDeniedHandler accessDeniedHandler;
  private YunkeAuthExceptionEntryPoint exceptionEntryPoint;

  @Autowired
  public void setProperties(YunkeCloudSecurityProperties properties) {
    this.properties = properties;
  }

  @Autowired
  public void setAccessDeniedHandler(YunkeAccessDeniedHandler accessDeniedHandler) {
    this.accessDeniedHandler = accessDeniedHandler;
  }

  @Autowired
  public void setExceptionEntryPoint(YunkeAuthExceptionEntryPoint exceptionEntryPoint) {
    this.exceptionEntryPoint = exceptionEntryPoint;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    String[] anonUrls = StrUtil.splitToArray(properties.getAnonUris(), ',');
    if (anonUrls.length == 0) {
      anonUrls = new String[]{};
    }

    http.csrf().disable()
        .requestMatchers().antMatchers(properties.getAuthUri())
        .and()
        .authorizeRequests()
        .antMatchers(anonUrls).permitAll()
        .antMatchers(properties.getAuthUri()).authenticated()
        .and()
        .httpBasic();
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.authenticationEntryPoint(exceptionEntryPoint)
        .accessDeniedHandler(accessDeniedHandler);
  }

}
