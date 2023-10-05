package TextEditor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class TextEditor extends JFrame implements ActionListener{
	JMenuBar menu=new JMenuBar();
	JMenu file=new JMenu("file");
	JMenu edit=new JMenu("edit");
	JMenu saveandsubmit=new JMenu("save and submit");
	

	JMenuItem newfile=new JMenuItem("new");
	JMenuItem openfile=new JMenuItem("open"); 
	JMenuItem savefile=new JMenuItem("save"); 
	JMenuItem printfile=new JMenuItem("print"); 
	JMenuItem exit=new JMenuItem("exit"); 
	
	JMenuItem cut=new JMenuItem("cut");
	JMenuItem copy=new JMenuItem("copy"); 
	JMenuItem paste=new JMenuItem("paste"); 
	JMenuItem selectall=new JMenuItem("select all"); 
	
	JMenuItem sus=new JMenuItem("Save and Exit"); 
	
	JTextArea textarea=new JTextArea();
	
	TextEditor(){
		setTitle("Text Editor");
		setBounds(100,100,800,600); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menu);
		menu.add(file);
		menu.add(edit);
		menu.add(saveandsubmit);
		
		file.add(newfile);
		file.add(openfile);
		file.add(savefile);
		file.add(printfile);
		file.add(exit);
		
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(selectall);
		
		saveandsubmit.add(sus);
		
		JScrollPane scrollpane=new JScrollPane(textarea);
		add(scrollpane);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setBorder(BorderFactory.createEmptyBorder());
		textarea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		newfile.addActionListener(this);
		openfile.addActionListener(this);
		savefile.addActionListener(this);
		printfile.addActionListener(this);	
		exit.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		selectall.addActionListener(this);
		sus.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("new")){
			textarea.setText(null);
		} 
		else if(e.getActionCommand().equalsIgnoreCase("open")){
			JFileChooser filechooser=new JFileChooser();
			FileNameExtensionFilter filename= new FileNameExtensionFilter("Only text files.txt","txt");
			filechooser.setAcceptAllFileFilterUsed(true);
			filechooser.addChoosableFileFilter(filename);
			int action=filechooser.showOpenDialog(null);
			if(action!=JFileChooser.APPROVE_OPTION) {
				return;
			}
			else {
				try {
					BufferedReader reader=new BufferedReader(new FileReader(filechooser.getSelectedFile()));
					textarea.read(reader,null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(e.getActionCommand().equalsIgnoreCase("Print")){
			try {
				textarea.print();
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		else if(e.getActionCommand().equalsIgnoreCase("save")){
			JFileChooser filechooser=new JFileChooser();
			FileNameExtensionFilter filename= new FileNameExtensionFilter("Only text files.txt","txt");
			filechooser.setAcceptAllFileFilterUsed(true);
			filechooser.addChoosableFileFilter(filename);
			int action=filechooser.showSaveDialog(null);
			if(action!=JFileChooser.APPROVE_OPTION) {
				return;
			}
			else {
				String fileName=filechooser.getSelectedFile().getAbsolutePath().toString();
				if(!fileName.contains(".text")) {
					fileName+=".txt";
				}
				try {
					BufferedWriter writer=new BufferedWriter(new FileWriter(fileName));
					textarea.write(writer);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		else if(e.getActionCommand().equalsIgnoreCase("exit")){
			System.exit(0);
			
		}
		else if(e.getActionCommand().equalsIgnoreCase("cut")){
			textarea.cut();
		}
		else if(e.getActionCommand().equalsIgnoreCase("copy")){
			textarea.copy();
		}
		else if(e.getActionCommand().equalsIgnoreCase("paste")){
			textarea.paste();
		}
		
		else if(e.getActionCommand().equalsIgnoreCase("select all")){
			textarea.selectAll();
		}
		else if(e.getActionCommand().equalsIgnoreCase("save and Exit")){
			JFileChooser filechooser=new JFileChooser();
			FileNameExtensionFilter filename= new FileNameExtensionFilter("Only text files.txt","txt");
			filechooser.setAcceptAllFileFilterUsed(true);
			filechooser.addChoosableFileFilter(filename);
			int action=filechooser.showSaveDialog(null);
			if(action!=JFileChooser.APPROVE_OPTION) {
				return;
			}
			else {
				String fileName=filechooser.getSelectedFile().getAbsolutePath().toString();
				if(!fileName.contains(".text")) {
					fileName+=".txt";
				}
				try {
					BufferedWriter writer=new BufferedWriter(new FileWriter(fileName));
					textarea.write(writer);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			System.exit(0);
		}
		
		
	}
	public static void main(String[]args)throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new TextEditor().setVisible(true);
		
		
	}
}