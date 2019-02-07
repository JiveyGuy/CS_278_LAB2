import java.util.Scanner;

class Task2{
  public static void main(String args[]){
    Part2.model(Integer.parseInt( args[0] ));
  }
}

class Part2{
  public static boolean[] P;
  public static boolean[][] Q;
  public static int iterations;
  public static int len;
  public static final boolean DEBUG = false;
  
  public static void debug(String...input){
    if(!DEBUG) return;
    for( String itor : input )
      System.out.println( itor );
  }
  
  public static void model(int len){
   boolean[][] allModels = generate( (int)Math.pow(2, len) - 1);
   int size = allModels.length;
   int iterations = (int)Math.pow(2, len);
   len = len;
   System.out.println( );
   for(int x = 0; x < size; x++){
     P = allModels[x];
     
     for(int y = 0; y < size; y++){
       for(int z = 0; z < size; z++){
         Q = new boolean[][]{ allModels[y] , allModels[z] };
         if( W() )  isModel(x,y,z);
         else isNotModel(x,y,z);
       }
     }
   }
  }
  
  public static void isModel( int p, int qx, int qy ){
    System.out.println("Model found at P["+p+"] Q["+qx+"]["+qy+"]: \n\tP=");
    System.out.print("\t\t");
    for(boolean itor : P)
      System.out.print( itor ? "1 " : "0 ");
    System.out.print("\n\tQ = \n");
    for(boolean[] itor : Q){
      System.out.print("\t\t");
      for(boolean itor2 : itor){
        System.out.print( itor2 ? "1 " : "0 ");
      }
      System.out.println();
    }
  }
  
  public static void isNotModel( int p, int qx, int qy ){
    System.out.println("Counter model found at P["+p+"] Q["+qx+"]["+qy+"]: \n\tP=");
    System.out.print("\t\t");
    for(boolean itor : P)
      System.out.print( itor ? "1 " : "0 ");
    System.out.print("\n\tQ = \n");
    for(boolean[] itor : Q){
      System.out.print("\t\t");
      for(boolean itor2 : itor){
        System.out.print( itor2 ? "1 " : "0 ");
      }
      System.out.println();
    }
  }
  
  public static boolean W(){
    return imply( f1(), f2() );
  }
  
  public static boolean f1(){
    return f3() && f4();
  }
  
  public static boolean f2(){
    for( int x = 0; x < len; x++)
      if( !f5(x) ) return false;
    return true;
  }
  
  public static boolean f3(){
    for( int x = 0; x < len; x++)
      if( !P(x)) return false;
    return true;
  }
  
  public static boolean f4(){
    for( int x = 0; x < len; x++)
      if( !Q( x, x) ) return true;
    return false;
  }
  
  public static boolean f5(int x){
    return imply( P(x) , f6(x) );
  }
  
  public static boolean f6(int x){
    for( int y = 0; y < len; y++)
     if( !f7( x, y )) return false;

    return true;
  }
  
  public static boolean f7( int x, int y ){
    return imply( Q(x, y) , Q(y, x) );
  }
  
  public static boolean imply( boolean a, boolean b){
    return !a || b; 
  }
  
  public static boolean P( int x ){
    return P[ x ];
  }
  
  public static boolean Q( int x, int y ){
    return Q[ x][y ];
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
  
  public static void printModel( boolean[] a ){
    for(boolean itor : a)
      System.out.print( (( itor ) ? "1" : "0" ) +" ");
  }
}

class Part1{
  public static boolean[] p;
  public static boolean[] q;
  
  public static void model( int numOfModels ){
    boolean [][] allModels = generate( numOfModels );
    for( int p_count = 0; p_count < allModels.length; p_count++){
      p = allModels[ p_count ];
      for( int q_count = 0; q_count < allModels.length; q_count++){
        q = allModels[ q_count ];
        if( W() ) {
          System.out.print( "model found at ("+p_count+","+q_count+"):\np =\t");
          for(boolean itor : p)
            System.out.print( itor ? "0 " : "1 ");
          System.out.print("\nq =\t");
          for( boolean itor : q )
            System.out.print( itor ? "0 " : "1 " );
          System.out.println();
        } else {
          System.out.print( "counter model found at ("+p_count+","+q_count+"):\np =\t");
          for(boolean itor : p)
            System.out.print( itor ? "0 " : "1 ");
          System.out.print("\nq =\t");
          for( boolean itor : q )
            System.out.print( itor ? "0 " : "1 " );
          System.out.println(); 
        }
      }
    }
  }
  
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
    for( int x = 0; x < q.length; x++){
      result = result && q( x );
    }
    return result;
  }
  
  public static boolean f5(int x){
    return imply( p(x) , q(x) );
  }
}
