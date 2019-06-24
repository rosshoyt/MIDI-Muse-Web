package com.rosshoyt.analysis.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@ConfigurationProperties("storage")
public class StorageProperties {

   /**
    * Folder location for storing files
    */
   private String location = "upload-dir";

   public String getLocation() {
      return location;
   }

   public void setLocation(String location) {
      this.location = location;
   }

}