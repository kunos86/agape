package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.ksb.agape.domain.model.dict.RoleEnum;

@Generated(value="Dali", date="2014-03-16T10:20:01.295+0100")
@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, Long> id;
	public static volatile SingularAttribute<Role, Date> modificationDate;
	public static volatile SingularAttribute<Role, RoleEnum> roleName;
	public static volatile SingularAttribute<Role, User> user;
}