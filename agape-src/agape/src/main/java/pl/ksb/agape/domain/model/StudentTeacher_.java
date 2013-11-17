package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-10-31T23:58:42.071+0100")
@StaticMetamodel(StudentTeacher.class)
public class StudentTeacher_ {
	public static volatile SingularAttribute<StudentTeacher, Long> id;
	public static volatile SingularAttribute<StudentTeacher, User> teacher;
	public static volatile SingularAttribute<StudentTeacher, User> student;
	public static volatile SingularAttribute<StudentTeacher, Date> creationDate;
	public static volatile SingularAttribute<StudentTeacher, Date> deletedDate;
	public static volatile SingularAttribute<StudentTeacher, String> current;
}
