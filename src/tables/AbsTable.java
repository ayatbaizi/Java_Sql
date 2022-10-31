package tables;

import db.IDbExecutor;
import db.MySqlDbExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class AbsTable implements ITable {

   private String tableName;
   protected IDbExecutor iDbExecutor;

   public IDbExecutor getiDbExecutor() {
      return iDbExecutor;
   }

   public AbsTable(String tableName) {
      this.tableName = tableName;
      iDbExecutor = new MySqlDbExecutor();
   }

   @Override
   public void delete() throws SQLException {
      iDbExecutor.execute(String.format("drop table %s", tableName), false);
   }


   public boolean isTableCreated() throws SQLException {
      ResultSet tables = iDbExecutor.execute("show tables", true);
      boolean isTableCreated = false;
      while (tables.next()) {
         if (tables.getString(1).equals(tableName)) {
            isTableCreated = true;
            break;
         }
      }
      return isTableCreated;
   }


   public void selectStudents() throws SQLException {
      ResultSet students = iDbExecutor.execute(String.format(
              "SELECT students.fio, groupTable.groupName, curators.fio " +
                      "FROM students " +
                      "INNER JOIN groupTable ON groupTable.id = students.idGroup " +
                      "INNER JOIN curators ON curators.id = groupTable.idCurator;"), true);

      System.out.println("This is list students:");
      System.out.println("  FIO student  " + "   name group  " + "   FIO curator");
      while (students.next()) {
         String fio = students.getString(1);
         String groupName = students.getString(2);
         String curatorsFio = students.getString(3);

         System.out.println(" | " + fio + " | " + groupName + " | " + curatorsFio + " | ");
      }
   }

   @Override
   public void selectFemaleStudents() throws SQLException {
      ResultSet resultSet = iDbExecutor.execute(String.format("SELECT * FROM %s WHERE sex='f';", tableName), true);
      System.out.println("\n" + " List of Female Students:");
      System.out.println(" Id " + "  FIO student  " + "Sex");
      while (resultSet.next()) {
         int idFemaleStudents = resultSet.getInt(1);
         String fio = resultSet.getString(2);
         String sex = resultSet.getString(3);
         System.out.println(" | " + idFemaleStudents + " | " + fio + " | " + sex + " | ");
      }
   }


   public void selectGroupsCurator() throws SQLException {
      ResultSet resultSet = iDbExecutor.execute(String.format(
              "SELECT groupTable.groupName, curators.fio " +
                      "FROM curators " +
                      "INNER JOIN groupTable ON curators.id = groupTable.idCurator;"), true);

      System.out.println("\n" + "This is list of curators:");
      while (resultSet.next()) {
         String groupName = resultSet.getString(1);
         String curatorsFio = resultSet.getString(2);
         System.out.println(" | " + groupName + " | " + curatorsFio + " | ");
      }
   }

   @Override
   public void update() throws SQLException {
      iDbExecutor.execute(String.format("UPDATE curators SET fio = \"Барашкин Филлип\" WHERE id = 2;"), false);
      System.out.println("\n" + "Update table curators is successfully!");

   }

   protected String createPredicate(String[] predicateValues) {
      return String.format("WHERE $s", String.join(" and ", predicateValues));
   }

   @Override
   public void getSelectCount() throws SQLException {
      ResultSet resultSet = iDbExecutor.execute(String.format("SELECT COUNT(*) FROM %s;", tableName), true);
      if (resultSet.next()) {
         String count = resultSet.getString(1);
         System.out.println("\n" + "Total students - " + count);
      }
   }

   @Override
   public void getStudentsByNameGroup() throws SQLException {
      ResultSet resultSet = iDbExecutor.execute(String.format("SELECT students.fio, groupTable.groupName " +
              "FROM students, groupTable " +
              "WHERE students.idGroup = groupTable.id  and groupName = 'Первая группа'"), true);
      System.out.println("\n" + "Students from group:");
      while (resultSet.next()) {
         String fio = resultSet.getString(1);
         String group = resultSet.getString(2);
         System.out.println(" | " + fio + " | " + group + " | ");
      }
   }
}


