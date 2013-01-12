package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-11T20:01:37.112+0100")
@StaticMetamodel(Odpowiedz.class)
public class Odpowiedz_ {
	public static volatile SingularAttribute<Odpowiedz, Long> id;
	public static volatile SingularAttribute<Odpowiedz, Long> idPytanie;
	public static volatile SingularAttribute<Odpowiedz, Long> idOsoba;
	public static volatile SingularAttribute<Odpowiedz, String> tresc;
	public static volatile SingularAttribute<Odpowiedz, Date> dataArch;
}
