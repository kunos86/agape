package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-01-12T19:59:20.889+0100")
@StaticMetamodel(Kurs.class)
public class Kurs_ {
	public static volatile SingularAttribute<Kurs, Long> id;
	public static volatile SingularAttribute<Kurs, Long> nrKursu;
	public static volatile SingularAttribute<Kurs, String> tytul;
	public static volatile SingularAttribute<Kurs, String> opis;
	public static volatile SingularAttribute<Kurs, Boolean> widocznosc;
	public static volatile SingularAttribute<Kurs, Date> dataDodania;
	public static volatile SingularAttribute<Kurs, Date> dataArch;
	public static volatile SingularAttribute<Kurs, String> status;
	public static volatile SingularAttribute<Kurs, byte[]> image;
	public static volatile CollectionAttribute<Kurs, Lekcja> lekcje;
}
