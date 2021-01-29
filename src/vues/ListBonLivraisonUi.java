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

import Toaster.Toaster;
import Utils.MonBonLivraison;
import Utils.MonDevis;
import services.BonLivraisionImpl;
import services.DevisServiceImpl;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;

public class ListBonLivraisonUi extends JFrame implements MouseListener,ActionListener{


	private JTable table;
	private final Toaster toaster;
	JLabel idSelected;
	JButton btnDetails,btnDelete,btnBack,btnSeekByCode;
	DefaultTableModel model;
	private Dashboard dash;
	private JTextField fieldSeek;
	private JPanel panel_1;
	private JButton btnRefresh;

		public ListBonLivraisonUi(Dashboard dash) {
		this.dash = dash;
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ListDevisUi.class.getResource("/Gambar/dragon.png")));
		this.setTitle("Liste Devis");
		this.setBounds(100, 100, 913, 621);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.ORANGE);
		this.getContentPane().add(topPanel, BorderLayout.NORTH);
		this.toaster = new Toaster((JPanel) this.getContentPane());
		JLabel lblNewLabel = new JLabel("Liste de Bon Livraison");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel.setIcon(new ImageIcon(ListDevisUi.class.getResource("/Gambar/completed-task.png")));
		topPanel.add(lblNewLabel);
		
		JPanel buttonPanel = new JPanel();
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		idSelected = new JLabel("0");
		idSelected.setVisible(false);
		buttonPanel.add(idSelected);
		
		btnDetails = new JButton("");
		btnDetails.setEnabled(false);
		btnDetails.addActionListener(this);
		btnDetails.setIcon(new ImageIcon(ListDevisUi.class.getResource("/Gambar/employee.png")));
		buttonPanel.add(btnDetails);
		
		btnDelete = new JButton("");
		btnDelete.setEnabled(false);
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
        
        BonLivraisionImpl bonLivraisonService = new BonLivraisionImpl();
        for(int i=0 ; i<bonLivraisonService.getAllBonLivraison().size(); i++) {
        	 model.addRow(new String[] {
        			 Integer.toString(bonLivraisonService.getAllBonLivraison().get(i).getId()),
        			 bonLivraisonService.getAllBonLivraison().get(i).getCode(),
        			 bonLivraisonService.getAllBonLivraison().get(i).getClient().getRaisonSocial().getNom()+" "+bonLivraisonService.getAllBonLivraison().get(i).getClient().getRaisonSocial().getPrenom(),
        			 dateFormat.format(bonLivraisonService.getAllBonLivraison().get(i).getDate())
        			 
        			 });
        }
		
		
		table = new JTable();
		table.setBackground(new Color(255, 255, 153));
		table.setFillsViewportHeight(true);
		table.addMouseListener(this);
		table.setModel(model);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		corsePanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnSeekByCode = new JButton("");
		btnSeekByCode.addActionListener(this);
		btnSeekByCode.setIcon(new ImageIcon(ListBonLivraisonUi.class.getResource("/Gambar/search (1).png")));
		panel.add(btnSeekByCode);
		
		fieldSeek = new JTextField();
		panel.add(fieldSeek);
		fieldSeek.setColumns(10);
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.EAST);
		
		btnRefresh = new JButton("");
		btnRefresh.addActionListener(this);
		btnRefresh.setIcon(new ImageIcon(ListBonLivraisonUi.class.getResource("/Gambar/refresh.png")));
		panel_1.add(btnRefresh);
	
		
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
		        if(Integer.parseInt(idSelected.getText()) > 0) {
		        	btnDetails.setEnabled(true);
		        	btnDelete.setEnabled(true);
		        }
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
				MonBonLivraison pdfBonLivraison = new MonBonLivraison(Integer.parseInt(idSelected.getText()));
				
				 if (Desktop.isDesktopSupported()) {
		             try {
		            	 String path = this.getClass().getClassLoader().getResource("").getPath();
		            	    String fullPath = URLDecoder.decode(path, "UTF-8");
		            	    String pathArr[] = fullPath.split("bin/");
		            	    System.out.println(fullPath);
		            	    System.out.println(pathArr[0]);
		            	    fullPath = pathArr[0];

		                    
		                    File myFile = new File(fullPath+"bon_livraison.pdf");
		                    Desktop.getDesktop().open(myFile);
		            } catch (IOException ex) {
		                        // no application registered for PDFs
		                }
		            }
			}
			
			if(e.getSource().equals(btnDelete)) {
				BonLivraisionImpl bonLivraisonService = new BonLivraisionImpl();
				bonLivraisonService.deleteBonLivraison(Integer.parseInt(idSelected.getText()));
				toaster.error("Bon livraison deleted successfully");
				((DefaultTableModel)table.getModel()).setNumRows(0);
				 SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYY, HH:mm");
				for(int i=0 ; i<bonLivraisonService.getAllBonLivraison().size(); i++) {
		        	 model.addRow(new String[] {
		        			 Integer.toString(bonLivraisonService.getAllBonLivraison().get(i).getId()),
		        			 bonLivraisonService.getAllBonLivraison().get(i).getCode(),
		        			 bonLivraisonService.getAllBonLivraison().get(i).getClient().getRaisonSocial().getNom()+" "+bonLivraisonService.getAllBonLivraison().get(i).getClient().getRaisonSocial().getPrenom(),
		        			 dateFormat.format(bonLivraisonService.getAllBonLivraison().get(i).getDate())
		        			 
		        			 });
		        }
				model.fireTableDataChanged();
				btnDelete.setEnabled(false);
			}
			
			if(e.getSource().equals(btnBack)) {
				this.dispose();
				dash.setVisible(true);
				
			}
			
			if(e.getSource().equals(btnSeekByCode)) {
				if(fieldSeek.getText().length() <= 13 && fieldSeek.getText().length()>=1) {
					BonLivraisionImpl bonLivraisonService = new BonLivraisionImpl();
					if(bonLivraisonService.getAllBonLivraisonSeekByCode(fieldSeek.getText()).size()<=0) {
					toaster.error("Element(s) not found");
					}
					else
					{
						toaster.success(bonLivraisonService.getAllBonLivraisonSeekByCode(fieldSeek.getText()).size()+"Element(s) found");
						((DefaultTableModel)table.getModel()).setNumRows(0);
						 SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYY, HH:mm");
						for(int i=0 ; i<bonLivraisonService.getAllBonLivraisonSeekByCode(fieldSeek.getText()).size(); i++) {
				        	 model.addRow(new String[] {
				        			 Integer.toString(bonLivraisonService.getAllBonLivraisonSeekByCode(fieldSeek.getText()).get(i).getId()),
				        			 bonLivraisonService.getAllBonLivraisonSeekByCode(fieldSeek.getText()).get(i).getCode(),
				        			 bonLivraisonService.getAllBonLivraisonSeekByCode(fieldSeek.getText()).get(i).getClient().getRaisonSocial().getNom()+" "+bonLivraisonService.getAllBonLivraisonSeekByCode(fieldSeek.getText()).get(i).getClient().getRaisonSocial().getPrenom(),
				        			 dateFormat.format(bonLivraisonService.getAllBonLivraisonSeekByCode(fieldSeek.getText()).get(i).getDate())
				        			 
				        			 });
				        }
						model.fireTableDataChanged();
					}
					
				}
				else
				{
					toaster.error("field search should not be emplty or 13 caracter max");
				}
			}
			if(e.getSource().equals(btnRefresh)) {
				
				BonLivraisionImpl bonLivraisonService = new BonLivraisionImpl();
				toaster.success("Liste Bon livraison Refreshed successfully");
				((DefaultTableModel)table.getModel()).setNumRows(0);
				 SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYY, HH:mm");
				for(int i=0 ; i<bonLivraisonService.getAllBonLivraison().size(); i++) {
		        	 model.addRow(new String[] {
		        			 Integer.toString(bonLivraisonService.getAllBonLivraison().get(i).getId()),
		        			 bonLivraisonService.getAllBonLivraison().get(i).getCode(),
		        			 bonLivraisonService.getAllBonLivraison().get(i).getClient().getRaisonSocial().getNom()+" "+bonLivraisonService.getAllBonLivraison().get(i).getClient().getRaisonSocial().getPrenom(),
		        			 dateFormat.format(bonLivraisonService.getAllBonLivraison().get(i).getDate())
		        			 
		        			 });
		        }
				model.fireTableDataChanged();
				
			}
			
			
		}

}
