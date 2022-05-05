package course.java.model;

public enum Role {
   READER("Has basic permissions to view book information"),
   AUTHOR("And add new books in the system"),
   ADMIN("Can manage users and books - has all permissions");

   private String description;

   Role(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }
}
