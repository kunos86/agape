package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-08-22T21:41:20.480+0200")
@StaticMetamodel(Answer.class)
public class Answer_ {
	public static volatile SingularAttribute<Answer, Long> id;
	public static volatile SingularAttribute<Answer, Question> question;
	public static volatile SingularAttribute<Answer, User> student;
	public static volatile SingularAttribute<Answer, String> content;
	public static volatile SingularAttribute<Answer, Date> modyficationDate;
}
