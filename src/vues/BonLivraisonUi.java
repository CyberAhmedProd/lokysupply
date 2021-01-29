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
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;



import Toaster.Toaster;
import Utils.MonBonLivraison;
import Utils.MonDevis;
import models.Adress;
import models.BonLivraison;
import models.Client;

import models.Product;
import services.BonLivraisionImpl;
import services.ClientServiceImpl;


import services.ProductServiceImpl;


import javax.swing.JScrollPane;
import java.awt.GridLayout;

import javax.swing.JComponent;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Component;
import java.awt.Desktop;

import javax.swing.ScrollPaneConstants;



import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import models.Ville;


public class BonLivraisonUi extends JFrame implements ActionListener,MouseListener,CaretListener  {

	private JPanel contentPane;
	private JTable table;
	private JButton addBonDeLivraisonValider,printBtn,btnBackToDash,seekClientButton,btnSeekProduct,addToLigne,deleteFromLigne;
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
	private int idBonLibraison,rowDelete;
	DefaultTableModel model;
	private final Toaster toaster;
	

	Product p;
	Dashboard dash;
	private JTextField fieldIdClient;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JTextField fieldRue;
	private JLabel lblNewLabel_3;
	private JTextField fieldNumRue;
	private JLabel lblVille;
	private JComboBox comboVille;
	private JLabel lblNewLabel_4;
	private JTextField fieldCode;
	private JLabel lblNewLabel_5;
	private JTextField fieldGouv;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JComboBox comboPay;
	private JLabel lblInformationExtra;
	private JLabel lblNewLabel_9;
	private JTextField fieldInfo;
	private boolean testGouv=false,testNumRue=false,testRue=false,testCode=false,testInfo=false,testQty = false;
	private JButton btncomm;

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
	public BonLivraisonUi(Dashboard dash) {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DevisUi.class.getResource("/Gambar/dragon.png")));
		setTitle("LokyErp - Bon De Livraison");
		
		this.dash = dash;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1161, 672);
		contentPane = new JPanel();
		this.toaster = new Toaster(contentPane);
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.PINK);
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JLabel lblFournisseurManagement = new JLabel("Bon De Livraison");
		lblFournisseurManagement.setIcon(new ImageIcon(DevisUi.class.getResource("/Gambar/DEVIS.png")));
		lblFournisseurManagement.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 17));
		topPanel.add(lblFournisseurManagement);
		
		JPanel vuePanel = new JPanel();
		//contentPane.add(vuePanel, BorderLayout.WEST);
		vuePanel.setLayout(new BoxLayout(vuePanel, BoxLayout.Y_AXIS));
		
		JLabel labelDevisList = new JLabel("Lignes de Bon de livraision                       ");
		labelDevisList.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
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
		
		addBonDeLivraisonValider = new JButton("");
		addBonDeLivraisonValider.setEnabled(false);
		addBonDeLivraisonValider.setIcon(new ImageIcon(DevisUi.class.getResource("/Gambar/accept.png")));
		buttonPanel.add(addBonDeLivraisonValider);
		addBonDeLivraisonValider.addActionListener(this);
		
		printBtn = new JButton("");
		printBtn.addActionListener(this);
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
		
		JLabel lblFournisseur = new JLabel("Bon De Livraison Information");
		lblFournisseur.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		panel_3.add(lblFournisseur, BorderLayout.NORTH);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane_1.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblNewLabel_7 = new JLabel("Client Infomations");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setForeground(new Color(255, 102, 102));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 0;
		panel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		fieldIdClient = new JTextField();
		fieldIdClient.setEnabled(false);
		fieldIdClient.setVisible(false);
		fieldIdClient.setEditable(false);
		GridBagConstraints gbc_fieldIdClient = new GridBagConstraints();
		gbc_fieldIdClient.insets = new Insets(0, 0, 5, 5);
		gbc_fieldIdClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldIdClient.gridx = 2;
		gbc_fieldIdClient.gridy = 0;
		panel.add(fieldIdClient, gbc_fieldIdClient);
		fieldIdClient.setColumns(10);
		
		lblNewLabel = new JLabel("Address");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(255, 102, 153));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel labelMatriculeClient = new JLabel("Matricule Client : ");
		GridBagConstraints gbc_labelMatriculeClient = new GridBagConstraints();
		gbc_labelMatriculeClient.anchor = GridBagConstraints.WEST;
		gbc_labelMatriculeClient.insets = new Insets(0, 0, 5, 5);
		gbc_labelMatriculeClient.gridx = 1;
		gbc_labelMatriculeClient.gridy = 1;
		panel.add(labelMatriculeClient, gbc_labelMatriculeClient);
		
		fieldMatricule = new JTextField();
		fieldMatricule.setEnabled(false);
		GridBagConstraints gbc_fieldMatricule = new GridBagConstraints();
		gbc_fieldMatricule.insets = new Insets(0, 0, 5, 5);
		gbc_fieldMatricule.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldMatricule.gridx = 2;
		gbc_fieldMatricule.gridy = 1;
		panel.add(fieldMatricule, gbc_fieldMatricule);
		fieldMatricule.setColumns(10);
		
		seekClientButton = new JButton("");
		seekClientButton.setEnabled(false);
		seekClientButton.addActionListener(this);	
		seekClientButton.setIcon(new ImageIcon(DevisUi.class.getResource("/Gambar/magnifier.png")));
		GridBagConstraints gbc_seekClientButton = new GridBagConstraints();
		gbc_seekClientButton.insets = new Insets(0, 0, 5, 5);
		gbc_seekClientButton.gridx = 3;
		gbc_seekClientButton.gridy = 1;
		panel.add(seekClientButton, gbc_seekClientButton);
		
		lblNewLabel_2 = new JLabel("Rue");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		fieldRue = new JTextField();
		fieldRue.addCaretListener(this);
		GridBagConstraints gbc_fieldRue = new GridBagConstraints();
		gbc_fieldRue.insets = new Insets(0, 0, 5, 0);
		gbc_fieldRue.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldRue.gridx = 5;
		gbc_fieldRue.gridy = 1;
		panel.add(fieldRue, gbc_fieldRue);
		fieldRue.setColumns(10);
		
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
		
		lblNewLabel_3 = new JLabel("Num Rue");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 2;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		fieldNumRue = new JTextField();
		fieldNumRue.addCaretListener(this);
		GridBagConstraints gbc_fieldNumRue = new GridBagConstraints();
		gbc_fieldNumRue.insets = new Insets(0, 0, 5, 0);
		gbc_fieldNumRue.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldNumRue.gridx = 5;
		gbc_fieldNumRue.gridy = 2;
		panel.add(fieldNumRue, gbc_fieldNumRue);
		fieldNumRue.setColumns(10);
		
		JLabel labelLastName = new JLabel("Nom :");
		GridBagConstraints gbc_labelLastName = new GridBagConstraints();
		gbc_labelLastName.anchor = GridBagConstraints.WEST;
		gbc_labelLastName.insets = new Insets(0, 0, 5, 5);
		gbc_labelLastName.gridx = 1;
		gbc_labelLastName.gridy = 3;
		panel.add(labelLastName, gbc_labelLastName);
		
		fieldLastName = new JTextField();
		fieldLastName.addCaretListener(this);
		fieldLastName.setEditable(false);
		GridBagConstraints gbc_fieldLastName = new GridBagConstraints();
		gbc_fieldLastName.insets = new Insets(0, 0, 5, 5);
		gbc_fieldLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldLastName.gridx = 2;
		gbc_fieldLastName.gridy = 3;
		panel.add(fieldLastName, gbc_fieldLastName);
		fieldLastName.setColumns(10);
		
		lblVille = new JLabel("Ville");
		GridBagConstraints gbc_lblVille = new GridBagConstraints();
		gbc_lblVille.anchor = GridBagConstraints.EAST;
		gbc_lblVille.insets = new Insets(0, 0, 5, 5);
		gbc_lblVille.gridx = 4;
		gbc_lblVille.gridy = 3;
		panel.add(lblVille, gbc_lblVille);
		
		comboVille = new JComboBox();
		comboVille.setModel(new DefaultComboBoxModel(Ville.values()));
		GridBagConstraints gbc_comboVille = new GridBagConstraints();
		gbc_comboVille.insets = new Insets(0, 0, 5, 0);
		gbc_comboVille.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboVille.gridx = 5;
		gbc_comboVille.gridy = 3;
		panel.add(comboVille, gbc_comboVille);
		
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
		
		lblNewLabel_4 = new JLabel("Code");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 4;
		gbc_lblNewLabel_4.gridy = 4;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		fieldCode = new JTextField();
		fieldCode.addCaretListener(this);
		GridBagConstraints gbc_fieldCode = new GridBagConstraints();
		gbc_fieldCode.insets = new Insets(0, 0, 5, 0);
		gbc_fieldCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldCode.gridx = 5;
		gbc_fieldCode.gridy = 4;
		panel.add(fieldCode, gbc_fieldCode);
		fieldCode.setColumns(10);
		
		lblNewLabel_8 = new JLabel("Produit information");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setForeground(new Color(255, 102, 102));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 5;
		panel.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		lblNewLabel_5 = new JLabel("Gouvernat");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 4;
		gbc_lblNewLabel_5.gridy = 5;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		fieldGouv = new JTextField();
		fieldGouv.addCaretListener(this);
		GridBagConstraints gbc_fieldGouv = new GridBagConstraints();
		gbc_fieldGouv.insets = new Insets(0, 0, 5, 0);
		gbc_fieldGouv.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldGouv.gridx = 5;
		gbc_fieldGouv.gridy = 5;
		panel.add(fieldGouv, gbc_fieldGouv);
		fieldGouv.setColumns(10);
		
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
		gbc_btnSeekProduct.insets = new Insets(0, 0, 5, 5);
		gbc_btnSeekProduct.gridx = 3;
		gbc_btnSeekProduct.gridy = 6;
		panel.add(btnSeekProduct, gbc_btnSeekProduct);
		
		lblNewLabel_6 = new JLabel("Pays");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 4;
		gbc_lblNewLabel_6.gridy = 6;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		comboPay = new JComboBox();
		comboPay.setModel(new DefaultComboBoxModel(new String[] {"Tunisie"}));
		GridBagConstraints gbc_comboPay = new GridBagConstraints();
		gbc_comboPay.insets = new Insets(0, 0, 5, 0);
		gbc_comboPay.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboPay.gridx = 5;
		gbc_comboPay.gridy = 6;
		panel.add(comboPay, gbc_comboPay);
		
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
		gbc_addToLigne.insets = new Insets(0, 0, 5, 5);
		gbc_addToLigne.gridx = 3;
		gbc_addToLigne.gridy = 7;
		panel.add(addToLigne, gbc_addToLigne);
		
		lblInformationExtra = new JLabel("Information extra");
		lblInformationExtra.setForeground(new Color(255, 102, 153));
		lblInformationExtra.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblInformationExtra = new GridBagConstraints();
		gbc_lblInformationExtra.insets = new Insets(0, 0, 5, 5);
		gbc_lblInformationExtra.gridx = 4;
		gbc_lblInformationExtra.gridy = 7;
		panel.add(lblInformationExtra, gbc_lblInformationExtra);
		
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
		gbc_deleteFromLigne.insets = new Insets(0, 0, 5, 5);
		gbc_deleteFromLigne.gridx = 3;
		gbc_deleteFromLigne.gridy = 8;
		panel.add(deleteFromLigne, gbc_deleteFromLigne);
		
		lblNewLabel_9 = new JLabel("information");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 4;
		gbc_lblNewLabel_9.gridy = 8;
		panel.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		fieldInfo = new JTextField();
		fieldInfo.addCaretListener(this);
		GridBagConstraints gbc_fieldInfo = new GridBagConstraints();
		gbc_fieldInfo.insets = new Insets(0, 0, 5, 0);
		gbc_fieldInfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldInfo.gridx = 5;
		gbc_fieldInfo.gridy = 8;
		panel.add(fieldInfo, gbc_fieldInfo);
		fieldInfo.setColumns(10);
		
		btncomm = new JButton("");
		btncomm.addActionListener(this);
		btncomm.setIcon(new ImageIcon(BonLivraisonUi.class.getResource("/Gambar/accept.png")));
		GridBagConstraints gbc_btncomm = new GridBagConstraints();
		gbc_btncomm.insets = new Insets(0, 0, 5, 0);
		gbc_btncomm.gridx = 5;
		gbc_btncomm.gridy = 9;
		panel.add(btncomm, gbc_btncomm);
		
		JLabel labelPrixTva = new JLabel("Prix Tva :");
		GridBagConstraints gbc_labelPrixTva = new GridBagConstraints();
		gbc_labelPrixTva.anchor = GridBagConstraints.WEST;
		gbc_labelPrixTva.insets = new Insets(0, 0, 5, 5);
		gbc_labelPrixTva.gridx = 1;
		gbc_labelPrixTva.gridy = 10;
		panel.add(labelPrixTva, gbc_labelPrixTva);
		
		FieldTva = new JTextField();
		FieldTva.setEditable(false);
		GridBagConstraints gbc_FieldTva = new GridBagConstraints();
		gbc_FieldTva.insets = new Insets(0, 0, 5, 5);
		gbc_FieldTva.fill = GridBagConstraints.HORIZONTAL;
		gbc_FieldTva.gridx = 2;
		gbc_FieldTva.gridy = 10;
		panel.add(FieldTva, gbc_FieldTva);
		FieldTva.setColumns(10);
		
		JLabel labelQty = new JLabel("Quantity :");
		GridBagConstraints gbc_labelQty = new GridBagConstraints();
		gbc_labelQty.anchor = GridBagConstraints.WEST;
		gbc_labelQty.insets = new Insets(0, 0, 5, 5);
		gbc_labelQty.gridx = 1;
		gbc_labelQty.gridy = 11;
		panel.add(labelQty, gbc_labelQty);
		
		fieldQty = new JTextField();
		fieldQty.addCaretListener(this);
		fieldQty.setText("1");
		GridBagConstraints gbc_fieldQty = new GridBagConstraints();
		gbc_fieldQty.insets = new Insets(0, 0, 5, 5);
		gbc_fieldQty.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldQty.gridx = 2;
		gbc_fieldQty.gridy = 11;
		panel.add(fieldQty, gbc_fieldQty);
		fieldQty.setColumns(10);
		
		labelTotal = new JLabel("Prix Total");
		GridBagConstraints gbc_labelTotal = new GridBagConstraints();
		gbc_labelTotal.anchor = GridBagConstraints.WEST;
		gbc_labelTotal.insets = new Insets(0, 0, 5, 5);
		gbc_labelTotal.gridx = 1;
		gbc_labelTotal.gridy = 12;
		panel.add(labelTotal, gbc_labelTotal);
		
		labelTot = new JLabel("0.0");
		labelTot.setForeground(new Color(204, 0, 51));
		labelTot.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 14));
		GridBagConstraints gbc_labelTot = new GridBagConstraints();
		gbc_labelTot.insets = new Insets(0, 0, 5, 5);
		gbc_labelTot.gridx = 2;
		gbc_labelTot.gridy = 12;
		panel.add(labelTot, gbc_labelTot);
		
		JLabel lblNewLabel_1 = new JLabel("PrixTotal Tva :");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 13;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		labelTotTva = new JLabel("0.0");
		labelTotTva.setForeground(new Color(204, 0, 51));
		labelTotTva.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 14));
		GridBagConstraints gbc_labelTotTva = new GridBagConstraints();
		gbc_labelTotTva.insets = new Insets(0, 0, 0, 5);
		gbc_labelTotTva.gridx = 2;
		gbc_labelTotTva.gridy = 13;
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
					
					// bl
					Adress address = new Adress();
					BonLivraison bl = new BonLivraison();
					address.setNumRue(Integer.parseInt(fieldNumRue.getText()));
					address.setLibelleRue( fieldRue.getText());
					address.setNomVille(comboVille.getSelectedItem().toString());
					address.setCodePostale(Integer.parseInt(fieldCode.getText()));
					address.setGouvernat(fieldGouv.getText());
					address.setPays(comboPay.getSelectedItem().toString());
					bl.setAdressLivraison(address);
					bl.setInformation(fieldInfo.getText());
					//
					BonLivraisionImpl devisService = new BonLivraisionImpl();
					idBonLibraison = devisService.createBonLivraison(Integer.parseInt(fieldIdClient.getText()),bl);
					
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
			if(testQty) {
				
			
			ProductServiceImpl prouctService = new ProductServiceImpl();
			if(prouctService.seekByRef(fieldRef.getText()).getStock()- Double.parseDouble(fieldQty.getText()) > prouctService.seekByRef(fieldRef.getText()).getMinStock()) {
			model.addRow(new String[] {
					p.getDesignation(),
					p.getRef(),
					Double.toString(p.getUnitPriceHt() * Integer.parseInt(fieldQty.getText())),
					Double.toString(p.getUnitPriceTva() * Integer.parseInt(fieldQty.getText())),
					fieldQty.getText(),
					Integer.toString(idBonLibraison),
					Integer.toString(p.getId())
			});
			model.fireTableDataChanged();
			 if(model.getRowCount()>0) {
		    	 addBonDeLivraisonValider.setEnabled(true);
		     }
		     else {
		    	 addBonDeLivraisonValider.setEnabled(false);
		     }
			double totHt=0,tot=0;
			
			for (int count = 0; count < model.getRowCount(); count++){
				
				  totHt+=Double.parseDouble(model.getValueAt(count, 2).toString());
				  tot+=Double.parseDouble(model.getValueAt(count, 3).toString());
				}
			
			 labelTot.setText(Double.toString(totHt));
		     labelTotTva.setText(Double.toString(tot));
		     toaster.success("product added to Bon livraison");
			}
			else {
				 toaster.error("Stock minimal ="+prouctService.seekByRef(fieldRef.getText()).getMinStock()+" and you have "+prouctService.seekByRef(fieldRef.getText()).getStock()+" in stock");
			}
			}
			else {
				 toaster.error("verifier champs Quantity");
			}
		}
		
		if(e.getSource().equals(addBonDeLivraisonValider)) {
			BonLivraisionImpl bonLivraisionService = new BonLivraisionImpl();
			
			if(idBonLibraison > 0) {
				
				bonLivraisionService.addProductLignes(model);
				btnSeekProduct.setEnabled(true);
				seekClientButton.setEnabled(false);
				
			}
			toaster.success("Bon Livraison added successfuly");
		}
		
		if(e.getSource().equals(deleteFromLigne)) {
			((DefaultTableModel) table.getModel()).removeRow(rowDelete);
			toaster.error("product deleted from Bon Livraison");
			double totHt=0,tot=0;
			
			for (int count = 0; count < model.getRowCount(); count++){
				
				  totHt+=Double.parseDouble(model.getValueAt(count, 2).toString());
				  tot+=Double.parseDouble(model.getValueAt(count, 3).toString());
				}
			
			 labelTot.setText(Double.toString(totHt));
		     labelTotTva.setText(Double.toString(tot));
		     if(totHt==0 || tot==0) {
		    	 addBonDeLivraisonValider.setEnabled(false);
		     }
		     else {
		    	 addBonDeLivraisonValider.setEnabled(true);
		     }
		     deleteFromLigne.setEnabled(false);
			}
		
		
		if(e.getSource().equals(printBtn)) {
			 if (Desktop.isDesktopSupported()) {
				 MonBonLivraison printDevis = new MonBonLivraison(idBonLibraison);
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
	
		if(e.getSource().equals(btncomm)) {
			if(testGouv && testCode && testNumRue && testRue && testInfo) {
				seekClientButton.setEnabled(true);
				fieldMatricule.setEnabled(true);
			}
			else {
				toaster.error("verifier les champs address");
			}
			
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

	@Override
	public void caretUpdate(CaretEvent e) {
		if(e.getSource().equals(fieldGouv)) {
			if(fieldGouv.getText() != "") {
				testGouv = true;
			}
			else {
				seekClientButton.setEnabled(false);
				fieldMatricule.setEnabled(false);
			}
			
		}
		if(e.getSource().equals(fieldInfo)) {
			if(fieldInfo.getText() != "") {
				testInfo = true;
			}
			else {
				seekClientButton.setEnabled(false);
				fieldMatricule.setEnabled(false);
			}
		}
		if(e.getSource().equals(fieldRue)) {
			if(fieldRue.getText() != "") {
				testRue = true;
			}
			else {
				seekClientButton.setEnabled(false);
				fieldMatricule.setEnabled(false);
			}
		}
		if(e.getSource().equals(fieldNumRue)) {
			if(fieldNumRue.getText() != "") {
				testNumRue = true;
			}
			else {
				seekClientButton.setEnabled(false);
				fieldMatricule.setEnabled(false);
			}
		}
		if(e.getSource().equals(fieldCode)) {
			if(fieldCode.getText() != "") {
				testCode = true;
			}
			else {
				seekClientButton.setEnabled(false);
				fieldMatricule.setEnabled(false);
			}
		}
		if(e.getSource().equals(fieldQty)) {
			String regex = "^[0-9]+";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(fieldQty.getText());
			if(matcher.matches()) {
				testQty = true;
			}
			else {
				testQty= false;
			}
		}
		
		
	}


	

}
