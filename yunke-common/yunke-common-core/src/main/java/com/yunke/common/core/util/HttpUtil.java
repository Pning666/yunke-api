package com.yunke.common.core.util;

import static com.alibaba.fastjson.JSON.toJSONBytes;

import cn.hutool.core.net.NetUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import com.google.common.net.HttpHeaders;
import com.yunke.common.core.entity.UserAgent;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * HuTool HttpUtil 拓展
 *
 * @author chachae
 * @since 2020/4/24 18:21
 */
public class HttpUtil extends cn.hutool.http.HttpUtil {

  private HttpUtil() {
  }

  private static final String[] LOCALHOST = {"unknown", "127.0.0.1", "0:0:0:0:0:0:0:1"};

  /**
   * 设置响应
   *
   * @param response    HttpServletResponse
   * @param contentType content-type
   * @param status      http状态码
   * @param value       响应内容
   * @throws IOException IOException
   */
  public static void makeResponse(HttpServletResponse response, String contentType,
      int status, Object value) throws IOException {
    response.setContentType(contentType);
    response.setStatus(status);
    response.getOutputStream().write(toJSONBytes(value));
  }

  /**
   * 设置JSON类型响应
   *
   * @param response HttpServletResponse
   * @param status   http状态码
   * @param value    响应内容
   * @throws IOException IOException
   */
  public static void makeJsonResponse(HttpServletResponse response, int status, Object value)
      throws IOException {
    makeResponse(response, MediaType.APPLICATION_JSON_VALUE, status, value);
  }

  /**
   * 设置成功响应
   *
   * @param response HttpServletResponse
   * @param value    响应内容
   * @throws IOException IOException
   */
  public static void makeSuccessResponse(HttpServletResponse response, Object value)
      throws IOException {
    makeResponse(response, MediaType.APPLICATION_JSON_VALUE, HttpServletResponse.SC_OK, value);
  }

  /**
   * 设置失败响应
   *
   * @param response HttpServletResponse
   * @param value    响应内容
   * @throws IOException IOException
   */
  public static void makeFailureResponse(HttpServletResponse response, Object value)
      throws IOException {
    makeResponse(response, MediaType.APPLICATION_JSON_VALUE,
        HttpServletResponse.SC_INTERNAL_SERVER_ERROR, value);
  }

  /**
   * 获取HttpServletRequest
   *
   * @return HttpServletRequest
   */
  public static HttpServletRequest getHttpServletRequest() {
    return ((ServletRequestAttributes) Objects
        .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
  }

  /**
   * 获取请求头值
   *
   * @return String
   */
  public static String getHeader(String key) {
    return getHttpServletRequest().getHeader(key);
  }

  /**
   * 获取请求数据
   *
   * @return String
   */
  public static String getParam(String key) {
    return getHttpServletRequest().getParameter(key);
  }

  /**
   * 获取请求地址
   *
   * @return String
   */
  public static String getRequestUrl() {
    return getHttpServletRequest().getRequestURI();
  }

  /**
   * 获取请求IP
   *
   * @return String IP
   */
  public static String getIpAddress() {
    HttpServletRequest request = getHttpServletRequest();
    String ip = request.getHeader(HttpHeaders.X_FORWARDED_FOR);
    if (ip == null || ip.length() == 0 || LOCALHOST[0].equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || LOCALHOST[0].equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || LOCALHOST[0].equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    if (LOCALHOST[0].equalsIgnoreCase(ip) || LOCALHOST[1].equalsIgnoreCase(ip) || LOCALHOST[2]
        .equalsIgnoreCase(ip)) {
      ip = NetUtil.getLocalhostStr();
    }
    return ip;
  }

  /**
   * 获取当前用户浏览器 UserAgent 信息
   *
   * @return 用户标识对象
   */
  public static UserAgent getUserAgent() {
    String userAgent = getHeader(HttpHeaders.USER_AGENT);
    cn.hutool.http.useragent.UserAgent parse = UserAgentUtil.parse(userAgent);
    return new UserAgent(parse.getBrowser().getName(), parse.getOs().getName());
  }
}
