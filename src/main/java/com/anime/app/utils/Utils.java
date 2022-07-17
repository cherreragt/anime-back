package com.anime.app.utils;

import com.anime.app.exceptions.BadRequest;
import com.anime.app.exceptions.ErrorInternal;
import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.contentSources.B2ContentSource;
import com.backblaze.b2.client.contentSources.B2FileContentSource;
import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.client.structures.*;
import com.backblaze.b2.util.B2ExecutorUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class Utils {
  @Value("${s3.bucket}")
  private String bucketName;

  private final B2StorageClient b2StorageClient;

  private ExecutorService executor;

  public Utils(B2StorageClient b2StorageClient) {
    this.b2StorageClient = b2StorageClient;
  }

  public String uploadFile(MultipartFile multipartFile) {
    if (multipartFile.isEmpty() || Objects.requireNonNull(multipartFile.getOriginalFilename()).isEmpty()) {
      throw new BadRequest("No hay ningun archivo");
    }

    try {
      B2Bucket bucket = b2StorageClient.getBucketOrNullByName(bucketName);

      File file = convert(multipartFile);
      B2ContentSource source = B2FileContentSource
              .builder(file)
              .build();

      var request = B2UploadFileRequest
              .builder(bucket.getBucketId(),
                      file.getName(),
                      multipartFile.getContentType(), // B2ContentTypes.B2_AUTO
                      source)
              .setCustomFields(new TreeMap<>())
              .build();

      final long contentLength = request.getContentSource().getContentLength();
      if (b2StorageClient.getFilePolicy().shouldBeLargeFile(contentLength)) {
        b2StorageClient.uploadLargeFile(request, getExecutor());
      } else {
        b2StorageClient.uploadSmallFile(request);
      }
      Path path = file.toPath();
      Files.delete(path);
      return path.toString();
    } catch (IOException | B2Exception e) {
      throw new ErrorInternal(e.getMessage());
    }
  }

  public void deleteFile(List<String> keyName) {

    if (keyName.isEmpty()) {
      throw new BadRequest("valor no permitido");
    }

    keyName.forEach(name -> {
                if (name.isEmpty()) {
                  throw new BadRequest("object name no puede ser nulo");
                }

      try {
        B2Bucket bucket = b2StorageClient.getBucketOrNullByName(bucketName);

        B2ListFileVersionsRequest request = B2ListFileVersionsRequest
                .builder(bucket.getBucketId())
                .setStartFileName(name)
                .setPrefix(name)
                .build();
        for (B2FileVersion version : b2StorageClient.fileVersions(request)) {
          if (version.getFileName().equals(name)) {
            b2StorageClient.deleteFileVersion(version);
          }
        }
      } catch (B2Exception e) {
        throw new ErrorInternal(e.getMessage());
      }
    });
  }

  public String preSignFile(String keyName) {
    if (keyName.isEmpty()) {
      throw new BadRequest("keyName no puede ser nulo");
    }


    return "";
  }


  private ExecutorService getExecutor() {
    if (executor == null) {
      executor = Executors.newFixedThreadPool(4, B2ExecutorUtils.createThreadFactory("AnimeApp - Java" + "-%d"));
    }
    return executor;
  }

  public File convert(MultipartFile file) throws IOException {
    //ACA debe haber un nuevo nombre unico fecha + uuidd + oldname (en este orden para conservar el file.*)
    UUID uuid = UUID. randomUUID();
    String newName = uuid.toString() + new Date().getTime() + Objects.requireNonNull(file.getOriginalFilename());
    File convFile = new File(newName.replace(" ", ""));
    var result = convFile.createNewFile();

    if (!result) {
      throw new ErrorInternal("Error al subir el file");
    }

    try (FileOutputStream fos = new FileOutputStream(convFile)) {
      fos.write(file.getBytes());
    } catch (Exception ex) {
      throw new ErrorInternal("Error al subir el file: " + ex.getMessage());
    }
    return convFile;
  }
}
