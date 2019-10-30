package nl.kvk.np.semantics;

import nl.kvk.np.Constants;

import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface OperatieOpEntiteitClass {
    public Class<?>[] BeschrijftAanvangVan() default Constants.class;
    public Class<? extends Enum>[] BeschrijftAanvangVan_E() default Enum.class;
    public Class<?>[] BeschrijftBeeindigingVan() default Constants.class;
    public Class<? extends Enum>[] BeschrijftBeeindigingVan_E() default Enum.class;
    public Class<?>[] Wijzigt() default Constants.class;
    public Class<? extends Enum>[] Wijzigt_E() default Enum.class;
    public Class<?>[] Bevat() default Constants.class;
    public Class<? extends Enum>[] Bevat_E() default Enum.class;
    public Class<?>[] Betreft() default Constants.class;
    public Class<? extends Enum>[] Betreft_E() default Enum.class;
    public Class<?>[] TypeWijziging() default Constants.class;
    public Class<? extends Enum>[] TypeWijziging_E() default Enum.class;
}
