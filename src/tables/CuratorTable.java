package tables;

import java.sql.SQLException;

public class CuratorTable extends AbsTable{
   private String tableName = "curators";

   public CuratorTable() {super("curators"); }


   @Override
   public void create() throws SQLException {
      iDbExecutor.execute(String.format("CREATE table %s (id int not null, fio varchar(40));", tableName), false);

   }

   @Override
   public void insert() throws SQLException {
      iDbExecutor.execute(String.format("INSERT INTO %s VALUES " +
              "(1, 'Первыхин Ростик'), " +
              "(2, 'Втораяева Анася'), " +
              "(3, 'Третьяков Савва'), " +
              "(4, 'Четвертных Владимир');", tableName), false);
   }
}
