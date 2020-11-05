package JavaBook;

import java.sql.*;
import java.util.Scanner;

public class Book {
    Scanner sc = new Scanner(System.in);
    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }

    public void displayMenu() {
        System.out.println("Welcome to E-Book Online Program ^_^");
        System.out.println("Please choose what you want to do");
        System.out.println("1. Show top 10 newest books");
        System.out.println("2. Show top 10 most popular books");
        System.out.println("3. Find books by Category");
        System.out.println("4. Find books by Name");
        System.out.println("5. Find books by BookID");
        System.out.println("6. Show top 10 newest orders");
        System.out.println("7. Show orders by customer ID");
        System.out.println("8. Show order status by order ID");
        System.out.println("9. Show orders by order ID");
        System.out.println("10. Show orders waiting to be delivered");
        System.out.println("11. Show orders packed");
        System.out.println("12. Show orders delivering");
        System.out.println("13. Show successful delivered orders");
        System.out.println("14. Show cancelled orders");
        System.out.println("15. Back to menu");
        System.out.println("16. Exit from the Program");
        System.out.println();
    }

//    public void tryCatchSqlandShow(String strSelect) {
//        try (
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "");
//                Statement stmt = conn.createStatement();
//        ) {
//            System.out.println("The SQL statement is: " + strSelect);
//            ResultSet rset = stmt.executeQuery(strSelect);
//            System.out.println("The records for displaying all products");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eBookStore", "root", "");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }

    public void menu1() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from books" +
                    " order by year desc limit 10;");
            System.out.println("The records for displaying top 10 newest books are: \n");
            System.out.println(padRight("BoodID", 10) +
                    padRight("Name",50) + "Year");
            while (rs.next()) {
                int BookId = rs.getInt("BookId");
//                int CatID = rs.getInt("CatID");
                String Name = rs.getNString("Name");
//                float price = rs.getFloat("Price");
//                int status = rs.getInt("status");
                int year = rs.getInt("year");
//                String createdDate = rs.getTimestamp("createdDate").toString();
//                String updatedDate = rs.getTimestamp("updatedDate").toString();

                System.out.println(padRight(Integer.toString(BookId), 10) +
                        padRight(Name,50) + year);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void menu2() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery("select oD.bookID, Name, sum(oD.price * oD.qty) AS \"SoTienBanDuoc\" from books" +
                    " inner join ordersDetails oD on books.BookId = oD.bookID" +
                    " group by BookId, Name" +
                    " order by sum(oD.price * oD.qty) desc limit 10;");
            System.out.println("The records for displaying top 10 most popular books are:\n");
            System.out.println(padRight("BookID",10) +
                    padRight("Name", 50) + "TotalMoney");
            while (rs.next()) {
                int BookId = rs.getInt("BookId");
//                int CatID = rs.getInt("CatID");
                String Name = rs.getNString("Name");
//                float price = rs.getFloat("Price");
//                int status = rs.getInt("status");
//                int year = rs.getInt("year");
//                String createdDate = rs.getTimestamp("createdDate").toString();
//                String updatedDate = rs.getTimestamp("updatedDate").toString();
                String sum = rs.getString(3);

                System.out.println(padRight(Integer.toString(BookId),10) +
                        padRight(Name, 50) + sum);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void menu3() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            String catName;
            System.out.print("Please enter the name of category you want to find: ");
            catName = sc.nextLine();

            st = con.createStatement();
            rs = st.executeQuery("select * from books " +
                    "inner join category c on books.CatID = c.CatID " +
                    "where c.Name = \'" + catName + "\';");
            System.out.println("The records for books in " + catName + " are: ");
            while (rs.next()) {
                int BookId = rs.getInt("BookId");
//                int CatID = rs.getInt("CatID");
                String Name = rs.getNString("Name");
                float price = rs.getFloat("Price");
//                int status = rs.getInt("status");
//                int year = rs.getInt("year");
//                String createdDate = rs.getTimestamp("createdDate").toString();
//                String updatedDate = rs.getTimestamp("updatedDate").toString();

                System.out.println(padRight(Integer.toString(BookId),10) +
                        padRight(Name, 50) + price);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void menu4() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            int searchYear;
            System.out.print("Please enter the year you want to find: ");
            searchYear = Integer.parseInt(sc.nextLine());

            st = con.createStatement();
            rs = st.executeQuery("select * from books " +
                    "where year = " + searchYear + ";");
            System.out.println("The records for books search by " + searchYear + " are: ");
            System.out.println(padRight("BookID",10) +
                    padRight("Name", 50) + "Year");
            while (rs.next()) {
                int BookId = rs.getInt("BookId");
//                int CatID = rs.getInt("CatID");
                String Name = rs.getNString("Name");
//                float price = rs.getFloat("Price");
//                int status = rs.getInt("status");
                int year = rs.getInt("year");
//                String createdDate = rs.getTimestamp("createdDate").toString();
//                String updatedDate = rs.getTimestamp("updatedDate").toString();

                System.out.println(padRight(Integer.toString(BookId),10) +
                        padRight(Name, 50) + year);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void menu5() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            int searchBookID;
            System.out.print("Please enter book ID you want to find: ");
            searchBookID = Integer.parseInt(sc.nextLine());

            st = con.createStatement();
            rs = st.executeQuery("select * from books " +
                    "where BookId = " + searchBookID + ";");
            System.out.println("The records for books search by " + searchBookID + " are: ");
            System.out.println(padRight("BookID",10) +
                    padRight("Name", 50) + "Year");
            while (rs.next()) {
                int BookId = rs.getInt("BookId");
//                int CatID = rs.getInt("CatID");
                String Name = rs.getNString("Name");
//                float price = rs.getFloat("Price");
//                int status = rs.getInt("status");
                int year = rs.getInt("year");
//                String createdDate = rs.getTimestamp("createdDate").toString();
//                String updatedDate = rs.getTimestamp("updatedDate").toString();

                System.out.println(padRight(Integer.toString(BookId),10) +
                        padRight(Name, 50) + year);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void menu6() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from orders\n" +
                    "        where status = 1\n" +
                    "        order by dateOrdered desc limit 10;");
            System.out.println("The records for top 10 newest orders are: \n");
            System.out.println(padRight("OrderID",10) +
                    padRight("CustomerID", 15) + padRight("DateOrdered", 20) +
                    padRight("DateRequired", 20) + padRight("Status", 10) +
                    padRight("Total", 15) + "Discount");
            while (rs.next()) {
                int orderId = rs.getInt(1);
                int customerId = rs.getInt(2);
                String dateOrdered = rs.getString(3);
                String dateRequired = rs.getString(4);
                int status = rs.getInt(5);
                float total = rs.getFloat(6);
                int discount = rs.getInt(7);
                System.out.println(padRight(Integer.toString(orderId), 10) +
                        padRight(Integer.toString(customerId), 15) +
                        padRight(dateOrdered, 20) + padRight(dateRequired, 20) +
                        padRight(Integer.toString(status), 10) +
                        padRight(Float.toString(total), 15) + discount + "%");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void menu7() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            int searchCustomerID;
            System.out.print("Please enter customer ID: ");
            searchCustomerID = Integer.parseInt(sc.nextLine());

            st = con.createStatement();
            rs = st.executeQuery("select * from orders\n" +
                    "        where CustomerID = " + searchCustomerID +";");
            System.out.println("The records for customer with ID=" + searchCustomerID + " are: \n");
            System.out.println(padRight("OrderID",10) +
                    padRight("CustomerID", 15) + padRight("DateOrdered", 20) +
                    padRight("DateRequired", 20) + padRight("Status", 10) +
                    padRight("Total", 15) + "Discount");
            while (rs.next()) {
                int orderId = rs.getInt(1);
                int customerId = rs.getInt(2);
                String dateOrdered = rs.getString(3);
                String dateRequired = rs.getString(4);
                int status = rs.getInt(5);
                float total = rs.getFloat(6);
                int discount = rs.getInt(7);
                System.out.println(padRight(Integer.toString(orderId), 10) +
                        padRight(Integer.toString(customerId), 15) +
                        padRight(dateOrdered, 20) + padRight(dateRequired, 20) +
                        padRight(Integer.toString(status), 10) +
                        padRight(Float.toString(total), 15) + discount + "%");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void menu8() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            int searchOrderID;
            System.out.print("Please enter order ID: ");
            searchOrderID = Integer.parseInt(sc.nextLine());

            st = con.createStatement();
            rs = st.executeQuery("select orderID, status from orders\n" +
                    "        where orderID = " + searchOrderID + ";");
            System.out.println("The records for order with ID=" + searchOrderID + " are: \n");
            System.out.println(padRight("OrderID",10) + padRight("Status", 10));
            while (rs.next()) {
                int orderId = rs.getInt(1);
                int status = rs.getInt(2);
                System.out.println(padRight(Integer.toString(orderId), 10) +
                        padRight(Integer.toString(status), 10));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void menu9() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            int searchOrderID;
            System.out.print("Please enter order ID: ");
            searchOrderID = Integer.parseInt(sc.nextLine());

            st = con.createStatement();
            rs = st.executeQuery("select * from orders\n" +
                    "        where orderID = " + searchOrderID + ";");
            System.out.println("The records for order with ID=" + searchOrderID + " are: \n");
            System.out.println(padRight("OrderID",10) +
                    padRight("CustomerID", 15) + padRight("DateOrdered", 20) +
                    padRight("DateRequired", 20) + padRight("Status", 10) +
                    padRight("Total", 15) + "Discount");
            while (rs.next()) {
                int orderId = rs.getInt(1);
                int customerId = rs.getInt(2);
                String dateOrdered = rs.getString(3);
                String dateRequired = rs.getString(4);
                int status = rs.getInt(5);
                float total = rs.getFloat(6);
                int discount = rs.getInt(7);
                System.out.println(padRight(Integer.toString(orderId), 10) +
                        padRight(Integer.toString(customerId), 15) +
                        padRight(dateOrdered, 20) + padRight(dateRequired, 20) +
                        padRight(Integer.toString(status), 10) +
                        padRight(Float.toString(total), 15) + discount + "%");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void menu10() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from orders\n" +
                    "        where status = 2\n" +
                    "        order by dateOrdered desc limit 10;");
            System.out.println("The records for orders waiting to be delivered are: \n");
            System.out.println(padRight("OrderID",10) +
                    padRight("CustomerID", 15) + padRight("DateOrdered", 20) +
                    padRight("DateRequired", 20) + padRight("Status", 10) +
                    padRight("Total", 15) + "Discount");
            while (rs.next()) {
                int orderId = rs.getInt(1);
                int customerId = rs.getInt(2);
                String dateOrdered = rs.getString(3);
                String dateRequired = rs.getString(4);
                int status = rs.getInt(5);
                float total = rs.getFloat(6);
                int discount = rs.getInt(7);
                System.out.println(padRight(Integer.toString(orderId), 10) +
                        padRight(Integer.toString(customerId), 15) +
                        padRight(dateOrdered, 20) + padRight(dateRequired, 20) +
                        padRight(Integer.toString(status), 10) +
                        padRight(Float.toString(total), 15) + discount + "%");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void menu11() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from orders\n" +
                    "        where status = 3\n" +
                    "        order by dateOrdered desc limit 10;");
            System.out.println("The records for orders being delivered are: \n");
            System.out.println(padRight("OrderID",10) +
                    padRight("CustomerID", 15) + padRight("DateOrdered", 20) +
                    padRight("DateRequired", 20) + padRight("Status", 10) +
                    padRight("Total", 15) + "Discount");
            while (rs.next()) {
                int orderId = rs.getInt(1);
                int customerId = rs.getInt(2);
                String dateOrdered = rs.getString(3);
                String dateRequired = rs.getString(4);
                int status = rs.getInt(5);
                float total = rs.getFloat(6);
                int discount = rs.getInt(7);
                System.out.println(padRight(Integer.toString(orderId), 10) +
                        padRight(Integer.toString(customerId), 15) +
                        padRight(dateOrdered, 20) + padRight(dateRequired, 20) +
                        padRight(Integer.toString(status), 10) +
                        padRight(Float.toString(total), 15) + discount + "%");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void menu12() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from orders\n" +
                    "        where status = 4\n" +
                    "        order by dateOrdered desc limit 10;");
            System.out.println("The records for packed orders are: \n");
            System.out.println(padRight("OrderID",10) +
                    padRight("CustomerID", 15) + padRight("DateOrdered", 20) +
                    padRight("DateRequired", 20) + padRight("Status", 10) +
                    padRight("Total", 15) + "Discount");
            while (rs.next()) {
                int orderId = rs.getInt(1);
                int customerId = rs.getInt(2);
                String dateOrdered = rs.getString(3);
                String dateRequired = rs.getString(4);
                int status = rs.getInt(5);
                float total = rs.getFloat(6);
                int discount = rs.getInt(7);
                System.out.println(padRight(Integer.toString(orderId), 10) +
                        padRight(Integer.toString(customerId), 15) +
                        padRight(dateOrdered, 20) + padRight(dateRequired, 20) +
                        padRight(Integer.toString(status), 10) +
                        padRight(Float.toString(total), 15) + discount + "%");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void menu13() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from orders\n" +
                    "        where status = 5\n" +
                    "        order by dateOrdered desc limit 10;");
            System.out.println("The records for successful orders are: \n");
            System.out.println(padRight("OrderID",10) +
                    padRight("CustomerID", 15) + padRight("DateOrdered", 20) +
                    padRight("DateRequired", 20) + padRight("Status", 10) +
                    padRight("Total", 15) + "Discount");
            while (rs.next()) {
                int orderId = rs.getInt(1);
                int customerId = rs.getInt(2);
                String dateOrdered = rs.getString(3);
                String dateRequired = rs.getString(4);
                int status = rs.getInt(5);
                float total = rs.getFloat(6);
                int discount = rs.getInt(7);
                System.out.println(padRight(Integer.toString(orderId), 10) +
                        padRight(Integer.toString(customerId), 15) +
                        padRight(dateOrdered, 20) + padRight(dateRequired, 20) +
                        padRight(Integer.toString(status), 10) +
                        padRight(Float.toString(total), 15) + discount + "%");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void menu14() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from orders\n" +
                    "        where status = 0\n" +
                    "        order by dateOrdered desc limit 10;");
            System.out.println("The records for cancelled orders are: \n");
            System.out.println(padRight("OrderID",10) +
                    padRight("CustomerID", 15) + padRight("DateOrdered", 20) +
                    padRight("DateRequired", 20) + padRight("Status", 10) +
                    padRight("Total", 15) + "Discount");
            while (rs.next()) {
                int orderId = rs.getInt(1);
                int customerId = rs.getInt(2);
                String dateOrdered = rs.getString(3);
                String dateRequired = rs.getString(4);
                int status = rs.getInt(5);
                float total = rs.getFloat(6);
                int discount = rs.getInt(7);
                System.out.println(padRight(Integer.toString(orderId), 10) +
                        padRight(Integer.toString(customerId), 15) +
                        padRight(dateOrdered, 20) + padRight(dateRequired, 20) +
                        padRight(Integer.toString(status), 10) +
                        padRight(Float.toString(total), 15) + discount + "%");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
