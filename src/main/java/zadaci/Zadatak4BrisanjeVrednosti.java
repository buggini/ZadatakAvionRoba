package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import modeli.Avion;
import modeli.Roba;

import java.util.List;

public class Zadatak4BrisanjeVrednosti {

    static Dao<Avion, Integer> avionDao;
    static Dao<Roba, Integer> robaDao;


    public static void main(String[] args) {

        ConnectionSource connectionSource =null;
        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            avionDao = DaoManager.createDao(connectionSource, Avion.class);
            robaDao = DaoManager.createDao(connectionSource, Roba.class);

            List<Roba> roba = robaDao.queryForAll();

            for (Roba rb : roba) {
                System.out.println(rb);

            }


            List<Roba> robas =robaDao.queryForEq(Roba.POLJE_NAZIV, "Voda");
            Roba r =robas.get(0);
            robaDao.delete(r);


        }catch (Exception e){

        }
    }
}
