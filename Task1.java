//Jason Ivey 2019

public class Task1{
   /**
    main executes all major logic and itoration through manual boolean computations for each value 1 or 0 which are mapped booleans
    @param args command line args not used here  
    */
   public static void main(String args[]){
      System.out.println("(~ S -> R ) /\\ ( W \\/ ~ R) /\\ (M -> ~ G) /\\ (G <-> S) /\\ (W -> S/\\ M) /\\ (S -> W)");
      for( int G = 0; G <= 1; G++ )
      for( int M = 0; M <= 1; M++ )
      for( int R = 0; R <= 1; R++ )
      for( int S = 0; S <= 1; S++ )
      for( int W = 0; W <= 1; W++ ){
         boolean g=(G == 1),m=(M==1),r=(R==1),s=(S==1),w=(W==1);
         String out = (" "+!s+" "+s+"  "+ imply( !s, r) +" "+r+"    "+ ( imply(!s, r) && ( w || !r ) ) + "   " + w + "  " + ( w || !r ) +
         " " + !r + " " + r + "   " + ( ( imply(!s, r) && ( w || !r ) ) && (imply(m, !g)) ) + "  " + m + " " + imply( m, !g ) + "  " +
         !g + " " + g + "   " + (( ( imply(!s, r) && ( w || !r ) ) && (imply(m, !g)) ) && iff(g,s)) + "  " + g + "  " + iff(g,s) + "  " + s +
         "   "+ (((( imply(!s, r) && ( w || !r ) ) && (imply(m, !g))) && iff(g,s)) && imply(w, s&&m ))+"  " +w+"  "+imply(w,s&&m)+" "+s+" "+ (s&&m) +" "+m+"   "+
              ((((( imply(!s, r) && ( w || !r ) ) && (imply(m, !g))) && iff(g,s)) && imply(w, s&&m ))&&imply(s,w))+"  "+s+"  "+imply(s,w)+" "+w );
         out = out.replaceAll("true","1").replaceAll("false","0");
         System.out.println( out ); // That concludes the ugliest yet most powerful line of java I have ever written. 
      }
   }

   /**
    imply through truth table we can see that the only true values are for p = false, inclusive or p and q
    @param p first arg of imply gets negated
    @param q  second arg needed for and
    @returns result of p -> q
   */
   public static boolean imply( boolean p, boolean q){
      return (!p|(p&q));
   }

   /**
    iff if and only if method used to shorten the already long statement above
    @param p  boolean arg order does not matter
    @param q  boolean arg order does not matter
    @returns result of p -> q
   */
   public static boolean iff( boolean p, boolean q){
     return (p&&q) || (!p&&!q);
   }
}
