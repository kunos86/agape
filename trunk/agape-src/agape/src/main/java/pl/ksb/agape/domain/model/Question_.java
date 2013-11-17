package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-10-31T23:58:42.055+0100")
@StaticMetamodel(Question.class)
public class Question_ {
	public static volatile SingularAttribute<Question, Long> id;
	public static volatile SingularAttribute<Question, Long> number;
	public static volatile SingularAttribute<Question, Lesson> lesson;
	public static volatile SingularAttribute<Question, String> content;
	public static volatile SingularAttribute<Question, Boolean> enabled;
	public static volatile SingularAttribute<Question, Date> creationDate;
	public static volatile SingularAttribute<Question, Date> modificationDate;
	public static volatile SingularAttribute<Question, String> status;
}
