/*Lomar Lilly 1401375
 * Darryl Brown 1503803
 */

package classes;
//This whole class just redirects the system out console display to a text field

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class CustomOutputStream extends OutputStream
{
    
    private JTextArea textArea;
    
    public CustomOutputStream(JTextArea textArea)
    {
        this.textArea = textArea;
    }
    
     @Override
    public void write(int b) throws IOException 
    {
        textArea.append(String.valueOf((char)b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
