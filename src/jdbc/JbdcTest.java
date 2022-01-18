package jdbc;

import java.sql.*;
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
        query2();
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
}
