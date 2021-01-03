package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String url="jdbc:sqlite:knjige.db";

        try{
           Connection conn = DriverManager.getConnection(url, "username", "password");
//            Statement stnt = conn.createStatement();
//
//            System.out.println("Unesite naslov knjige: ");
//            Scanner ulaz = new Scanner(System.in);
//            String naslovPretraga = ulaz.nextLine();
//
//            ResultSet rs =stnt.executeQuery("SELECT * FROM knjiga");
//
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM knjiga WHERE naslov=? or autor=?");

            System.out.println("Unesite naslov ili autora knjige: ");
            Scanner ulaz = new Scanner(System.in);
            String pretraga = ulaz.nextLine();

            ps.setString(1,pretraga);
            ps.setString(2,pretraga);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt(1);
                String naslov= rs.getString(2);
                String autor=rs.getString(3);
                String isbn=rs.getString(4);
                System.out.println("ID: "+ id + "Naslov: "+naslov);
            };
            conn.close();
        }catch (SQLException e){
            System.out.println("Greska u radu sa bazom podataka");
            System.out.print(e.getMessage());
        }
    }
}
