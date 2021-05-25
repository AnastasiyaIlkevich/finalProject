package com.finalProject.model;

public class Author {

   private Long id;
   private String name;

   public Author(Long id) {
      this.id = id;
   }

   public Author() {

   }

   @Override
   public String toString() {
      return "'" + name + '\'';
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
