package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-11T20:01:37.128+0100")
@StaticMetamodel(StanZaawansowania.class)
public class StanZaawansowania_ {
	public static volatile SingularAttribute<StanZaawansowania, Long> id;
	public static volatile SingularAttribute<StanZaawansowania, Osoba> osoba;
	public static volatile SingularAttribute<StanZaawansowania, Lekcja> lekcja;
	public static volatile SingularAttribute<StanZaawansowania, Date> dataUdostepnienia;
	public static volatile SingularAttribute<StanZaawansowania, Date> dataWyslania;
	public static volatile SingularAttribute<StanZaawansowania, Date> dataSprawdzenia;
	public static volatile SingularAttribute<StanZaawansowania, String> komentarz;
	public static volatile SingularAttribute<StanZaawansowania, Boolean> czyOdczytano;
	public static volatile SingularAttribute<StanZaawansowania, Date> dataOdczytania;
	public static volatile SingularAttribute<StanZaawansowania, Boolean> czyOdczytanoKomentarz;
	public static volatile SingularAttribute<StanZaawansowania, Date> dataOdczytaniaKomentarza;
}
