package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-11T20:01:37.133+0100")
@StaticMetamodel(UczenNauczyciel.class)
public class UczenNauczyciel_ {
	public static volatile SingularAttribute<UczenNauczyciel, Long> id;
	public static volatile SingularAttribute<UczenNauczyciel, Osoba> nauczyciel;
	public static volatile SingularAttribute<UczenNauczyciel, Osoba> uczen;
	public static volatile SingularAttribute<UczenNauczyciel, Date> dataDodania;
	public static volatile SingularAttribute<UczenNauczyciel, Date> dataUsuniecia;
	public static volatile SingularAttribute<UczenNauczyciel, String> aktualny;
}
