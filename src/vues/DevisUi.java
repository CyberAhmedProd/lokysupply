package vues;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.FieldView;

import Toaster.Toaster;
import models.Adress;
import models.Client;
import models.CompteBancaire;
import models.Fournisseur;
import models.Product;
import models.RaisonSocial;
import models.TypeEntreprise;
import services.ClientServiceImpl;
import services.DevisServiceImpl;
import services.FournisseurServiceImpl;
import services.ProductServiceImpl;

import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;
import models.Ville;

public class DevisUi extends JFrame implements ActionListener,MouseListener  {
	private final Toaster toaster;
	private JPanel contentPane;
	private JTable table;
	private JButton addDevisValider,printBtn,btnBackToDash,seekClientButton,btnSeekProduct,addToLigne,deleteFromLigne;
	private JTextField fieldMatricule;
	private JTextField fieldName;
	private JTextField fieldLastName;
	private JTextField fieldTel;
	private JTextField fieldRef;
	private JTextField fieldDesignation;
	private JTextField fieldPrixHt;
	private JTextField FieldTva;
	private JTextField fieldQty;
	JLabel labelTotTva,labelTotal,labelTot;
	private int idDevis,rowDelete;
	DefaultTableModel model;
	

	Product p;
	Dashboard dash;
	private JTextField fieldIdClient;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientUi frame = new ClientUi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public DevisUi(Dashboard dash) {
		
		this.dash = dash;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1036, 639);
		contentPane = new JPanel();
		this.toaster = new Toaster(contentPane);
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.PINK);
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JLabel lblFournisseurManagement = new JLabel("Devis");
		lblFournisseurManagement.setIcon(new ImageIcon(DevisUi.class.getResource("/Gambar/DEVIS.png")));
		lblFournisseurManagement.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 17));
		topPanel.add(lblFournisseurManagement);
		
		JPanel vuePanel = new JPanel();
		//contentPane.add(vuePanel, BorderLayout.WEST);
		vuePanel.setLayout(new BoxLayout(vuePanel, BoxLayout.Y_AXIS));
		
		JLabel labelDevisList = new JLabel("Devis's List       ");
		labelDevisList.setFont(new Font("DejaVu Serif", Font.BOLD, 12));
		vuePanel.add(labelDevisList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		vuePanel.add(scrollPane);
		
		model = makeCompoTable();
       
		
		table = new JTable(model) {

            private static final long serialVersionUID = 1L;

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (isRowSelected(row) && isColumnSelected(column)) {
                    ((JComponent) c).setBorder(new LineBorder(Color.red));
                }

                return c;
            }
        };
        table.addMouseListener(this);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(model);
		table.addMouseListener(this);
			
		scrollPane.setViewportView(table);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		addDevisValider = new JButton("");
		addDevisValider.setEnabled(false);
		addDevisValider.setIcon(new ImageIcon(DevisUi.class.getResource("/Gambar/accept.png")));
		buttonPanel.add(addDevisValider);
		addDevisValider.addActionListener(this);
		
		printBtn = new JButton("");
		printBtn.setEnabled(false);
		printBtn.setIcon(new ImageIcon(DevisUi.class.getResource("/Gambar/printer.png")));
		printBtn.addActionListener(this);
		buttonPanel.add(printBtn);
		
		btnBackToDash = new JButton("");
		btnBackToDash.setIcon(new ImageIcon(DevisUi.class.getResource("/Gambar/reply-message.png")));
		btnBackToDash.addActionListener(this);
		buttonPanel.add(btnBackToDash);
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(1,2));
		JPanel panel_3 = new JPanel();
		panelCenter.add(vuePanel);
		panelCenter.add(panel_3);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblFournisseur = new JLabel("Devis Informations");
		lblFournisseur.setFont(new Font("DejaVu Serif", Font.BOLD, 13));
		panel_3.add(lblFournisseur, BorderLayout.NORTH);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane_1.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		fieldIdClient = new JTextField();
		fieldIdClient.setEnabled(false);
		fieldIdClient.setEditable(false);
		GridBagConstraints gbc_fieldIdClient = new GridBagConstraints();
		gbc_fieldIdClient.insets = new Insets(0, 0, 5, 5);
		gbc_fieldIdClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldIdClient.gridx = 2;
		gbc_fieldIdClient.gridy = 0;
		panel.add(fieldIdClient, gbc_fieldIdClient);
		fieldIdClient.setColumns(10);
		
		JLabel labelMatriculeClient = new JLabel("Matricule Client : ");
		GridBagConstraints gbc_labelMatriculeClient = new GridBagConstraints();
		gbc_labelMatriculeClient.anchor = GridBagConstraints.WEST;
		gbc_labelMatriculeClient.insets = new Insets(0, 0, 5, 5);
		gbc_labelMatriculeClient.gridx = 1;
		gbc_labelMatriculeClient.gridy = 1;
		panel.add(labelMatriculeClient, gbc_labelMatriculeClient);
		
		fieldMatricule = new JTextField();
		GridBagConstraints gbc_fieldMatricule = new GridBagConstraints();
		gbc_fieldMatricule.insets = new Insets(0, 0, 5, 5);
		gbc_fieldMatricule.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldMatricule.gridx = 2;
		gbc_fieldMatricule.gridy = 1;
		panel.add(fieldMatricule, gbc_fieldMatricule);
		fieldMatricule.setColumns(10);
		
		seekClientButton = new JButton("");
		seekClientButton.addActionListener(this);	
		seekClientButton.setIcon(new ImageIcon(DevisUi.class.getResource("/Gambar/magnifier.png")));
		GridBagConstraints gbc_seekClientButton = new GridBagConstraints();
		gbc_seekClientButton.insets = new Insets(0, 0, 5, 0);
		gbc_seekClientButton.gridx = 3;
		gbc_seekClientButton.gridy = 1;
		panel.add(seekClientButton, gbc_seekClientButton);
		
		JLabel labelName = new JLabel("Pr\u00E9nom : ");
		GridBagConstraints gbc_labelName = new GridBagConstraints();
		gbc_labelName.anchor = GridBagConstraints.WEST;
		gbc_labelName.insets = new Insets(0, 0, 5, 5);
		gbc_labelName.gridx = 1;
		gbc_labelName.gridy = 2;
		panel.add(labelName, gbc_labelName);
		
		fieldName = new JTextField();
		fieldName.setEditable(false);
		GridBagConstraints gbc_fieldName = new GridBagConstraints();
		gbc_fieldName.insets = new Insets(0, 0, 5, 5);
		gbc_fieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldName.gridx = 2;
		gbc_fieldName.gridy = 2;
		panel.add(fieldName, gbc_fieldName);
		fieldName.setColumns(10);
		
		JLabel labelLastName = new JLabel("Nom :");
		GridBagConstraints gbc_labelLastName = new GridBagConstraints();
		gbc_labelLastName.anchor = GridBagConstraints.WEST;
		gbc_labelLastName.insets = new Insets(0, 0, 5, 5);
		gbc_labelLastName.gridx = 1;
		gbc_labelLastName.gridy = 3;
		panel.add(labelLastName, gbc_labelLastName);
		
		fieldLastName = new JTextField();
		fieldLastName.setEditable(false);
		GridBagConstraints gbc_fieldLastName = new GridBagConstraints();
		gbc_fieldLastName.insets = new Insets(0, 0, 5, 5);
		gbc_fieldLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldLastName.gridx = 2;
		gbc_fieldLastName.gridy = 3;
		panel.add(fieldLastName, gbc_fieldLastName);
		fieldLastName.setColumns(10);
		
		JLabel labelTel = new JLabel("Tel :");
		GridBagConstraints gbc_labelTel = new GridBagConstraints();
		gbc_labelTel.anchor = GridBagConstraints.WEST;
		gbc_labelTel.insets = new Insets(0, 0, 5, 5);
		gbc_labelTel.gridx = 1;
		gbc_labelTel.gridy = 4;
		panel.add(labelTel, gbc_labelTel);
		
		fieldTel = new JTextField();
		fieldTel.setEditable(false);
		GridBagConstraints gbc_fieldTel = new GridBagConstraints();
		gbc_fieldTel.insets = new Insets(0, 0, 5, 5);
		gbc_fieldTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldTel.gridx = 2;
		gbc_fieldTel.gridy = 4;
		panel.add(fieldTel, gbc_fieldTel);
		fieldTel.setColumns(10);
		
		JLabel labelRefProduit = new JLabel("R\u00E9ference Produit :");
		GridBagConstraints gbc_labelRefProduit = new GridBagConstraints();
		gbc_labelRefProduit.anchor = GridBagConstraints.EAST;
		gbc_labelRefProduit.insets = new Insets(0, 0, 5, 5);
		gbc_labelRefProduit.gridx = 1;
		gbc_labelRefProduit.gridy = 6;
		panel.add(labelRefProduit, gbc_labelRefProduit);
		
		fieldRef = new JTextField();
		GridBagConstraints gbc_fieldRef = new GridBagConstraints();
		gbc_fieldRef.insets = new Insets(0, 0, 5, 5);
		gbc_fieldRef.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldRef.gridx = 2;
		gbc_fieldRef.gridy = 6;
		panel.add(fieldRef, gbc_fieldRef);
		fieldRef.setColumns(10);
		
		btnSeekProduct = new JButton("");
		btnSeekProduct.setEnabled(false);
		btnSeekProduct.addActionListener(this);
		btnSeekProduct.setIcon(new ImageIcon(DevisUi.class.getResource("/Gambar/ecommerce.png")));
		GridBagConstraints gbc_btnSeekProduct = new GridBagConstraints();
		gbc_btnSeekProduct.insets = new Insets(0, 0, 5, 0);
		gbc_btnSeekProduct.gridx = 3;
		gbc_btnSeekProduct.gridy = 6;
		panel.add(btnSeekProduct, gbc_btnSeekProduct);
		
		JLabel labelDescription = new JLabel("Designation : ");
		GridBagConstraints gbc_labelDescription = new GridBagConstraints();
		gbc_labelDescription.anchor = GridBagConstraints.NORTHWEST;
		gbc_labelDescription.insets = new Insets(0, 0, 5, 5);
		gbc_labelDescription.gridx = 1;
		gbc_labelDescription.gridy = 7;
		panel.add(labelDescription, gbc_labelDescription);
		
		fieldDesignation = new JTextField();
		fieldDesignation.setEditable(false);
		GridBagConstraints gbc_fieldDesignation = new GridBagConstraints();
		gbc_fieldDesignation.insets = new Insets(0, 0, 5, 5);
		gbc_fieldDesignation.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldDesignation.gridx = 2;
		gbc_fieldDesignation.gridy = 7;
		panel.add(fieldDesignation, gbc_fieldDesignation);
		fieldDesignation.setColumns(10);
		
		addToLigne = new JButton("");
		addToLigne.setEnabled(false);
		addToLigne.addActionListener(this);
		addToLigne.setForeground(new Color(255, 255, 255));
		addToLigne.setBackground(new Color(255, 255, 255));
		addToLigne.setIcon(new ImageIcon(DevisUi.class.getResource("/Gambar/plus.png")));
		GridBagConstraints gbc_addToLigne = new GridBagConstraints();
		gbc_addToLigne.insets = new Insets(0, 0, 5, 0);
		gbc_addToLigne.gridx = 3;
		gbc_addToLigne.gridy = 7;
		panel.add(addToLigne, gbc_addToLigne);
		
		JLabel labelPrixHt = new JLabel("Prix hors taxe:");
		GridBagConstraints gbc_labelPrixHt = new GridBagConstraints();
		gbc_labelPrixHt.anchor = GridBagConstraints.WEST;
		gbc_labelPrixHt.insets = new Insets(0, 0, 5, 5);
		gbc_labelPrixHt.gridx = 1;
		gbc_labelPrixHt.gridy = 8;
		panel.add(labelPrixHt, gbc_labelPrixHt);
		
		fieldPrixHt = new JTextField();
		fieldPrixHt.setEditable(false);
		GridBagConstraints gbc_fieldPrixHt = new GridBagConstraints();
		gbc_fieldPrixHt.insets = new Insets(0, 0, 5, 5);
		gbc_fieldPrixHt.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldPrixHt.gridx = 2;
		gbc_fieldPrixHt.gridy = 8;
		panel.add(fieldPrixHt, gbc_fieldPrixHt);
		fieldPrixHt.setColumns(10);
		
		deleteFromLigne = new JButton("");
		deleteFromLigne.addActionListener(this);
		deleteFromLigne.setEnabled(false);
		deleteFromLigne.setIcon(new ImageIcon(DevisUi.class.getResource("/Gambar/delete.png")));
		GridBagConstraints gbc_deleteFromLigne = new GridBagConstraints();
		gbc_deleteFromLigne.insets = new Insets(0, 0, 5, 0);
		gbc_deleteFromLigne.gridx = 3;
		gbc_deleteFromLigne.gridy = 8;
		panel.add(deleteFromLigne, gbc_deleteFromLigne);
		
		JLabel labelPrixTva = new JLabel("Prix Tva :");
		GridBagConstraints gbc_labelPrixTva = new GridBagConstraints();
		gbc_labelPrixTva.anchor = GridBagConstraints.WEST;
		gbc_labelPrixTva.insets = new Insets(0, 0, 5, 5);
		gbc_labelPrixTva.gridx = 1;
		gbc_labelPrixTva.gridy = 9;
		panel.add(labelPrixTva, gbc_labelPrixTva);
		
		FieldTva = new JTextField();
		FieldTva.setEditable(false);
		GridBagConstraints gbc_FieldTva = new GridBagConstraints();
		gbc_FieldTva.insets = new Insets(0, 0, 5, 5);
		gbc_FieldTva.fill = GridBagConstraints.HORIZONTAL;
		gbc_FieldTva.gridx = 2;
		gbc_FieldTva.gridy = 9;
		panel.add(FieldTva, gbc_FieldTva);
		FieldTva.setColumns(10);
		
		JLabel labelQty = new JLabel("Quantity :");
		GridBagConstraints gbc_labelQty = new GridBagConstraints();
		gbc_labelQty.anchor = GridBagConstraints.WEST;
		gbc_labelQty.insets = new Insets(0, 0, 5, 5);
		gbc_labelQty.gridx = 1;
		gbc_labelQty.gridy = 10;
		panel.add(labelQty, gbc_labelQty);
		
		fieldQty = new JTextField();
		GridBagConstraints gbc_fieldQty = new GridBagConstraints();
		gbc_fieldQty.insets = new Insets(0, 0, 5, 5);
		gbc_fieldQty.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldQty.gridx = 2;
		gbc_fieldQty.gridy = 10;
		panel.add(fieldQty, gbc_fieldQty);
		fieldQty.setColumns(10);
		
		labelTotal = new JLabel("Prix Total");
		GridBagConstraints gbc_labelTotal = new GridBagConstraints();
		gbc_labelTotal.anchor = GridBagConstraints.WEST;
		gbc_labelTotal.insets = new Insets(0, 0, 5, 5);
		gbc_labelTotal.gridx = 1;
		gbc_labelTotal.gridy = 11;
		panel.add(labelTotal, gbc_labelTotal);
		
		labelTot = new JLabel("0.0");
		labelTot.setForeground(new Color(204, 0, 51));
		labelTot.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 14));
		GridBagConstraints gbc_labelTot = new GridBagConstraints();
		gbc_labelTot.insets = new Insets(0, 0, 5, 5);
		gbc_labelTot.gridx = 2;
		gbc_labelTot.gridy = 11;
		panel.add(labelTot, gbc_labelTot);
		
		JLabel lblNewLabel_1 = new JLabel("PrixTotal Tva :");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 12;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		labelTotTva = new JLabel("0.0");
		labelTotTva.setForeground(new Color(204, 0, 51));
		labelTotTva.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 14));
		GridBagConstraints gbc_labelTotTva = new GridBagConstraints();
		gbc_labelTotTva.insets = new Insets(0, 0, 0, 5);
		gbc_labelTotTva.gridx = 2;
		gbc_labelTotTva.gridy = 12;
		panel.add(labelTotTva, gbc_labelTotTva);
	}
	
	private DefaultTableModel makeCompoTable() {
		
		model = new DefaultTableModel();
        model.addColumn("Designation");
        model.addColumn("Reference");
        model.addColumn("Price Ht");
        model.addColumn("Price tva");
        model.addColumn("Qte");
        model.addColumn("id");
        model.addColumn("idProduct");
 

       
        return model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(seekClientButton)) {
		
			ClientServiceImpl clientImpl = new ClientServiceImpl();
			if(fieldMatricule.getText() != "") {
				Client c = clientImpl.seekByMatricule(fieldMatricule.getText());
				fieldIdClient.setText(Integer.toString(c.getId()));
				fieldName.setText(c.getRaisonSocial().getNom());
				fieldLastName.setText(c.getRaisonSocial().getPrenom());
				fieldTel.setText(Integer.toString(c.getTelMobile()));
				if(fieldName.getText() != "") {
					btnSeekProduct.setEnabled(true);
					DevisServiceImpl devisService = new DevisServiceImpl();
					idDevis = devisService.createDevis(Integer.parseInt(fieldIdClient.getText()));
					
				}
			}
			
		}
		if(e.getSource().equals(btnSeekProduct)) {
			ProductServiceImpl productImpl = new ProductServiceImpl();
			
			p = productImpl.seekByRef(fieldRef.getText());
			
			fieldDesignation.setText(p.getDesignation());
			fieldPrixHt.setText(Double.toString(p.getUnitPriceHt()));
			FieldTva.setText(Double.toString(p.getUnitPriceTva()));
			if(fieldDesignation.getText() != "") {
				addToLigne.setEnabled(true);
			}
		}
		
		
		if(e.getSource().equals(printBtn)){
			
		}
		if(e.getSource().equals(btnBackToDash)) {
			this.dispose();
			dash.setVisible(true);
		}
		
		if(e.getSource().equals(addToLigne)) {
			
			model.addRow(new String[] {
					p.getDesignation(),
					p.getRef(),
					Double.toString(p.getUnitPriceHt() * Integer.parseInt(fieldQty.getText())),
					Double.toString(p.getUnitPriceTva() * Integer.parseInt(fieldQty.getText())),
					fieldQty.getText(),
					Integer.toString(idDevis),
					Integer.toString(p.getId())
			});
			model.fireTableDataChanged();
			 if(model.getRowCount()>0) {
		    	 addDevisValider.setEnabled(true);
		     }
		     else {
		    	 addDevisValider.setEnabled(false);
		     }
			double totHt=0,tot=0;
			
			for (int count = 0; count < model.getRowCount(); count++){
				
				  totHt+=Double.parseDouble(model.getValueAt(count, 2).toString());
				  tot+=Double.parseDouble(model.getValueAt(count, 3).toString());
				}
			
			 labelTot.setText(Double.toString(totHt));
		     labelTotTva.setText(Double.toString(tot));
		     toaster.success("product added to devis");
		     
		}
		
		if(e.getSource().equals(addDevisValider)) {
			DevisServiceImpl devisService = new DevisServiceImpl();
			
			if(idDevis > 0) {
				devisService.addProductLignes(model);
				btnSeekProduct.setEnabled(true);
				seekClientButton.setEnabled(false);
				
			}
			toaster.success("Devis added successfuly");
		}
		
		if(e.getSource().equals(deleteFromLigne)) {
			((DefaultTableModel) table.getModel()).removeRow(rowDelete);
			toaster.error("product deleted from devis");
			double totHt=0,tot=0;
			
			for (int count = 0; count < model.getRowCount(); count++){
				
				  totHt+=Double.parseDouble(model.getValueAt(count, 2).toString());
				  tot+=Double.parseDouble(model.getValueAt(count, 3).toString());
				}
			
			 labelTot.setText(Double.toString(totHt));
		     labelTotTva.setText(Double.toString(tot));
		     if(totHt==0 || tot==0) {
		    	 addDevisValider.setEnabled(false);
		     }
		     else {
		    	 addDevisValider.setEnabled(true);
		     }
		     deleteFromLigne.setEnabled(false);
			}
		
	}
	
		
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(table)) {
			rowDelete = table.getSelectedRow();
			deleteFromLigne.setEnabled(true);
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


	

}
