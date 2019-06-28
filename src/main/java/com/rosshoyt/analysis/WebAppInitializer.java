package com.rosshoyt.analysis;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
   @Override
   public void onStartup(ServletContext container) {
      AnnotationConfigWebApplicationContext context
            = new AnnotationConfigWebApplicationContext();
      context.setConfigLocation("com.rosshoyt.resources");

      container.addListener(new ContextLoaderListener(context));

      ServletRegistration.Dynamic dispatcher = container
            .addServlet("dispatcher", new DispatcherServlet(context));

      dispatcher.setLoadOnStartup(2);
      dispatcher.addMapping("/resources/**");
   }
}
