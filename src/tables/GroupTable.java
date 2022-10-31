package tables;

import java.sql.SQLException;

public class GroupTable extends AbsTable  {
   private String tableName = "groupTable";
   public GroupTable() {
      super("groupTable");
   }

   @Override
   public void create() throws SQLException {
      iDbExecutor.execute(String.format("CREATE table %s (id int not null, groupName varchar(40), idCurator int not null);", tableName), false);
   }

   @Override
   public void insert() throws SQLException {
      iDbExecutor.execute(String.format("INSERT INTO %s VALUES " +
              "(1, 'первая группа', 1), " +
              "(2, 'вторая группа', 2), " +
              "(3, 'третья группа', 3);", tableName), false);
   }
}
