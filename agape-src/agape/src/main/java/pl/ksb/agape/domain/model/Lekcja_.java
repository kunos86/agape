package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.ksb.agape.domain.model.dict.Status;

@Generated(value="Dali", date="2012-12-11T20:01:37.098+0100")
@StaticMetamodel(Lekcja.class)
public class Lekcja_ {
	public static volatile SingularAttribute<Lekcja, Long> id;
	public static volatile SingularAttribute<Lekcja, Long> nrLekcji;
	public static volatile SingularAttribute<Lekcja, Long> idKursu;
	public static volatile SingularAttribute<Lekcja, String> opis;
	public static volatile SingularAttribute<Lekcja, String> wprowadzenie;
	public static volatile SingularAttribute<Lekcja, String> zadaniaDomowe;
	public static volatile SingularAttribute<Lekcja, Boolean> widocznosc;
	public static volatile SingularAttribute<Lekcja, Date> dataDodania;
	public static volatile SingularAttribute<Lekcja, Date> dataArch;
	public static volatile SingularAttribute<Lekcja, Status> status;
	public static volatile CollectionAttribute<Lekcja, StanZaawansowania> stanyZaawansowania;
}
