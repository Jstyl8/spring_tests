package com.clanbasic.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// this replaces the web.xml

public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @java.lang.Override
    protected java.lang.Class<?>[] getRootConfigClasses() {
        return new java.lang.Class[0];
    }

    @java.lang.Override
    protected java.lang.Class<?>[] getServletConfigClasses() {
        return new java.lang.Class[] { WebConfig.class };
    }

    @java.lang.Override
    protected java.lang.String[] getServletMappings() {
        return new java.lang.String[] {"/"};
    }
}
