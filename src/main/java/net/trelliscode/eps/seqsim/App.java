package net.trelliscode.eps.seqsim;

import net.ogalab.util.rand.RNG;
import cern.jet.random.engine.RandomEngine;
import cern.jet.random.Uniform;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import net.ogalab.util.os.FileIO;

/**
 * Hello world!
 *
 */
public class App 
{
    
    String sequence = null;
    StringBuilder mutatedSequence = null;
    
    char[] aa = {
    'A', 'R', 'N', 'D', 'C',
    'Q', 'E', 'G', 'H', 'I',
    'L', 'K', 'M', 'F', 'P',
    'S', 'T', 'W', 'Y', 'V'
    };
    
    public static void main( String[] args ) throws IOException
    {

        App obj = new App();
        PrintWriter pw= FileIO.getPrintWriter("result.tfa");
        
        for (int i=0; i<40; i++) { // => PAM400
            // obj.initSequence();
            obj.mutate(15); // PAM10
            pw.println("> mutatedSequence" + i);
            pw.println(obj.mutatedSequence);
            
            pw.println("\n\n");
        }
        pw.close();
        
    }
    
    
    public App() {
        this.initSequence();
    }
    
    final void initSequence() {

        // Sorry, it's hard coding.
        // >NP_005359.1 myoglobin isoform 1 [Homo sapiens]
        StringBuilder sb = new StringBuilder();
        sb.append("MVHLTPEEKSAVTALWGKVNVDEVGGEALGRLLVVYPWT");
        sb.append("QRFFESFGDLSTPDAVMGNPKVKAHGKKVLGAFSDGLAHL");
        sb.append("DNLKGTFATLSELHCDKLHVDPENFRLLGNVLVCVLAHHF");
        sb.append("GKEFTPPVQAAYQKVVAGVANALAHKYH");
        
        
        this.sequence = sb.toString();
        mutatedSequence = sb;
    }
    
    /** Point mutation.
     * 
     */
    public void mutate() {
        RandomEngine engine = RNG.getEngine();
        Uniform unif1 = new Uniform(0, this.sequence.length()-1, engine);
        
        int site = unif1.nextInt();
        
        Uniform unif2 = new Uniform(0, 19, engine);
        int aa = unif2.nextInt();
    
        this.mutatedSequence.setCharAt(site, this.aa[aa]);
    }
    
    
    public void mutate(int rep) {
        for (int i=0; i<rep; i++) {
            this.mutate();
        }
    }
    
}
