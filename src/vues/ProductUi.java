package vues;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Toaster.Toaster;
import Utils.UIUtils;
import models.Adress;


import models.Fournisseur;
import models.Product;
import models.ProduitFamille;
import services.ClientServiceImpl;
import services.FournisseurServiceImpl;
import services.ProductServiceImpl;

import javax.swing.JScrollPane;

import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;

import javax.swing.ScrollPaneConstants;
import models.UnitOfMeasure;
import java.awt.Toolkit;

public class ProductUi extends JFrame implements ActionListener,MouseListener,CaretListener,FocusListener{

	private JPanel contentPane;
	private JTable table;
	private JTextField fieldRef;
	private JTextField fieldDesignation;
	private JTextField fieldMinStock;
	private JTextField fieldTva;
	private JTextField fieldPrice;
	private JComboBox comboFournisseur;
	private JButton addProductbtn,modifyProductBtn,deleteProductBtn,btnBackToDash;
	private JTextField fieldIdFamille;
	private JTextField fieldIdProduct;
	private JTextField fieldStock;
	private JTextField fieldNomFamille;
	private JTextField fieldTypeFamille;
	Map<Integer, String> mapFournisseur;
	private final Toaster toaster;
	JComboBox comboUnit;
	private DefaultTableModel model;
	Dashboard dash;
	private Boolean mm = false , a= false, b = false , c = false, d = false, ee = false , f = false;
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
	public ProductUi(Dashboard dash) {
		setTitle("LokyErp - Produit");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProductUi.class.getResource("/Gambar/dragon.png")));
		this.dash = dash;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1036, 639);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		Dimension d =   contentPane.getSize();
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(204, 0, 102));
		contentPane.add(topPanel, BorderLayout.NORTH);
		this.toaster = new Toaster(contentPane);
		JLabel lblFournisseurManagement = new JLabel("Product Management");
		lblFournisseurManagement.setIcon(new ImageIcon(ProductUi.class.getResource("/Gambar/product.png")));
		lblFournisseurManagement.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 17));
		topPanel.add(lblFournisseurManagement);
		
		JPanel vuePanel = new JPanel();
		

		//contentPane.add(vuePanel, BorderLayout.WEST);
		vuePanel.setLayout(new BoxLayout(vuePanel, BoxLayout.Y_AXIS));
		
		JLabel labelFournisseurList = new JLabel("Product's List");
		labelFournisseurList.setFont(new Font("DejaVu Serif", Font.BOLD, 12));
		vuePanel.add(labelFournisseurList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		vuePanel.add(scrollPane);
		model = new DefaultTableModel();
        model.addColumn("ref");
        model.addColumn("designation");
        
        model.addColumn("unit");
        model.addColumn("price");
        model.addColumn("tva");
        
        model.addColumn("stock");
        model.addColumn("minstock");
        model.addColumn("fournisseur_name");
        model.addColumn("famille_name");
        model.addColumn("famille_type");
        model.addColumn("famille_id");
        model.addColumn("fournisseur_id");
        
        model.addColumn("id");
        
      
        ProductServiceImpl produitServ = new ProductServiceImpl();
        for(int i =0 ; i<produitServ.getAll().size(); i++) {
        	 model.addRow(new String[] {
        			 produitServ.getAll().get(i).getRef(),
        			 produitServ.getAll().get(i).getDesignation(),
        			 produitServ.getAll().get(i).getUnit().toString(),
        			 Double.toString(produitServ.getAll().get(i).getUnitPriceHt()),
        			 Double.toString(produitServ.getAll().get(i).getUnitPriceTva()),
        			 Integer.toString(produitServ.getAll().get(i).getStock()),
        			 Integer.toString(produitServ.getAll().get(i).getMinStock()),
        			 produitServ.getAll().get(i).getFournisseur().getRaisonSocial().getNom(),
        			 produitServ.getAll().get(i).getFamille().getNom(),
        			 produitServ.getAll().get(i).getFamille().getType(),
        			 Integer.toString(produitServ.getAll().get(i).getFamille().getId()),
        			 Integer.toString(produitServ.getAll().get(i).getFournisseur().getId()),
        			 Integer.toString(produitServ.getAll().get(i).getId()),
        			 });
        }
       
 
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(model);
		table.addMouseListener(this);
			
		scrollPane.setViewportView(table);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		addProductbtn = new JButton("");
		addProductbtn.setIcon(new ImageIcon(ProductUi.class.getResource("/Gambar/shopping-cart.png")));
		buttonPanel.add(addProductbtn);
		addProductbtn.addActionListener(this);
		
		modifyProductBtn = new JButton("");
		modifyProductBtn.setIcon(new ImageIcon(ProductUi.class.getResource("/Gambar/files.png")));
		modifyProductBtn.addActionListener(this);
		
		buttonPanel.add(modifyProductBtn);
		
		deleteProductBtn = new JButton("");
		deleteProductBtn.setIcon(new ImageIcon(ProductUi.class.getResource("/Gambar/delete.png")));
		deleteProductBtn.addActionListener(this);
		buttonPanel.add(deleteProductBtn);
		
		btnBackToDash = new JButton("");
		btnBackToDash.setIcon(new ImageIcon(ProductUi.class.getResource("/Gambar/reply-message.png")));
		buttonPanel.add(btnBackToDash);
		btnBackToDash.addActionListener(this);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(1,2));
		
		JPanel panel_3 = new JPanel();
		panelCenter.add(vuePanel);
		panelCenter.add(panel_3);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblFournisseur = new JLabel("Product");
		lblFournisseur.setFont(new Font("DejaVu Serif", Font.BOLD, 13));
		panel_3.add(lblFournisseur, BorderLayout.NORTH);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane_1.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel labelRef = new JLabel("Ref  :");
		GridBagConstraints gbc_labelRef = new GridBagConstraints();
		gbc_labelRef.insets = new Insets(0, 0, 5, 5);
		gbc_labelRef.gridx = 1;
		gbc_labelRef.gridy = 1;
		panel.add(labelRef, gbc_labelRef);
		
		fieldRef = new JTextField(UIUtils.REF_PRODUIT);
		fieldRef.addFocusListener(this);
		GridBagConstraints gbc_fieldRef = new GridBagConstraints();
		gbc_fieldRef.gridwidth = 3;
		gbc_fieldRef.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldRef.insets = new Insets(0, 0, 5, 5);
		gbc_fieldRef.gridx = 2;
		gbc_fieldRef.gridy = 1;
		panel.add(fieldRef, gbc_fieldRef);
		fieldRef.setColumns(10);
		
		JLabel labelDesignation = new JLabel("Designation :");
		GridBagConstraints gbc_labelDesignation = new GridBagConstraints();
		gbc_labelDesignation.anchor = GridBagConstraints.WEST;
		gbc_labelDesignation.insets = new Insets(0, 0, 5, 5);
		gbc_labelDesignation.gridx = 6;
		gbc_labelDesignation.gridy = 1;
		panel.add(labelDesignation, gbc_labelDesignation);
		
		fieldDesignation = new JTextField();
		fieldDesignation.addCaretListener(this);
		GridBagConstraints gbc_fieldDesignation = new GridBagConstraints();
		gbc_fieldDesignation.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldDesignation.insets = new Insets(0, 0, 5, 5);
		gbc_fieldDesignation.gridx = 7;
		gbc_fieldDesignation.gridy = 1;
		panel.add(fieldDesignation, gbc_fieldDesignation);
		fieldDesignation.setColumns(10);
		
		JLabel labelUnit = new JLabel("Unit :");
		GridBagConstraints gbc_labelUnit = new GridBagConstraints();
		gbc_labelUnit.insets = new Insets(0, 0, 5, 5);
		gbc_labelUnit.gridx = 1;
		gbc_labelUnit.gridy = 3;
		panel.add(labelUnit, gbc_labelUnit);
		
		comboUnit = new JComboBox();
		comboUnit.setModel(new DefaultComboBoxModel(UnitOfMeasure.values()));
		GridBagConstraints gbc_comboUnit = new GridBagConstraints();
		gbc_comboUnit.gridwidth = 3;
		gbc_comboUnit.insets = new Insets(0, 0, 5, 5);
		gbc_comboUnit.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboUnit.gridx = 2;
		gbc_comboUnit.gridy = 3;
		panel.add(comboUnit, gbc_comboUnit);
		
		JLabel labelFournisseur = new JLabel("Fournisseur  : ");
		GridBagConstraints gbc_labelFournisseur = new GridBagConstraints();
		gbc_labelFournisseur.anchor = GridBagConstraints.EAST;
		gbc_labelFournisseur.insets = new Insets(0, 0, 5, 5);
		gbc_labelFournisseur.gridx = 1;
		gbc_labelFournisseur.gridy = 5;
		panel.add(labelFournisseur, gbc_labelFournisseur);
		
		
		comboFournisseur = new JComboBox();
		mapFournisseur = new HashMap<>();
		FournisseurServiceImpl fournisseurImpl = new FournisseurServiceImpl();
		//---------------------------
		// list des fournisseur 
	
		for(int i =0 ; i<fournisseurImpl.getAll().size(); i++) {
			
			mapFournisseur.put(fournisseurImpl.getAll().get(i).getId(),fournisseurImpl.getAll().get(i).getRaisonSocial().getNom());
		}
		//----------------------------
				
		  for (String name : mapFournisseur.values()) {
			  comboFournisseur.addItem(name);
		    }
		GridBagConstraints gbc_comboFournisseur = new GridBagConstraints();
		gbc_comboFournisseur.gridwidth = 3;
		gbc_comboFournisseur.insets = new Insets(0, 0, 5, 5);
		gbc_comboFournisseur.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboFournisseur.gridx = 2;
		gbc_comboFournisseur.gridy = 5;
		panel.add(comboFournisseur, gbc_comboFournisseur);
		
		JLabel labelFamille = new JLabel("Family : ");
		GridBagConstraints gbc_labelFamille = new GridBagConstraints();
		gbc_labelFamille.anchor = GridBagConstraints.WEST;
		gbc_labelFamille.insets = new Insets(0, 0, 5, 5);
		gbc_labelFamille.gridx = 6;
		gbc_labelFamille.gridy = 5;
		panel.add(labelFamille, gbc_labelFamille);
		
		JLabel labelNomFamily = new JLabel("Nom famille :");
		GridBagConstraints gbc_labelNomFamily = new GridBagConstraints();
		gbc_labelNomFamily.anchor = GridBagConstraints.WEST;
		gbc_labelNomFamily.insets = new Insets(0, 0, 5, 5);
		gbc_labelNomFamily.gridx = 6;
		gbc_labelNomFamily.gridy = 7;
		panel.add(labelNomFamily, gbc_labelNomFamily);
		
		fieldNomFamille = new JTextField();
		fieldNomFamille.addCaretListener(this);
		GridBagConstraints gbc_fieldNomFamille = new GridBagConstraints();
		gbc_fieldNomFamille.insets = new Insets(0, 0, 5, 5);
		gbc_fieldNomFamille.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldNomFamille.gridx = 7;
		gbc_fieldNomFamille.gridy = 7;
		panel.add(fieldNomFamille, gbc_fieldNomFamille);
		fieldNomFamille.setColumns(10);
		
		JLabel labelTypeFamily = new JLabel("Type :");
		GridBagConstraints gbc_labelTypeFamily = new GridBagConstraints();
		gbc_labelTypeFamily.anchor = GridBagConstraints.WEST;
		gbc_labelTypeFamily.insets = new Insets(0, 0, 5, 5);
		gbc_labelTypeFamily.gridx = 6;
		gbc_labelTypeFamily.gridy = 8;
		panel.add(labelTypeFamily, gbc_labelTypeFamily);
		
		fieldTypeFamille = new JTextField();
		fieldTypeFamille.addCaretListener(this);
		GridBagConstraints gbc_fieldTypeFamille = new GridBagConstraints();
		gbc_fieldTypeFamille.insets = new Insets(0, 0, 5, 5);
		gbc_fieldTypeFamille.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldTypeFamille.gridx = 7;
		gbc_fieldTypeFamille.gridy = 8;
		panel.add(fieldTypeFamille, gbc_fieldTypeFamille);
		fieldTypeFamille.setColumns(10);
		
		fieldPrice = new JTextField();
		fieldPrice.setText("1");
		fieldPrice.addCaretListener(this);
		fieldPrice.addCaretListener(this);
		
		JLabel labelPrice = new JLabel("Price(Dt)  : ");
		GridBagConstraints gbc_labelPrice = new GridBagConstraints();
		gbc_labelPrice.anchor = GridBagConstraints.EAST;
		gbc_labelPrice.insets = new Insets(0, 0, 5, 5);
		gbc_labelPrice.gridx = 1;
		gbc_labelPrice.gridy = 10;
		panel.add(labelPrice, gbc_labelPrice);
		GridBagConstraints gbc_fieldPrice = new GridBagConstraints();
		gbc_fieldPrice.gridwidth = 3;
		gbc_fieldPrice.insets = new Insets(0, 0, 5, 5);
		gbc_fieldPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldPrice.gridx = 2;
		gbc_fieldPrice.gridy = 10;
		panel.add(fieldPrice, gbc_fieldPrice);
		fieldPrice.setColumns(10);
		
		JLabel labelTva = new JLabel("TVA  :");
		GridBagConstraints gbc_labelTva = new GridBagConstraints();
		gbc_labelTva.anchor = GridBagConstraints.WEST;
		gbc_labelTva.insets = new Insets(0, 0, 5, 5);
		gbc_labelTva.gridx = 6;
		gbc_labelTva.gridy = 10;
		panel.add(labelTva, gbc_labelTva);
		
		fieldTva = new JTextField();
		fieldTva.setEnabled(false);
		GridBagConstraints gbc_fieldTva = new GridBagConstraints();
		gbc_fieldTva.insets = new Insets(0, 0, 5, 5);
		gbc_fieldTva.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldTva.gridx = 7;
		gbc_fieldTva.gridy = 10;
		panel.add(fieldTva, gbc_fieldTva);
		fieldTva.setColumns(10);
		
		JLabel labelStock = new JLabel("Stock : ");
		GridBagConstraints gbc_labelStock = new GridBagConstraints();
		gbc_labelStock.insets = new Insets(0, 0, 5, 5);
		gbc_labelStock.anchor = GridBagConstraints.EAST;
		gbc_labelStock.gridx = 1;
		gbc_labelStock.gridy = 12;
		panel.add(labelStock, gbc_labelStock);
		
		fieldStock = new JTextField();
		fieldStock.addCaretListener(this);
		GridBagConstraints gbc_fieldStock = new GridBagConstraints();
		gbc_fieldStock.gridwidth = 3;
		gbc_fieldStock.insets = new Insets(0, 0, 5, 5);
		gbc_fieldStock.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldStock.gridx = 2;
		gbc_fieldStock.gridy = 12;
		panel.add(fieldStock, gbc_fieldStock);
		fieldStock.setColumns(10);
		
		JLabel labelMinStock = new JLabel("Min Stock : ");
		GridBagConstraints gbc_labelMinStock = new GridBagConstraints();
		gbc_labelMinStock.anchor = GridBagConstraints.WEST;
		gbc_labelMinStock.insets = new Insets(0, 0, 5, 5);
		gbc_labelMinStock.gridx = 6;
		gbc_labelMinStock.gridy = 12;
		panel.add(labelMinStock, gbc_labelMinStock);
		
		fieldMinStock = new JTextField();
		fieldMinStock.addCaretListener(this);
		GridBagConstraints gbc_fieldMinStock = new GridBagConstraints();
		gbc_fieldMinStock.fill = GridBagConstraints.BOTH;
		gbc_fieldMinStock.insets = new Insets(0, 0, 5, 5);
		gbc_fieldMinStock.gridx = 7;
		gbc_fieldMinStock.gridy = 12;
		panel.add(fieldMinStock, gbc_fieldMinStock);
		fieldMinStock.setColumns(10);
		
		fieldIdProduct = new JTextField();
		fieldIdProduct.setText("none");
		if(fieldIdProduct.getText().equals("none")) {
			modifyProductBtn.setEnabled(false);
		}
		if(fieldIdProduct.getText().equals("none")) {
			deleteProductBtn.setEnabled(false);
		}
		
		GridBagConstraints gbc_fieldIdProduct = new GridBagConstraints();
		gbc_fieldIdProduct.insets = new Insets(0, 0, 0, 5);
		gbc_fieldIdProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldIdProduct.gridx = 6;
		gbc_fieldIdProduct.gridy = 13;
		panel.add(fieldIdProduct, gbc_fieldIdProduct);
		fieldIdProduct.setColumns(10);
		fieldIdProduct.setVisible(false);
		
		fieldIdFamille = new JTextField();
		GridBagConstraints gbc_fieldIdFamille = new GridBagConstraints();
		gbc_fieldIdFamille.insets = new Insets(0, 0, 0, 5);
		gbc_fieldIdFamille.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldIdFamille.gridx = 7;
		gbc_fieldIdFamille.gridy = 13;
		panel.add(fieldIdFamille, gbc_fieldIdFamille);
		fieldIdFamille.setColumns(10);
		fieldIdFamille.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(addProductbtn)) {
			if(a && b && c && d && ee && f && mm) {
				
			
			
			ProductServiceImpl productImpl = new ProductServiceImpl();
			Fournisseur fournisseur = new Fournisseur();
			ProduitFamille familleProduct = new ProduitFamille();
			// --------- info famille product 
			familleProduct.setNom(fieldNomFamille.getText());
			familleProduct.setType(fieldTypeFamille.getText());
			
			
			for (Entry<Integer, String> entry : mapFournisseur.entrySet()) {
				if(entry.getValue().equals(comboFournisseur.getSelectedItem().toString()))
					fournisseur.setId(entry.getKey());
			    }
			
			Product product = new Product();
			// ---------------------------
			// -------------------------- Produit 
			product.setRef(fieldRef.getText());
			product.setDesignation(fieldDesignation.getText());
			product.setUnitPriceHt(Double.parseDouble(fieldPrice.getText()));
			product.setUnitPriceTva(Double.parseDouble(fieldTva.getText()));
			product.setMinStock(Integer.parseInt(fieldMinStock.getText()));
			product.setStock(Integer.parseInt(fieldStock.getText()));
			product.setFournisseur(fournisseur);
	
			switch(comboUnit.getSelectedItem().toString()) {
			case "KILOGRAMME" : product.setUnit(UnitOfMeasure.KILOGRAMME);
			case "GRAMME" : product.setUnit(UnitOfMeasure.GRAMME);
			case "LITRE" : product.setUnit(UnitOfMeasure.LITRE);
			case "LOT" : product.setUnit(UnitOfMeasure.LOT);
			case "PIECE" : product.setUnit(UnitOfMeasure.PIECE);
			case "METRE" : product.setUnit(UnitOfMeasure.METRE);
			case "MILLIMETRE" : product.setUnit(UnitOfMeasure.MILLIMETRE);
			case "MILLIGRAMME" : product.setUnit(UnitOfMeasure.MILLIGRAMME);
			}
			
			product.setFamille(familleProduct);
			
			productImpl.save(product);
			
			toaster.success("product added to the liste");
			((DefaultTableModel)table.getModel()).setNumRows(0);
			 ProductServiceImpl produitServ = new ProductServiceImpl();
		        for(int i =0 ; i<produitServ.getAll().size(); i++) {
		        	 model.addRow(new String[] {
		        			 produitServ.getAll().get(i).getRef(),
		        			 produitServ.getAll().get(i).getDesignation(),
		        			 produitServ.getAll().get(i).getUnit().toString(),
		        			 Double.toString(produitServ.getAll().get(i).getUnitPriceHt()),
		        			 Double.toString(produitServ.getAll().get(i).getUnitPriceTva()),
		        			 Integer.toString(produitServ.getAll().get(i).getStock()),
		        			 Integer.toString(produitServ.getAll().get(i).getMinStock()),
		        			 produitServ.getAll().get(i).getFournisseur().getRaisonSocial().getNom(),
		        			 produitServ.getAll().get(i).getFamille().getNom(),
		        			 produitServ.getAll().get(i).getFamille().getType(),
		        			 Integer.toString(produitServ.getAll().get(i).getFamille().getId()),
		        			 Integer.toString(produitServ.getAll().get(i).getFournisseur().getId()),
		        			 Integer.toString(produitServ.getAll().get(i).getId()),
		        			 });
		        }
		        
		        model.fireTableDataChanged();
		     
			}
			else {
				toaster.error("verifier les champs");
			}
			
		}
		
		if(e.getSource().equals(modifyProductBtn)){
			if(a && b && c && d && ee && f && mm) {
			ProductServiceImpl productImpl = new ProductServiceImpl();
			Fournisseur fournisseur = new Fournisseur();
			ProduitFamille familleProduct = new ProduitFamille();
			// --------- info famille product 
			familleProduct.setId(Integer.parseInt(fieldIdFamille.getText()));
			
			
			for (Entry<Integer, String> entry : mapFournisseur.entrySet()) {
				if(entry.getValue().equals(comboFournisseur.getSelectedItem().toString()))
					fournisseur.setId(entry.getKey());
			    }
			
			Product product = new Product();
			// ---------------------------
			// -------------------------- Produit 
			product.setRef(fieldRef.getText());
			product.setDesignation(fieldDesignation.getText());
			product.setUnitPriceHt(Double.parseDouble(fieldPrice.getText()));
			product.setUnitPriceTva(Double.parseDouble(fieldTva.getText()));
			product.setMinStock(Integer.parseInt(fieldMinStock.getText()));
			product.setStock(Integer.parseInt(fieldStock.getText()));
			product.setFournisseur(fournisseur);
	
			switch(comboUnit.getSelectedItem().toString()) {
			case "KILOGRAMME" : product.setUnit(UnitOfMeasure.KILOGRAMME);
			case "GRAMME" : product.setUnit(UnitOfMeasure.GRAMME);
			case "LITRE" : product.setUnit(UnitOfMeasure.LITRE);
			case "LOT" : product.setUnit(UnitOfMeasure.LOT);
			case "PIECE" : product.setUnit(UnitOfMeasure.PIECE);
			case "METRE" : product.setUnit(UnitOfMeasure.METRE);
			case "MILLIMETRE" : product.setUnit(UnitOfMeasure.MILLIMETRE);
			case "MILLIGRAMME" : product.setUnit(UnitOfMeasure.MILLIGRAMME);
			}
			
			product.setFamille(familleProduct);
			product.setId(Integer.parseInt(fieldIdProduct.getText()));
			productImpl.update(product);
			toaster.success("product modified to the liste");
			((DefaultTableModel)table.getModel()).setNumRows(0);
			 ProductServiceImpl produitServ = new ProductServiceImpl();
		        for(int i =0 ; i<produitServ.getAll().size(); i++) {
		        	 model.addRow(new String[] {
		        			 produitServ.getAll().get(i).getRef(),
		        			 produitServ.getAll().get(i).getDesignation(),
		        			 produitServ.getAll().get(i).getUnit().toString(),
		        			 Double.toString(produitServ.getAll().get(i).getUnitPriceHt()),
		        			 Double.toString(produitServ.getAll().get(i).getUnitPriceTva()),
		        			 Integer.toString(produitServ.getAll().get(i).getStock()),
		        			 Integer.toString(produitServ.getAll().get(i).getMinStock()),
		        			 produitServ.getAll().get(i).getFournisseur().getRaisonSocial().getNom(),
		        			 produitServ.getAll().get(i).getFamille().getNom(),
		        			 produitServ.getAll().get(i).getFamille().getType(),
		        			 Integer.toString(produitServ.getAll().get(i).getFamille().getId()),
		        			 Integer.toString(produitServ.getAll().get(i).getFournisseur().getId()),
		        			 Integer.toString(produitServ.getAll().get(i).getId()),
		        			 });
		        }
		        
		        model.fireTableDataChanged();
			
			
			}
			else {
				toaster.error("verifier les champs");
			}
		}
		if(e.getSource().equals(deleteProductBtn)) {
			ProductServiceImpl productImpl = new ProductServiceImpl();
			Product product = new Product();
			product.setId(Integer.parseInt(fieldIdProduct.getText()));
			JOptionPane confirmPan = new JOptionPane();
			int a=confirmPan.showConfirmDialog(this,"Are you sure deleting this product?");
			if(a==confirmPan.YES_OPTION){  
				productImpl.delete(product);
				toaster.error("product deleted");
			}  
		
		}
		
		if(e.getSource().equals(btnBackToDash)) {
			this.dispose();
			dash.setVisible(true);
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row = table.getSelectedRow();
        int column = table.getColumnCount();
        Object[] val = new Object[column];
        for(int i = 0; i < column; i++) {
            val[i]=table.getValueAt(row, i);
        }
        

        
        fieldRef.setText((String) val[0]);
        fieldDesignation.setText((String) val[1]);
        fieldStock.setText((String) val[5]);
        fieldMinStock.setText((String) val[6]);
        
        fieldPrice.setText((String) val[3]);
        fieldTva.setText((String) val[4]);
        
        fieldNomFamille.setText((String) val[8]);
        fieldTypeFamille.setText((String) val[9]);
        
        fieldIdFamille.setText((String) val[10]);
        fieldIdProduct.setText((String) val[12]);
        
        comboUnit.getModel().setSelectedItem(val[2]);
      
       
        comboFournisseur.getModel().setSelectedItem((String) val[7]);
        
        modifyProductBtn.setEnabled(true);
        deleteProductBtn.setEnabled(true);
        
  
        
       
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
	public void caretUpdate(CaretEvent e) {
			if(e.getSource().equals(fieldPrice)){
				double result = Double.parseDouble(fieldPrice.getText());
				result += result*0.18f;
				fieldTva.setText(Double.toString((double)Math.round(result * 100) /100));
		}
			if(e.getSource().equals(fieldDesignation)){
				String regex = "([a-zA-Z]{3,20}\s*)+";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(fieldDesignation.getText());
				if(matcher.matches()) {
					fieldDesignation.setBorder(new LineBorder(Color.green,1));
					a =  true;
				}
				else
				{
					fieldDesignation.setBorder(new LineBorder(Color.red,1));
					a = false;
				}
			}
			
			if(e.getSource().equals(fieldNomFamille)){
				String regex = "([a-zA-Z]{3,20}\s*)+";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(fieldNomFamille.getText());
				if(matcher.matches()) {
					fieldNomFamille.setBorder(new LineBorder(Color.green,1));
					b =  true;
				}
				else
				{
					fieldNomFamille.setBorder(new LineBorder(Color.red,1));
					b = false;
				}
			}
			if(e.getSource().equals(fieldTypeFamille)){
				String regex = "([a-zA-Z]{3,20}\s*)+";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(fieldTypeFamille.getText());
				if(matcher.matches()) {
					fieldTypeFamille.setBorder(new LineBorder(Color.green,1));
					c =  true;
				}
				else
				{
					fieldTypeFamille.setBorder(new LineBorder(Color.red,1));
					c = false;
				}
			}
			
			if(e.getSource().equals(fieldPrice)){
				String regex = "^[0-9]+.?[0-9]{3}$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(fieldPrice.getText());
				if(matcher.matches()) {
					fieldPrice.setBorder(new LineBorder(Color.green,1));
					d =  true;
				}
				else
				{
					fieldPrice.setBorder(new LineBorder(Color.red,1));
					d = false;
				}
			}
			if(e.getSource().equals(fieldStock)){
				String regex = "^[0-9]+";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(fieldStock.getText());
				if(matcher.matches()) {
					fieldStock.setBorder(new LineBorder(Color.green,1));
					ee =  true;
				}
				else
				{
					fieldStock.setBorder(new LineBorder(Color.red,1));
					ee = false;
				}
			}
			if(e.getSource().equals(fieldMinStock)){
				String regex = "^[0-9]+";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(fieldMinStock.getText());
				if(matcher.matches()) {
					fieldMinStock.setBorder(new LineBorder(Color.green,1));
					f =  true;
				}
				else
				{
					fieldMinStock.setBorder(new LineBorder(Color.red,1));
					f = false;
				}
			}
		
		
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource().equals(fieldRef)) {
			if (fieldRef.getText().equals(UIUtils.REF_PRODUIT)) {
				fieldRef.setText("");
	        }

	
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
if(e.getSource().equals(fieldRef)) {
			
			String regex = "[0-9]{5}[/]{1}[A-Z]{2}[/]{1}[A-Z]{2}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(fieldRef.getText());
			if (matcher.matches()) {
				fieldRef.setBorder(new LineBorder(Color.green,1));
				mm = true;
	        }
			else {
				fieldRef.setBorder(new LineBorder(Color.red,1));
				mm = false;
			}

			
		}
		
	}

}
