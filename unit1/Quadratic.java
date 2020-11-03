package unit1;

/**  
 * This program will compute and print one of the solutions
 * to an equation of the form A*X*X + B*X + C = 0, where
 * A, B, and C are numbers entered by the user.  It depends
 * on the non-standard class TextIO for doing input/output.
 */
public class Quadratic {

   public static void main(String[] args) {
     
      double A, B, C;   // Coefficients in the equation.
      double solution;  // The solution computed for the equation.
      boolean goAgain;  // Set to true if the user wants to
                        //   solve another equation.
                       
      TextIO.putln("This program will print a solution of an equation");
      TextIO.putln("of the form A*X*X + B*X + C = 0, where A, B, and");
      TextIO.putln("C are values that you enter.");
     
      do {
        
         /* Get the coefficients from the user. */

         TextIO.putln();
         TextIO.putln("Enter values for A, B, and C:");
         TextIO.put("A = ");
         A = TextIO.getlnDouble();
         TextIO.put("B = ");
         B = TextIO.getlnDouble();
         TextIO.put("C = ");
         C = TextIO.getlnDouble();
         TextIO.putln();
        
         /* Print the solution, or an error message, if
            there is no solution. */

         try {
            solution = root(A,B,C);
            TextIO.putln("A solution of the equation is " + solution); 
         }
         catch (IllegalArgumentException e) {
            TextIO.putln("Sorry, I can't find a solution.");
            TextIO.putln(e.getMessage());
         }
         
         /* Find out whether the user wants to go again. */

         TextIO.putln();
         TextIO.put("Do you want to solve another equation? ");
         goAgain = TextIO.getlnBoolean();

      } while (goAgain);
  
   } // end main
  
 
   /**
    * Returns the larger of the two roots of the quadratic equation
    * A*x*x + B*x + C = 0, provided it has any roots.  If A == 0 or
    * if the discriminant, B*B - 4*A*C, is negative, then an exception
    * of type IllegalArgumentException is thrown.
    */
   static public double root( double A, double B, double C )
                                 throws IllegalArgumentException {
       if (A == 0) {
         throw new IllegalArgumentException("A can't be zero.");
       }
       else {
          double disc = B*B - 4*A*C;
          if (disc < 0)
             throw new IllegalArgumentException("Discriminant < zero.");
          return  (-B + Math.sqrt(disc)) / (2*A);
       }
   }  

}  // end class
