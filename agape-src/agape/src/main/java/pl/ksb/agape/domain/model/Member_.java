package pl.ksb.agape.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-08-15T11:47:31.572+0200")
@StaticMetamodel(Member.class)
public class Member_ {
	public static volatile SingularAttribute<Member, Long> id;
	public static volatile SingularAttribute<Member, String> name;
	public static volatile SingularAttribute<Member, String> email;
	public static volatile SingularAttribute<Member, String> phoneNumber;
}
