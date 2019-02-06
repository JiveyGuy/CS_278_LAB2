class Task2{
  
  public static boolean[] p;
  public static boolean[] q;
  
  public static void main(String args[]){
    model( args[0] );
  }
  
  public static void model( int numOfModels ){
    boolean [][] allModels = generate( numOfModels );
    for( int p_count = 0; p_count < allModels.length; p_count++){
      p = allModels[ p_count ];
      for( int q_count = 0; q_count < allModels.length; q_count++){
        q = allModels[ q_count ];
        if( W() ) {
          System.out.print( "model found at ("+p_count+","+q_count+"):\np =\t");
          for(boolean itor : p)
            System.out.print( itor ? "0" : "1");
          System.out.print("\nq =\t");
          for( boolean itor : q )
            System.out.print( itor ? "0" : "1" );
          System.out.println();
        } 
      }
    }
  }
//  
//  public static boolean[] counterModel( int models ){
//    
//  }
  
  public static void printArray( boolean[][] in ){
    for( boolean[] itor : in ){
      for( boolean val : itor){
        System.out.print( (val ? 1 : 0) + " " );
      }
      System.out.println();
    }
  }
  
  public static boolean[][] generate( int models ){
    int exponent = Integer.toBinaryString( models ).length();
    boolean[][] definitions = new boolean[(int)Math.pow(2, exponent)][exponent];
    for( int count = 0; count < definitions.length; count++){
      
      char[] countBin = Integer.toBinaryString( count ).toCharArray();

      for( int y = 0; y < countBin.length; y++ ){
        definitions[ count ][ exponent - 1 - y] = countBin[ countBin.length - y - 1] == 49;
      }
      
    }
    return definitions;
  }
  
  public static boolean p( int x ){
    return p[ x ]; 
  }
  
  public static boolean q( int x){
    return q[ x ]; 
  }
  
  public static boolean imply( boolean a, boolean b){
    return !a || b; 
  }
  
  public static boolean W(){
    return imply( f1() , f2() );
  }
  public static boolean f1(){
    return imply( f2() , f3() );
  }
  
  public static boolean f2(){
    boolean result = true;
    for( int x = 0; x < p.length; x++){
      result = result && f5( x );
    }
    return result;
  }
  
  public static boolean f3(){
    boolean result = true;
    for( int x = 0; x < p.length; x++){
      result = result && p( x );
    }
    return result;
  }
  
  public static boolean f4(){
    boolean result = true;
    for( int x = 0; x < p.length; x++){
      result = result && q( x );
    }
    return result;
  }
  
  public static boolean f5(int x){
    return imply( p(x) , q(x) );
  }
}
