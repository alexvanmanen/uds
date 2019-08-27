package nl.qien.uren.repository;

import nl.qien.uren.model.UrenRegistratie;

public interface UrenRegistratieRepository {

    int count();

    int save(UrenRegistratie urenRegistratie);

}
