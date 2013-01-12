package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.ksb.agape.domain.model.dict.Status;

@Generated(value="Dali", date="2012-12-11T20:01:37.123+0100")
@StaticMetamodel(Pytanie.class)
public class Pytanie_ {
	public static volatile SingularAttribute<Pytanie, Long> id;
	public static volatile SingularAttribute<Pytanie, Long> nrPytanie;
	public static volatile SingularAttribute<Pytanie, Long> nrLekcja;
	public static volatile SingularAttribute<Pytanie, String> tresc;
	public static volatile SingularAttribute<Pytanie, Boolean> widocznosc;
	public static volatile SingularAttribute<Pytanie, Date> dataDodania;
	public static volatile SingularAttribute<Pytanie, Date> dataArch;
	public static volatile SingularAttribute<Pytanie, Status> status;
}
