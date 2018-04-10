import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.io.*;

public class TextEditor{
    public static JFrame frame;
    public static JTextArea area;
    public static JMenuBar mbar;
    public static JMenu fmenu;
    public static JMenuItem new_file,open,save,saveas,quit;
    public static String current_file;

    public TextEditor(){
        //INITIALIZE COMPONENTS
        frame = new JFrame("text-editor");
        area = new JTextArea();
        mbar = new JMenuBar();
        fmenu = new JMenu("File");
        //MENU BUTTON FOR CREATING A NEW DOCUMENT
        new_file = new JMenuItem(new AbstractAction("New"){
            public void actionPerformed(ActionEvent ae){
                frame.setTitle("text-editor");
                current_file = null;
                area.setText(null);                                
            }
        });
        //MENU BUTTON FOR OPENING EXISTING FILE
        open = new JMenuItem(new AbstractAction("Open"){
            public void actionPerformed(ActionEvent ae){
                open_file();                             
            }
        });
        //MENU BUTTON FOR SAVING OVER EXISTING FILE
        save = new JMenuItem(new AbstractAction("Save"){ //Cancel THROWS NULL-POINTER EXCEPTION
            public void actionPerformed(ActionEvent ae){
                if (current_file == null) save_as(); //FOR SAVING A NEW TEXT FILE
                try {
                    FileWriter writer = new FileWriter(new File(current_file));
                    area.write(writer);  
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }                                
            }
        });
        //MENU BUTTON FOR SAVING A NEW FILE
        saveas = new JMenuItem(new AbstractAction("Save As"){
            public void actionPerformed(ActionEvent ae){
                save_as();  
            }            
        });
        //MENU BUTTON FOR EXITING THE EDITOR
        quit = new JMenuItem(new AbstractAction("Quit"){
            public void actionPerformed(ActionEvent ae){
                System.exit(0);                               
            }
        });               
        current_file = null;
        //ADD COMPONENTS
        area.setLineWrap(true);
        fmenu.add(new_file);
        fmenu.add(open);  
        fmenu.add(save);
        fmenu.add(saveas);
        fmenu.add(quit);
        mbar.add(fmenu);
        frame.setJMenuBar(mbar);
        frame.getContentPane().add(new JScrollPane(area));
        frame.setSize(400,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                 
    }
    //FUNCTION FOR SAVING A NEW FILE
    public static void save_as(){
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int val = chooser.showOpenDialog(null);
        if (val == JFileChooser.APPROVE_OPTION){
            current_file = chooser.getSelectedFile().getAbsolutePath();                                
            try {
                FileWriter writer = new FileWriter(new File(current_file));
                area.write(writer);  
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }        
    }
    //FUNCTION FOR OPENING A EXISTING FILE        
    public static void open_file(){
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int val = chooser.showOpenDialog(null);
        if (val == JFileChooser.APPROVE_OPTION){
            File loaded_file = chooser.getSelectedFile();
            current_file = loaded_file.getAbsolutePath();
            String line = null;
            try {
                FileReader fread = new FileReader(loaded_file.getAbsolutePath());
                BufferedReader bread = new BufferedReader(fread);
                area.setText(null);
                while((line = bread.readLine()) != null) area.append(line + '\n');
                bread.close();
                frame.setTitle("text-editor: " + current_file);
            }
            catch (FileNotFoundException ef){
                System.out.println("ERROR!");
            }
            catch (IOException ei){
                System.out.println("ERROR!");
            }                    
        }
    }
}
