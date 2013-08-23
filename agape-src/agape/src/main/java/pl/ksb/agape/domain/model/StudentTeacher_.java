package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-08-15T16:32:03.271+0200")
@StaticMetamodel(StudentTeacher.class)
public class StudentTeacher_ {
	public static volatile SingularAttribute<StudentTeacher, Long> id;
	public static volatile SingularAttribute<StudentTeacher, User> teacher;
	public static volatile SingularAttribute<StudentTeacher, User> student;
	public static volatile SingularAttribute<StudentTeacher, Date> creationDate;
	public static volatile SingularAttribute<StudentTeacher, Date> deletedDate;
	public static volatile SingularAttribute<StudentTeacher, String> current;
}
