package vues;



import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import Utils.MonDevis;
import services.DevisServiceImpl;
import javax.swing.ImageIcon;
import java.awt.Font;

public class ListDevisUi extends JFrame implements MouseListener,ActionListener{


	private JTable table;

	JLabel idSelected;
	JButton btnDetails,btnDelete,btnBack;
	DefaultTableModel model;
	private Dashboard dash;

		public ListDevisUi(Dashboard dash) {
		this.dash = dash;
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ListDevisUi.class.getResource("/Gambar/dragon.png")));
		this.setTitle("Liste Devis");
		this.setBounds(100, 100, 913, 621);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		this.getContentPane().add(topPanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("List Devis");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel.setIcon(new ImageIcon(ListDevisUi.class.getResource("/Gambar/completed-task.png")));
		topPanel.add(lblNewLabel);
		
		JPanel buttonPanel = new JPanel();
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		idSelected = new JLabel("0");
		idSelected.setVisible(false);
		buttonPanel.add(idSelected);
		
		btnDetails = new JButton("");
		btnDetails.addActionListener(this);
		btnDetails.setIcon(new ImageIcon(ListDevisUi.class.getResource("/Gambar/employee.png")));
		buttonPanel.add(btnDetails);
		
		btnDelete = new JButton("");
		btnDelete.addActionListener(this);
		btnDelete.setIcon(new ImageIcon(ListDevisUi.class.getResource("/Gambar/delete.png")));
		buttonPanel.add(btnDelete);
		
		btnBack = new JButton("");
		btnBack.addActionListener(this);
		btnBack.setIcon(new ImageIcon(ListDevisUi.class.getResource("/Gambar/reply-message.png")));
		buttonPanel.add(btnBack);
		
		JPanel corsePanel = new JPanel();
		this.getContentPane().add(corsePanel, BorderLayout.CENTER);
		
		
		
		model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Code");
        model.addColumn("Client");
        model.addColumn("Date");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYY, HH:mm");
        
        DevisServiceImpl devisServ = new DevisServiceImpl();
        for(int i=0 ; i<devisServ.getAllDevis().size(); i++) {
        	 model.addRow(new String[] {
        			 Integer.toString(devisServ.getAllDevis().get(i).getId()),
        			 devisServ.getAllDevis().get(i).getCode(),
        			 devisServ.getAllDevis().get(i).getClient().getRaisonSocial().getNom()+" "+devisServ.getAllDevis().get(i).getClient().getRaisonSocial().getPrenom(),
        			 dateFormat.format(devisServ.getAllDevis().get(i).getDate())
        			 
        			 });
        }
		
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(model);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		corsePanel.add(scrollPane);
	
		
		}


		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(table)) {
				int row = table.getSelectedRow();
		        int column = table.getColumnCount();
		        Object[] val = new Object[column];
		        for(int i = 0; i < column; i++) {
		            val[i]=table.getValueAt(row, i);
		        }
		        
		        idSelected.setText((String)val[0]);
			}
			
		}


		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnDetails)) {
				MonDevis pdfdevis = new MonDevis(Integer.parseInt(idSelected.getText()));
				
				 if (Desktop.isDesktopSupported()) {
		             try {
		            	 String path = this.getClass().getClassLoader().getResource("").getPath();
		            	    String fullPath = URLDecoder.decode(path, "UTF-8");
		            	    String pathArr[] = fullPath.split("bin/");
		            	    System.out.println(fullPath);
		            	    System.out.println(pathArr[0]);
		            	    fullPath = pathArr[0];

		                    
		                    File myFile = new File(fullPath+"devis.pdf");
		                    Desktop.getDesktop().open(myFile);
		            } catch (IOException ex) {
		                        // no application registered for PDFs
		                }
		            }
			}
			
			if(e.getSource().equals(btnDelete)) {
				DevisServiceImpl devisService = new DevisServiceImpl();
				devisService.deleteDevis(Integer.parseInt(idSelected.getText()));
				model.fireTableDataChanged();
			}
			
			if(e.getSource().equals(btnBack)) {
				this.dispose();
				dash.setVisible(true);
				
			}
			
		}

}
