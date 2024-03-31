package com.ms.usermanagement.config.error_handling.tomcat;

import org.apache.catalina.Container;
import org.apache.catalina.core.StandardHost;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomTomcatErrorConfig {
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> errorReportValveCustomizer() {
        String reportValvePackageName = CustomTomcatErrorReportValve.class.getPackageName();
        String reportValveSimpleName = CustomTomcatErrorReportValve.class.getSimpleName();
		String reportValvePackage = reportValvePackageName + "." + reportValveSimpleName;

        return factory -> 
            factory.addContextCustomizers(context -> {
                final Container parent = context.getParent();
                if (parent instanceof StandardHost)  {
                    ((StandardHost) parent).setErrorReportValveClass(reportValvePackage);
                }
            });
    }
}
