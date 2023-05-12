package org.arcone.lmsapi.util.config.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponseDTO {

  private List<String> messages = new ArrayList<>();

  public ErrorResponseDTO(String message) {
    addMessage(message);
  }

  public ErrorResponseDTO(List<String> messages) {
    this.messages.addAll(messages);
  }

  public void addMessage(String message) {
    this.messages.add(message);
  }
}
