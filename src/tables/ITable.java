package tables;

import java.sql.SQLException;

public interface ITable {
   void create() throws SQLException;
   void delete() throws SQLException;
   void insert() throws SQLException;
   /*void select() throws SQLException;*/

   void update() throws SQLException;

   void selectFemaleStudents() throws SQLException;

   void getSelectCount() throws SQLException;

   void getStudentsByNameGroup() throws SQLException;
}
