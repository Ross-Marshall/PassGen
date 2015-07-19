public class PassGen {

    public static final int LETTER_MAX  = 26;
    public static final int NUMBER_MAX  = 10;
    public static final int SPECIAL_MAX = 13;

    public static final int FIRST_CAP   = 65;
    public static final int FIRST_LOW   = 97;
    public static final int FIRST_NUM   = 48;
    public static final int FIRST_SPECIAL  = 33;
    public static final String USE_SYMBOLS = "USE_SYMBOLS";

    public int pwLength;

    char getChar( char [] array ) {
        return array[ (int)(Math.random() * array.length) ];
    }

    char [] generate( char [] array ) {
        int index = 0;
	char ch;
        char [] passwd = new char[ pwLength ];
	for ( int i = 0 ; i < pwLength ; i++ ) {
            ch = getChar( array );
	    while ( inArray( ch, passwd ) ) {
               ch = getChar( array );
	    }
            passwd[ index++ ] = ch;
        }
	return passwd;
    }

    void setArgs( String [] args ) {
        System.out.println( "args.length = " + args.length );
	for ( int i = 0 ; i < args.length ; i++ ) {
            System.out.println( "args[" + i + "] = " + args[i] );
        }
    }

    char [] populateArray( int length, int initValue ) {
	char [] array = new char[ length ];
	for ( int i = 0 ; i < length ; i++ ) {
	    array[ i ] = (char)(initValue + i);
        }
        return array;
    }

    boolean inArray ( char ch, char [] array ) {
//System.out.println( "Look for [" + ch + "] in the array..." );
//printArray( array );
       for ( int i = 0 ; i < array.length ; i++ ) {
           if ( array[i] == ch ) {
              return true;
           }
       }
       return false;
    }

    boolean inArray ( String string, String [] stringList ) {
       for ( int i = 0 ; i < stringList.length ; i++ ) {
           if ( stringList[i].toUpperCase().equals( string ) ) return true;
       }
       return false;
    }

    char[] merge( char[]... arrays ) {   
        // calculate size of target array   
        int size = 0;   
        for (char [] array : arrays) {
           size += array.length;   
        }   

        char [ ] returnArray = new char[ size ];

        int index = 0;
        for ( int i = 0 ; i < arrays.length ; i++ ) {
            for ( int j = 0 ; j < arrays[i].length ; j++ ) {
		 returnArray[ index++ ] = arrays[i][ j ];
            }
        }
        //printArray( returnArray );
        return returnArray;
    } 

    void printArray( char [] array ) {
        for ( int i = 0 ; i < array.length ; i++ ) {
	    System.out.println( "array[ " + i + "] = [ " + array[ i ] + " ]");
        }
    }

    public static void main (String [] args) {
        if ( args.length == 0 ) {
           System.out.println( "Usage: java PassGen length [use_symbols]" );
	   return;
        }
        //System.out.println( "Random = " + (int)(Math.random()*5 ) );
        PassGen pg = new PassGen();
	//pg.setArgs( args );

        pg.pwLength = Integer.valueOf( args[0] );
	//System.out.println( "pwLength = " + pg.pwLength );

	char [] caps = new char[ LETTER_MAX ];
	char [] lows = new char[ LETTER_MAX ];
	char [] nums = new char[ NUMBER_MAX ];
        char [] specials = new char[ SPECIAL_MAX ];

	caps = pg.populateArray( LETTER_MAX, FIRST_CAP );
	//pg.printArray( caps );

	lows = pg.populateArray( LETTER_MAX, FIRST_LOW );
	//pg.printArray( lows );

	nums = pg.populateArray( NUMBER_MAX, FIRST_NUM );
	//pg.printArray( nums );

        specials = pg.populateArray( SPECIAL_MAX, FIRST_SPECIAL );
        //pg.printArray( specials );

	char [] pwChars = null;
	if( pg.inArray( USE_SYMBOLS, args ) ) {
           pwChars = pg.merge( caps, lows, nums, specials );
        } else {
           pwChars = pg.merge( caps, lows, nums );
        }

	//System.out.println( "Print pwChars" );
        //System.out.println( "pwChars.length = " + pwChars.length );
 
        System.out.println( pg.generate( pwChars ) );        

    }
}
