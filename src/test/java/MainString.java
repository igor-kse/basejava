public class MainString {
    public static void main( String[] args ) {
        String[] strArray = new String[]{"1", "2", "3", "4", "5"};
//        String result = "";
        StringBuilder sb = new StringBuilder();
        for ( String str : strArray ) {
            sb.append( str ).append( ", " );
        }
        System.out.println( sb );

        String str1 = "abc";
        String str2 = "c";
        System.out.println( str1 == ( "ab" + str2 ).intern() );
        System.out.println( str1.equals( "ab" + str2 ) );
    }
}
