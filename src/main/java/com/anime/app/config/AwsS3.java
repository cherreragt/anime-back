package com.anime.app.config;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.B2StorageClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3 {
  @Value("${s3.access.key}")
  private String accessKey;
  @Value("${s3.secret.key}")
  private String secretKey;

  @Bean
  public B2StorageClient b2StorageClient() {
    return B2StorageClientFactory
            .createDefaultFactory()
            .create(accessKey, secretKey, "Java");
  }
}
