package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import modeli.Avion;
import modeli.Roba;

import java.util.List;

public class Zadatak3IzmenaVrednosti {

    static Dao<Avion, Integer> avionDao;
    static Dao<Roba, Integer> robaDao;

    public static void main(String[] args) {

        ConnectionSource connectionSource =null;
        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            List<Roba> roba = robaDao.queryForAll();

            for (Roba rb : roba) {
                System.out.println(rb);

            }

            List<Roba> robas =robaDao.queryForEq(Roba.POLJE_OPIS, "Plasticna stolica");


            Roba r = robas.get(0);

            r.setOpis("Drvena Stolica");

            robaDao.update(r);

        }catch (Exception e){

        }




    }
}
