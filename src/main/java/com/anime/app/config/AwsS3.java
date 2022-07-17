package com.anime.app.config;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.B2StorageClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class AwsS3 {
  @Value("${s3.access.key}")
  private String accessKey;
  @Value("${s3.secret.key}")
  private String secretKey;
  @Value("${s3.endpoint}")
  private String host;

  @Bean
  public S3Client s3Client() {
    Region region = Region.US_WEST_2;
    AwsSessionCredentials credentials = AwsSessionCredentials.create(accessKey, secretKey, "");

    return S3Client.builder().credentialsProvider(
                    StaticCredentialsProvider.create(credentials)
            )
            .endpointOverride(URI.create(host))
            .region(region)
            .build();
  }

  @Bean
  public B2StorageClient b2StorageClient() {
    return B2StorageClientFactory
            .createDefaultFactory()
            .create(accessKey, secretKey, "Java");
  }
}
