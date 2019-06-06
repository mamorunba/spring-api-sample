package com.example.springapisample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * 任意の設定をもったクラスをDIコンテナに登録するためのクラス。
 */
@Configuration
public class AppConfiguration {

    /**
     * アクセスログを出力するための設定を持ったCommonsRequestLoggingFilterをDIコンテナに登録する
     * @return
     */
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
      CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
      filter.setIncludeClientInfo(true);
      filter.setIncludeQueryString(true);
      filter.setIncludeHeaders(true);
      filter.setIncludePayload(true);
      filter.setMaxPayloadLength(1024);
      return filter;
    }
}
