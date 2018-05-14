package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import modeli.Avion;
import modeli.Roba;

import java.util.List;

public class AvionNIt extends Thread {

    static Dao<Avion, Integer> avionDao;
    static Dao<Roba, Integer> robaDao;
    public static boolean dozvoljenoSletanje;
    private static Object localobjec = new Object();
    Avion avion;

    public AvionNIt(Avion avion) {
        this.avion = avion;

    }

    @Override
    public void run() {

            System.out.println("Počinju provere za avion <" + avion.getOznaka() + "> na početku izvršavanja");

            try {
                sleep((long) Math.random() * 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Avion <" + avion.getOznaka() + "> je spreman za poletanje i čeka dozvolu za poletanje");

            synchronized (localobjec) {
            System.out.println("Avion <" + avion.getOznaka() + "> izlazi na pistu i uzlece");

            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Avion je poleteo <" + avion.getOznaka()+">");
        }
    }
    public static void main(String[] args) {



        ConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            avionDao = DaoManager.createDao(connectionSource, Avion.class);
            robaDao = DaoManager.createDao(connectionSource, Roba.class);

            List<Avion> avioni = avionDao.queryForAll();

            for (Avion a : avioni) {
                AvionNIt avionNIt = new AvionNIt(a);
                avionNIt.start();

            }

    }catch(Exception e){
            e.printStackTrace();
        }


    }
}