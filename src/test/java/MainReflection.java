import edu.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main( String[] args ) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible( true );
        System.out.println( field.getName() );
        System.out.println( field.get( r ) );
        field.set( r, "new_uuid" );
        System.out.println( r );

        System.out.println( "\ntoString invoking" );
        Method method = r.getClass().getMethod( "toString" );
        System.out.println( method.invoke( r ) );
    }
}
