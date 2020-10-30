package com.agileengine.test.models;

import lombok.Data;

@Data
public class AuthResponse {

  Boolean auth;
  String token;
}
