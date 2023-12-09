package org.issam.ecommerceweb.model;

import org.issam.ecommerceweb.beans.Charge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class changemodel {

    ResultSet rs;
    PreparedStatement pst = null;
    DbConnection db = new DbConnection();
    Connection con;

    public boolean checkCardValidation(String number) {
        try {
            con = db.openConnection();

            pst = con.prepareStatement("select used from charge where card_number=? ");

            pst.setString(1, number);
            rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getString("used").equals("0")) {
                    return true;
                }

            }

        } catch (SQLException ex) {
            db.closeConnection();
            ex.printStackTrace();
        }

        return false;

    }

    public boolean addCard(Charge obj) {

        int i = 0;

        con = db.openConnection();
        System.out.println(con);

        try {

            pst = con.prepareStatement("insert into charge(card_number,	value,used,taken) values (?,?,?,?)");

            pst.setString(1, obj.getCard_number());
            pst.setInt(2, obj.getValue());
            pst.setBoolean(3, obj.getUsed() == 0 ? false : true);
            pst.setBoolean(4, obj.getTaken() == 0 ? false : true);

            i = pst.executeUpdate();

            db.closeConnection();

            if (i > 0) {
                System.out.println("true");
                return true;
            }

        } catch (SQLException ex) {
            db.closeConnection();
            ex.printStackTrace();

        }
        return false;

    }

    public int getSumCardNumber(int value) {

        int countOfCard = 0;

        try {
            con = db.openConnection();
            pst = con.prepareStatement("select count(card_number) as count from charge where value=? and used=false and taken= false ");

            pst.setInt(1, value);
            rs = pst.executeQuery();
            if (rs.next()) {
                countOfCard = Integer.parseInt(rs.getString("count"));

            }

        } catch (SQLException ex) {
            db.closeConnection();
            ex.printStackTrace();
        }

        return countOfCard;

    }

    public boolean checkCardExistForUser(String number) {

        try {
            con = db.openConnection();
            pst = con.prepareStatement("select card_number from charge where  card_number=? and used=0 ");

            pst.setString(1, number);
            rs = pst.executeQuery();
            while (rs.next()) {
                return true;

            }

        } catch (SQLException ex) {
            db.closeConnection();
            ex.printStackTrace();
        }

        return false;
    }

    public int getValuefromNumber(String card_number) {

        try {
            con = db.openConnection();
            pst = con.prepareStatement("select value from charge where  card_number= ? ");

            pst.setString(1, card_number);
            rs = pst.executeQuery();
            if (rs.next()) {

                return rs.getInt("value");

            }

        } catch (SQLException ex) {
            db.closeConnection();
            ex.printStackTrace();
        }

        return 0;

    }

    public String getCard(int value) {
        try {
            con = db.openConnection();
            pst = con.prepareStatement("select card_number from charge where  value=? AND used=false  ");

            pst.setInt(1, value);
            rs = pst.executeQuery();
            if (rs.next()) {

                return rs.getString("card_number");

            }

        } catch (SQLException ex) {
            db.closeConnection();
            ex.printStackTrace();
        }

        return null;

    }

    public boolean setCardUsed(String number) {
        try {
            con = db.openConnection();
            pst = con.prepareStatement("update charge set used=1 where  card_number=?  ");

            pst.setString(1, number);
            int i = pst.executeUpdate();
            if (i == 1) {

                return true;

            }

        } catch (SQLException ex) {
            db.closeConnection();
            ex.printStackTrace();
        }

        return false;

    }

    public boolean setCardTaken(String number) {
        try {
            con = db.openConnection();
            pst = con.prepareStatement("update charge set taken=true where  card_number=?  ");

            pst.setString(1, number);
            int i = pst.executeUpdate();
            if (i == 1) {

                return true;

            }

        } catch (SQLException ex) {
            db.closeConnection();
            ex.printStackTrace();
        }

        return false;

    }

    public boolean numberOfCardIsFound(String numbercheck) {

        try {
            con = db.openConnection();
            pst = con.prepareStatement("select card_number from charge where  card_number=?  ");

            pst.setString(1, numbercheck);
            rs = pst.executeQuery();
            if (rs.next()) {
                db.closeConnection();
                return true;

            }

        } catch (SQLException ex) {

            db.closeConnection();
            ex.printStackTrace();
        }
        db.closeConnection();
        return false;
    }
    
    public int getProfit() {
          int profit=0;
        try {
            
            con = db.openConnection();
            pst = con.prepareStatement("select SUM(value) AS profit from charge where taken=true");

           
            rs = pst.executeQuery();
            if (rs.next()) {

            
                   profit = rs.getInt("profit");
                     db.closeConnection();

            }

        } catch (SQLException ex) {
            db.closeConnection();
            ex.printStackTrace();
        }

        return profit;
      
   
     }
}
