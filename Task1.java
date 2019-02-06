public class Task1{
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
         System.out.println( out );
      }
   }
   public static boolean imply( boolean p, boolean q){
      return (!p|(p&q));
   }
   public static boolean iff( boolean p, boolean q){
     return (p&&q) || (!p&&!q);
   }
}
