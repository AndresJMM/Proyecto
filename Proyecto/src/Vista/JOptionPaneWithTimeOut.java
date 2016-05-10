package Vista;

import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandra Oteiza
 */
public class JOptionPaneWithTimeOut{
    
    private static JOptionPane option;
    private static JDialog dialogo = null;

    /**
     * Solo hace caso a padre la primera vez que se llama a este método. La
     * llamada a este metodo se queda bloqueada hasta que el usuario cierra el
     * JOptionPane o pasa el timeout.
     * 
     * @param padre
     * @param texto
     * @param titulo
     * @param timeout
     *            En mili segundos
     */
    public static void showDialog(Component padre, String texto, String titulo, final long timeout){
        option = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE);
        option.setMessage(texto);
        if(dialogo == null){
            dialogo = option.createDialog(padre, titulo);
        }
        else{
            dialogo.setTitle(titulo);
        }

        Thread hilo = new Thread(){
            public void run(){
                try{
                    Thread.sleep(timeout);
                    if( dialogo.isVisible() ){
                        dialogo.setVisible(false);
                        //dialogo.dispose();
                    }
                }
                catch ( InterruptedException e ){
                    e.printStackTrace();
                }
            }
        };
        
        hilo.start();

        dialogo.setVisible(true);   
    }
    
}
