package nl.kvk.np.semantics;

import nl.kvk.np.Constants;

import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface OperatieOpEntiteit {
    public String BeschrijftAanvangVan() default Constants.UNASSIGNED;
    public String BeschrijftBeeindigingVan() default Constants.UNASSIGNED;
    public String Wijzigt() default Constants.UNASSIGNED;
    public String Bevat() default Constants.UNASSIGNED;
    public String Betreft() default Constants.UNASSIGNED;
    public String TypeWijziging() default Constants.UNASSIGNED;
}
