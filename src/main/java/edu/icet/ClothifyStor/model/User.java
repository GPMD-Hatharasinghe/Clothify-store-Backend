package edu.icet.ClothifyStor.model;

import lombok.Data;

@Data

public class User {

      private Integer id;
      private String name;
      private String email;
      private String password;
      private String token;
}