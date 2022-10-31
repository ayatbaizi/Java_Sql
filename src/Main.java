import db.IDbExecutor;
import db.MySqlDbExecutor;
import tables.AbsTable;
import tables.CuratorTable;
import tables.GroupTable;
import tables.StudentTable;

import java.sql.SQLException;

public class Main {


   public static void main(String... args) throws SQLException {
      IDbExecutor iDbExecutor = new MySqlDbExecutor();

      AbsTable studentTable = new StudentTable();
      AbsTable curatorTable = new CuratorTable();
      AbsTable groupTable = new GroupTable();


      try {
         if (studentTable.isTableCreated()) {
            studentTable.delete();
         }

         if (curatorTable.isTableCreated()) {
            curatorTable.delete();
         }
         if (groupTable.isTableCreated()) {
            groupTable.delete();
         }

         studentTable.create();
         studentTable.insert();

         curatorTable.create();
         curatorTable.insert();

         groupTable.create();
         groupTable.insert();

         studentTable.selectStudents();
         studentTable.getSelectCount();
         studentTable.selectFemaleStudents();
         groupTable.update();
         curatorTable.selectGroupsCurator();
         studentTable.getStudentsByNameGroup();


      } finally {
         studentTable.getiDbExecutor().close();
      }
   }
}