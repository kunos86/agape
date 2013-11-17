package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-03T15:01:17.052+0100")
@StaticMetamodel(EducationState.class)
public class EducationState_ {
	public static volatile SingularAttribute<EducationState, Long> id;
	public static volatile SingularAttribute<EducationState, User> student;
	public static volatile SingularAttribute<EducationState, Lesson> lesson;
	public static volatile SingularAttribute<EducationState, Date> sharedDate;
	public static volatile SingularAttribute<EducationState, Date> sentDate;
	public static volatile SingularAttribute<EducationState, Date> checkedDate;
	public static volatile SingularAttribute<EducationState, String> comment;
	public static volatile SingularAttribute<EducationState, Date> readDate;
	public static volatile SingularAttribute<EducationState, Date> readCommentDate;
}
