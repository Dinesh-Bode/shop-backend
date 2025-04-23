package com.web.shop.model.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
  private Integer code;
  private String message;
  private Object data;
}
