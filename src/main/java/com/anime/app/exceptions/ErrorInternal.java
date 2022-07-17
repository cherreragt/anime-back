package com.anime.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorInternal extends RuntimeException {
  public ErrorInternal(String message) {
    super(message);
  }
}
