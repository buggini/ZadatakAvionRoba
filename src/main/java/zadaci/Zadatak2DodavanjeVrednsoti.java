package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import modeli.Avion;
import modeli.Roba;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Zadatak2DodavanjeVrednsoti {

    static Dao<Avion, Integer> avionDao;
    static Dao<Roba, Integer> robaDao;

    public static void main(String[] args) {

        ConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            avionDao = DaoManager.createDao(connectionSource, Avion.class);
            robaDao = DaoManager.createDao(connectionSource, Roba.class);


            Avion avion1 = new Avion("Avion1", 34);
            Avion avion2 = new Avion("Avion2", 21);
            avionDao.create(avion1);
            avionDao.create(avion2);


            Roba roba1 = new Roba("Patike", "Duboke patike", 1, avion1);
            Roba roba2 = new Roba("Kosulja", "Na duge rukave", 0.4, avion2);
            Roba roba3 = new Roba("Voda", "Voda za pice", 1.4, avion1);
            Roba roba4 = new Roba("Ploce", "Drvene ploce", 3.4, avion2);
            Roba roba5 = new Roba("Stolice", "Plasticne stolice", 2.4, avion2);
            robaDao.create(roba1);
            robaDao.create(roba2);
            robaDao.create(roba3);
            robaDao.create(roba4);
            robaDao.create(roba5);
            List<Roba> roba = robaDao.queryForAll();

            for (Roba rb : roba) {
                System.out.println(rb);

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }
    }
}