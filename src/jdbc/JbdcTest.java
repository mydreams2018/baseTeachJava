package jdbc;

import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

public class JbdcTest {

    static Connection connection ;
    static {
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","yjssaje");
        try {
            JbdcTest.connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/flybbs?useSSL=true&serverTimezone=GMT%2B8",
                    properties);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        DriverManager.drivers().forEach(System.out::println);
        //数据库的元数据
        DatabaseMetaData metaData = connection.getMetaData();
//        query();
//        query2();
//        insert();
//        updateBatch();
//        sqlInject();
//        sqlPreparedInject();
//        updatePreparedBatch();
//        PreparedExecute();
        PreparedUpdate();
    }

    private static void PreparedExecute()  {
        try(PreparedStatement prepareStatement = connection.prepareStatement("select * from user_collect where id=?")){
            prepareStatement.setInt(1,1);
            boolean isResultSet = prepareStatement.execute();
            if(isResultSet){
                ResultSet resultSet = prepareStatement.getResultSet();
                //返回的表结构信息
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            for(int x=1;x<=metaData.getColumnCount();x++){
//                System.out.println(metaData.getColumnClassName(x));
//                System.out.println(metaData.getColumnName(x));
//                System.out.println(metaData.getColumnDisplaySize(x));
//            }
                ResultSetMetaData metaData = prepareStatement.getMetaData();
                for(int x=1;x<=metaData.getColumnCount();x++){
                    System.out.println(metaData.getColumnClassName(x));
                    System.out.println(metaData.getColumnName(x));
                    System.out.println(metaData.getColumnDisplaySize(x));
                }
                //数据信息
                while (resultSet.next()){
                    System.out.print(resultSet.getString(1)+" ");
                    System.out.print(resultSet.getString(2)+" ");
                    System.out.print(resultSet.getString(3)+" ");
                    System.out.println();
                }

                System.out.println(prepareStatement.getMoreResults());
            }else{
                System.out.println("count:"+prepareStatement.getUpdateCount());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void sqlInject() {
        try(Statement statement = connection.createStatement()){
            String account = "qepau888";
            String password = "1 or account='qepau888'";
            String sql = "select * from user where account='"+account+"' and password="+password;
            ResultSet resultSet = statement.executeQuery(sql);
            //数据信息
            while (resultSet.next()){
                System.out.print(resultSet.getString(1)+" ");
                System.out.print(resultSet.getString(2)+" ");
                System.out.print(resultSet.getString(3)+" ");
                System.out.println();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void sqlPreparedInject() {
        try(PreparedStatement preparedStatement = connection.prepareStatement("select * from user where account=? and password=?")){
            String account = "qepau888";
            String password = "6666";
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            //数据信息
            while (resultSet.next()){
                System.out.print(resultSet.getString(1)+" ");
                System.out.print(resultSet.getString(2)+" ");
                System.out.print(resultSet.getString(3)+" ");
                System.out.println();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void query(){
        try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)){
            ResultSet resultSet = statement.executeQuery("select * from user_collect");
            //返回的表结构信息
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            for(int x=1;x<=metaData.getColumnCount();x++){
//                System.out.println(metaData.getColumnClassName(x));
//                System.out.println(metaData.getColumnName(x));
//                System.out.println(metaData.getColumnDisplaySize(x));
//            }
            //数据信息
            while (resultSet.next()){
                int row = resultSet.getRow();
                if(row == 12){
                   // resultSet.updateString("user_account", "AAAAAAA"); // updates the
                   // resultSet.updateRow(); // updates the row in the data source

//                    resultSet.moveToInsertRow(); // moves cursor to the insert row
//                    // first column of the insert row to be AINSWORTH
//                    resultSet.updateInt(1,66);
//                    resultSet.updateString(2,"BBBBB"); // updates the second column to be 35
//                    resultSet.insertRow();
//                    resultSet.moveToCurrentRow();
                }
                System.out.print(resultSet.getString(2)+" ");
                System.out.print(resultSet.getString(5)+" ");
                System.out.print(resultSet.getString(6)+" ");
                System.out.println();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void query2(){
        try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)){
            boolean isResultSet = statement.execute("select * from user_collect");
            if(isResultSet){
                ResultSet resultSet = statement.getResultSet();
                //返回的表结构信息
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            for(int x=1;x<=metaData.getColumnCount();x++){
//                System.out.println(metaData.getColumnClassName(x));
//                System.out.println(metaData.getColumnName(x));
//                System.out.println(metaData.getColumnDisplaySize(x));
//            }
                //数据信息
                while (resultSet.next()){
                    System.out.print(resultSet.getString(2)+" ");
                    System.out.print(resultSet.getString(5)+" ");
                    System.out.print(resultSet.getString(6)+" ");
                    System.out.println();
                }

                System.out.println(statement.getMoreResults());
            }else{
                System.out.println("count:"+statement.getUpdateCount());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void insert(){
        try(Statement statement = connection.createStatement()){
            int num = statement.executeUpdate("insert into user_collect (user_account, class_id,port_id,collect_time,port_title)values ('qepau886','1','2','2021-12-16','CCCCCC')"
                                     ,Statement.RETURN_GENERATED_KEYS);
                System.out.println("count:"+num);
                ResultSet generatedKeys = statement.getGeneratedKeys();
                ResultSetMetaData metaData = generatedKeys.getMetaData();
                for(int x=1;x<=metaData.getColumnCount();x++){
                    System.out.println(metaData.getColumnClassName(x));
                    System.out.println(metaData.getColumnName(x));
                    System.out.println(metaData.getColumnDisplaySize(x));
                }
                //数据信息
                while (generatedKeys.next()){
                    System.out.print(generatedKeys.getString(1));
                }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void updateBatch(){
        try(Statement statement = connection.createStatement()){
            statement.addBatch("insert into user_collect (user_account, class_id,port_id,collect_time,port_title)values ('qepau886','1','2','2021-12-16','CCCCCC')");
            statement.addBatch("insert into user_collect (user_account, class_id,port_id,collect_time,port_title)values ('qepau886','1','2','2021-12-16','CCCCCC')");
            statement.addBatch("insert into user_collect (user_account, class_id,port_id,collect_time,port_title)values ('qepau886','1','2','2021-12-16','CCCCCC')");
            statement.addBatch("insert into user_collect (user_account, class_id,port_id,collect_time,port_title)values ('qepau886','1','2','2021-12-16','CCCCCC')");
            statement.addBatch("insert into user_collect (user_account, class_id,port_id,collect_time,port_title)values ('qepau886','1','2','2021-12-16','CCCCCC')");
            int[] ints = statement.executeBatch();
            System.out.println(Arrays.toString(ints));
            statement.clearBatch();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void updatePreparedBatch(){
        try(PreparedStatement statement = connection.prepareStatement("insert into user_collect (user_account, class_id,port_id,collect_time,port_title) values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,"qepau888");
            statement.setInt(2,56);
            statement.setInt(3,56);
            statement.setString(4,"2022-02-02");
            statement.setString(5,"test");
            statement.addBatch();
            statement.setString(1,"qepau666");
            statement.setInt(2,56);
            statement.setInt(3,56);
            statement.setString(4,"2022-02-02");
            statement.setString(5,"test");
            statement.addBatch();
            int[] ints = statement.executeBatch();
            System.out.println(Arrays.toString(ints));
            statement.clearBatch();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            ResultSetMetaData metaData = generatedKeys.getMetaData();
            for(int x=1;x<=metaData.getColumnCount();x++){
                System.out.println(metaData.getColumnClassName(x));
                System.out.println(metaData.getColumnName(x));
                System.out.println(metaData.getColumnDisplaySize(x));
            }
            //数据信息
            while (generatedKeys.next()){
                System.out.println(generatedKeys.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void PreparedUpdate(){
        try(PreparedStatement statement = connection.prepareStatement("UPDATE user_collect SET user_account = ? WHERE id = ?")){
            statement.setString(1,"6666666");
            statement.setInt(2,1);
            int i = statement.executeUpdate();
            System.out.println(i);

            ResultSet resultSet = statement.getResultSet();
            System.out.println(resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
