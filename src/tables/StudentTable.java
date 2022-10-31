package tables;

import java.sql.SQLException;

public class StudentTable extends AbsTable {
   private String tableName = "students";
   public StudentTable() {
      super("students");
   }

   String[] arrayPredicate = new String[] {"sex = f"};

   @Override
   protected String createPredicate(String[] predicateValues) {
      return super.createPredicate(arrayPredicate);
   }


   @Override
   public void create() throws SQLException {
      iDbExecutor.execute(String.format("CREATE table %s (id int not null, fio varchar(40), sex varchar(40), idGroup int not null);", tableName), false);
   }

   @Override
   public void insert() throws SQLException {
      iDbExecutor.execute(String.format("INSERT INTO %s VALUES " +
              "(1, 'Яковленко Ростислав', 'm', 3), " +
              "(2, 'Малафеева Анастасия', 'f', 3), " +
              "(3, 'Берков Савва', 'm', 2), " +
              "(4, 'Горев Игнатий', 'm', 1), " +
              "(5, 'Сияница Данила', 'm', 1), " +
              "(6, 'Бычков Никита', 'm', 2), " +
              "(7, 'Катькина Анжела', 'f', 1), " +
              "(8, 'Букирь Герасим', 'm', 1), " +
              "(9, 'Луговой Максим', 'm', 2), " +
              "(10, 'Снегирев Павел', 'm', 2), " +
              "(11, 'Толкачева Жанна', 'f', 2), " +
              "(12, 'Ростов Василий', 'm', 1), " +
              "(13, 'Лямин Венедикт', 'm', 2), " +
              "(14, 'Лубашева Катерина', 'f', 3), " +
              "(15, 'Голубова Таисия', 'f', 3);", tableName), false);
   }


   }

   /*public void selectStudents() throws SQLException {
      ResultSet students = iDbExecutor.execute(String.format("SELECT students.fio, groupStudent.groupName, curators.fio   FROM students  INNER JOIN groupStudent ON groupStudent.id = students.idGroup  INNER JOIN curators ON curators.id = groupStudent.idCurator;"), false);
      System.out.println("This is list students:");
      while (students.next()) {
         int id = students.getInt(1);
         String fio = students.getString(2);
         String sex = students.getString(3);
         int idGroup = students.getInt(4);
         System.out.println(id + "|" + fio + "|" + sex + "|" + idGroup + "|");
      }
   }*/


