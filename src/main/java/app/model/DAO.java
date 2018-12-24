package app.model;

import app.entities.Animal;

import javax.faces.bean.ManagedBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


/**
 * Основной класс приложения
 */
@ManagedBean(name = "dao")
public class DAO {
    private static final String PROPERTIES_FILENAME = "application.properties";
    private static final String DB_URL_PROPERTY = "db.url";
    private static final String DB_USERNAME_PROPERTY = "db.username";
    private static final String DB_PASSWORD_PROPERTY = "db.password";

    private Properties settings = new Properties();
    private Scanner scanner = new Scanner(System.in);
    private Connection connection;
    private List<Animal> ListAllAnimal;
    public void setListAllAnimal()
    {
        this.ListAllAnimal=listAllPerson();
    }
    public List<Animal> getListAllAnimal()
    {
        return listAllPerson();
    }
     /**
     * insert into Person
     */
    public void addPerson(Animal a) {

        establishConnection();
        String query =
                "insert into Animals (Animals.Number,Animals.Name,Animals.Vids_ID,Animals.Date) values ( ?, ?, ?, ?)";

        PreparedStatement stmt = null;
        ResultSet rs = null;

       try {
            stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

           stmt.setInt(1, a.getNumber());
            stmt.setString(2, a.getName());
            stmt.setInt(3, a.getVids_ID());
            stmt.setDate(4, new java.sql.Date(a.getDate().getTime()));

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Пользователь успешно добавлен с ID = " + rs.getInt(1));
                }
            } else {
                System.out.println("Не удалось добавить пользователя.");
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка 1");
                // do nothing
            }
            try {
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
            } catch (Exception e) {
                // do nothing
                System.out.println("Произошла ошибка 2");
            }
        }

    }
 public  void deleteAnimal(Animal id)
 {
     establishConnection();
     String query =
             "delete from Animals WHERE `Animal_ID`= ? limit 1";
        Animal a= new Animal();
     PreparedStatement stmt = null;
     ResultSet rs = null;

     try {
         stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
         stmt.setInt(1, id.getAnimal_ID());
         int rows = stmt.executeUpdate();
         if (rows > 0) {
             rs = stmt.getGeneratedKeys();
             if (rs.next()) {
                 System.out.println("Пользователь успешно удален с ID = " + rs.getInt(1));
             }
         } else {
             System.out.println("Не удалось удалить пользователя.");
         }
     } catch (SQLException sqle) {
         System.out.println("Произошла ошибка при выполнении SQL запроса:");
         System.out.println(sqle.getMessage());
     } finally {
         try {
             if (rs != null && !rs.isClosed()) {
                 rs.close();
             }
         } catch (Exception e) {
             System.out.println("Произошла ошибка 2");
             // do nothing
         }
         try {
             if (stmt != null && !stmt.isClosed()) {
                 stmt.close();
             }
         } catch (Exception e) {
             // do nothing
             System.out.println("Произошла ошибка 3");
         }
     }

 }
    /**
     * select <brief> from Person
     */
    public List<Animal> listAllPerson() {

        establishConnection();
        String query = "select * from Animals";
        List<Animal> AnimalList = new ArrayList<Animal>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.connection.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()) {
                java.sql.Date SqlDate=rs.getDate("Date");
                java.util.Date UtilDate = new java.util.Date(SqlDate.getTime());
                        AnimalList.add(new Animal(rs.getInt("Animal_ID"),
                                rs.getInt("Number"),
                                rs.getString("Name"),
                                rs.getInt("Vids_ID"),
                                UtilDate));

            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }

            } catch (Exception e) {
                // do nothing
            }
            try {
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
                closeConnection();
            } catch (Exception e) {
                // do nothing
            }
        }

        // Вернуться в меню
        return AnimalList;
    }

    public Animal readNumber()
    {
        System.out.print("Введите номер: ");
        Integer aNumber = scanner.nextInt();
        return showPersonInfo(aNumber);
    }
    /**
     * select <full> from Person where id = <id>
     */
    public Animal showPersonInfo(int aNumber) {

        establishConnection();
        String query = "select * from Animals where `Animal_ID` = ? limit 1";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Animal a=null;

        try {
            stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, aNumber);
            rs = stmt.executeQuery();

            if (rs.next()) {
                //Date birthDate = rs.getDate("Date");
                 //a = new Animal(
                   //     rs.getInt("Animal_ID"),
                    //    rs.getInt("Number"),
                      //  rs.getString("Name"),
                     //   rs.getInt("Vids_ID")
                //);

                //System.out.println(String.format("%-19s %s", "1. Номер:", accountNumber));
                //System.out.println(String.format("%-19s %s", "2. Имя:", lastName));
               // System.out.println(String.format("%-19s %s", "3. Номер Вольера:", firstName));
               // System.out.println(String.format("%-19s %s", "4. Вид:", VidsID));
            } else {
                System.out.println("Пользователь с таким номером не найден.");
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при выполнении SQL запроса:");
            System.out.println(sqle.getMessage());
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (Exception e) {
                // do nothing
            }
            try {
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
            } catch (Exception e) {
                // do nothing
            }

        }


        return a;
    }


    /**
     * Установить соединение с БД
     */
    public void establishConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Произошла ошибка при загрузке MySQL драйвера.");
            System.exit(1);
        }

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/clients");
            this.connection = ds.getConnection();

        } catch (NamingException e){
            System.out.println("Произошла ошибка");
        }
        catch (SQLException sqle) {
            System.out.println("Произошла ошибка при установке соединения с БД: ");
            System.out.println(sqle.getMessage());
            System.exit(1);
        }
    }

    /**
     * Закрытие соединения с БД
     */
    public void closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException sqle) {
            System.out.println("Произошла ошибка при закрытии соединения:");
            System.out.println(sqle.getMessage());
        }
    }

    /**
     * Загрузка параметров подключения к БД
     */

    }




