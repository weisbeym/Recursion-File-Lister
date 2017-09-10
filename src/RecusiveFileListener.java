
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;


public class RecusiveFileListener extends JFrame {
    
    private JPanel main, body, button;
    private JButton startBtn, quitBtn;
    private JScrollPane scroll;
    private static JTextArea textArea = new JTextArea();;
    private JList list = new JList();
    
    public RecusiveFileListener() {
        super("Recursion GUI");
        main = new JPanel();
        main.setLayout(new BorderLayout());
        bodyPanel();
        buttonPanel();
        main.add(body,BorderLayout.CENTER);
        main.add(button,BorderLayout.SOUTH);
        
        add(main);
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }
    
    public static void displayDirectoryContents(File dir) {
        try {
                textArea.append("\n");
                File[] files = dir.listFiles();
                for (File file : files) {
                        if (file.isDirectory()) {
                                textArea.append("directory:" + file.getCanonicalPath());
                                displayDirectoryContents(file);
                        } else {
                                textArea.append("     file:" + file.getCanonicalPath());
                        }
                }
        } catch (IOException e) {
                e.printStackTrace();
        }
	
    }
    
    public void buttonPanel(){
        button = new JPanel();
        button.setLayout(new GridLayout(1,2));
        startBtn = new JButton("start");
        quitBtn = new JButton("quit");
    
        startBtn.addActionListener((ActionEvent ae) ->{
            final JFileChooser chooser = new JFileChooser() {
            @Override
            public void approveSelection() {
                if (getSelectedFile().isFile()) {
                    return;
                } else
                    super.approveSelection();
                }
             };
            chooser.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES);
            File file = chooser.getCurrentDirectory();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.showOpenDialog(null);
            displayDirectoryContents(file);
        }) ;
        
        quitBtn.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
        
        button.add(startBtn);
        button.add(quitBtn);
    }
    
    public void bodyPanel() {
        body = new JPanel();
        body.setLayout(new BorderLayout());
        scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        body.add(scroll, BorderLayout.CENTER);
    }
    
    
     public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new RecusiveFileListener().setVisible(true);
        });
    }
}
