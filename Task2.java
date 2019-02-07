//Jason ivey 2019
import java.util.Scanner;
import java.util.ArrayList;

class Task2{

  /*
    main brings the seperate part1 and part2 class together and helps with user input for dynamic output.
    @param args command line args not used here  
  */
  public static void main(String args[]){

    Scanner input = new Scanner( System.in );

    System.out.print( "TASK 2 CS 278 LAB 2 JASON IVEY\nFor part one input the value x so that this program will develop all posible models and counter models in the range [0, 2^x]"
      +"\n(WARNING the number of outputs increases exponentially with x, values greater than 4 can result in console spam._\nx: " );
    int len = input.nextInt();
    System.out.println("\n---------------PART1---------------");
    Part1.model( len );

    System.out.print( "For part two input the value x so that this program will develop all posible models and counter models in the range [0, 2^x]"
      +"\n(WARNING: this program only works over the domain 0, 2)\nx: " );
    len = input.nextInt();
    System.out.println("\n---------------PART2---------------");
    Part2.model( len );

  }
}

class Part2{

  public static boolean[] P;
  public static boolean[][] Q;

  /*
  model finds all models and counter models
  @param len the value of x for 2^x 
  */
  public static void model(int len){ 
   boolean[][] allModels = generate( (int)Math.pow(2, len) - 1);
   int size = allModels.length;

   System.out.println( );
   for(int x = 0; x < size; x++){
     P = allModels[x];
    
     for(int y = 0; y < size; y++){
       for(int z = 0; z < size; z++){
         Q = new boolean[][]{ allModels[y] , allModels[z] }; //Does not work for values > 2, once I make an improved generate this can be resolved
        if( W() ){
          isModel(x,y,z);
        } else {
          isNotModel(x,y,z);
        }
       }
     }
   }
  }
  /*
  isModel prints result for case where W() == true
  @param p location this set in p
  @param qx x index of this set in q
  @param qy y index of this set in q
  */
  public static void isModel( int p, int qx, int qy ){
    System.out.print("Model found at P["+p+"] Q["+qx+"]["+qy+"]: \n\tP = ");
    for(boolean itor : P)
      System.out.print( itor ? "1 " : "0 ");
    System.out.print("\n\tQ = ");
    for(boolean[] itor : Q){
      for(boolean itor2 : itor){

        System.out.print( itor2 ? "1 " : "0 ");
      }
    }
    System.out.println();
  }
  
  /*
  isNotModel prints result for case where W() == false
  @param p location this set in p
  @param qx x index of this set in q
  @param qy y index of this set in q
  */
  public static void isNotModel( int p, int qx, int qy ){
    System.out.print("Counter model found at P["+p+"] Q["+qx+"]["+qy+"]: \n\tP = ");
    for(boolean itor : P)
      System.out.print( itor ? "1 " : "0 ");
    System.out.print("\n\tQ = ");
    for(boolean[] itor : Q){
      for(boolean itor2 : itor){
        System.out.print( itor2 ? "1 " : "0 ");
      }
    }
    System.out.println();
  }
  
  public static boolean W(){
    return imply( f1(), f2() );
  }
  
  public static boolean f1(){
    return f3() && f4();
  }
  
  public static boolean f2(){
    for( int x = 0; x < P.length; x++)
      if( !f5(x) ) return false;
    return true;
  }
  
  public static boolean f3(){
    for( int x = 0; x < P.length; x++)
      if( !p(x) ) return false;
    return true;
  }
  
  public static boolean f4(){
    for( int x = 0; x < P.length; x++)
      if( !q( x, x) ) return false;
    return true;
  }
  
  public static boolean f5(int x){
    return imply( p(x) , f6(x) );
  }
  
  public static boolean f6(int x){
    for( int y = 0; y < P.length; y++){
     if( !f7( x, y )){
        return false;
      }
    }

    return true;
  }
  
  public static boolean f7( int x, int y ){
    return imply( q(x, y) , q(y, x) );
  }
  
  /*
   * Helper imply function
   */
  public static boolean imply( boolean a, boolean b){
    return !a || b; 
  }
  
  public static boolean p( int x ){
    return P[ x ];
  }
  
  public static boolean q( int x, int y ){
    return Q[ x][y ];
  }
  

  /*
   * generates all posible combinations of booleans for an array width
   */
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
  
}

class Part1{
  public static boolean[] p;
  public static boolean[] q;
  
  /*
  model finds all models and counter models
  @param numOfModels the value of x for 2^x 
  */
  public static void model( int numOfModels ){
    boolean [][] allModels = generate( numOfModels );
    for( int p_count = 0; p_count < allModels.length; p_count++){
      p = allModels[ p_count ];
      for( int q_count = 0; q_count < allModels.length; q_count++){
        q = allModels[ q_count ];
        if( W() ) {
          System.out.print( "model found at ("+p_count+","+q_count+"):\np =\t");
          for(boolean itor : p)
            System.out.print( itor ? "1 " : "0 ");
          System.out.print("\nq =\t");
          for( boolean itor : q )
            System.out.print( itor ? "1 " : "0 " );
          System.out.println();
        } else {
          System.out.print( "counter model found at ("+p_count+","+q_count+"):\np =\t");
          for(boolean itor : p)
            System.out.print( itor ? "1 " : "0 ");
          System.out.print("\nq =\t");
          for( boolean itor : q )
            System.out.print( itor ? "1 " : "0 " );
          System.out.println(); 
        }
      }
    }
  }
  
  /*
   * generates all posible combinations of booleans for an array width
   */
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
