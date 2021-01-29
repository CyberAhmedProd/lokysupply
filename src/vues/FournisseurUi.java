package vues;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
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
import javax.swing.text.FieldView;

import Toaster.Toaster;
import Utils.UIUtils;
import models.Adress;
import models.Client;
import models.CompteBancaire;
import models.Fournisseur;
import models.RaisonSocial;
import models.TypeEntreprise;
import services.ClientServiceImpl;
import services.FournisseurServiceImpl;

import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;
import models.Ville;
import java.awt.Toolkit;

public class FournisseurUi extends JFrame implements ActionListener,MouseListener,CaretListener,FocusListener {

	private JPanel contentPane;
	private JTable table;
	private JTextField fieldMatricule;
	private JTextField fielTel;
	private JTextField fieldMobile;
	private JTextField fieldEmail;
	private JTextField fieldWebSIte;
	private JTextField fieldLastName;
	private JTextField fieldName;
	private JTextField fieldNameBanque;
	private JTextField fieldNameAgence;
	private JTextField fieldRib;
	private JTextField fieldRue;
	private JTextField fieldNumRue;
	private JTextField fieldCode;
	private JTextField fieldGouvernat;
	private JComboBox comboVille,comboPays;
	JCheckBox checkBoxTva;
	JEditorPane editorPaneDescription;
	private JButton addFournisseurtbtn,modifyFournisseurBtn,deleteDeleteBtn,btnBackToDash;
	private JTextField fieldIdClient;
	private JTextField fieldIdSocial;
	private JTextField fieldIdAdress;
	private JTextField fieldIdBanque;
	JComboBox comboBoxType;
	private final Toaster toaster;
	DefaultTableModel model;
	Dashboard dash;
	private Boolean a=true,b=true,c=true,d=true,ee=true,f=true,g=true,h=true,j=true,k=true, mm = true;

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
	public FournisseurUi(Dashboard dash) {
		setTitle("LokyErp - Fournisseur");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FournisseurUi.class.getResource("/Gambar/dragon.png")));
		this.dash = dash;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1036, 639);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		this.toaster = new Toaster(contentPane);
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 204, 255));
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JLabel lblFournisseurManagement = new JLabel("Supplier Management");
		lblFournisseurManagement.setIcon(new ImageIcon(FournisseurUi.class.getResource("/Gambar/manufacture.png")));
		lblFournisseurManagement.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 17));
		topPanel.add(lblFournisseurManagement);
		
		JPanel vuePanel = new JPanel();
		//contentPane.add(vuePanel, BorderLayout.WEST);
		vuePanel.setLayout(new BoxLayout(vuePanel, BoxLayout.Y_AXIS));
		
		JLabel labelFournisseurList = new JLabel("Supplier's List");
		labelFournisseurList.setFont(new Font("DejaVu Serif", Font.BOLD, 12));
		vuePanel.add(labelFournisseurList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		vuePanel.add(scrollPane);
		model = new DefaultTableModel();
        model.addColumn("nom");
        model.addColumn("prenom");
        model.addColumn("matricule");
        model.addColumn("mobile");
        model.addColumn("email");
        model.addColumn("tel");
        
        model.addColumn("pays");
        model.addColumn("rue");
        model.addColumn("numRue");
        model.addColumn("ville");
        model.addColumn("gouvernat");
        model.addColumn("code postal");
        model.addColumn("type");
        
        model.addColumn("RIB");
        model.addColumn("nom banque");
        model.addColumn("nom agance");
        
        model.addColumn("sexe");
        
      
        model.addColumn("siteweb");
        model.addColumn("description");
        model.addColumn("id client");
        model.addColumn("id banque");
        model.addColumn("id soc");
        model.addColumn("id Adress");
      
        FournisseurServiceImpl fournisseurServ = new FournisseurServiceImpl();
        for(int i =0 ; i<fournisseurServ.getAll().size(); i++) {
        	 model.addRow(new String[] {
        			 fournisseurServ.getAll().get(i).getRaisonSocial().getNom(),
        			 fournisseurServ.getAll().get(i).getRaisonSocial().getPrenom(),
        			 fournisseurServ.getAll().get(i).getMatricule(),
        			 Integer.toString(fournisseurServ.getAll().get(i).getTelMobile()),
        			 fournisseurServ.getAll().get(i).getEmail(),
        			 Integer.toString(fournisseurServ.getAll().get(i).getTelFix()),
        			 fournisseurServ.getAll().get(i).getAdresse().getPays(),
        			 fournisseurServ.getAll().get(i).getAdresse().getLibelleRue(),
        			 Integer.toString(fournisseurServ.getAll().get(i).getAdresse().getNumRue()),
        			 fournisseurServ.getAll().get(i).getAdresse().getNomVille(),
        			 fournisseurServ.getAll().get(i).getAdresse().getGouvernat(),
        			 Integer.toString(fournisseurServ.getAll().get(i).getAdresse().getCodePostale()),
        			 fournisseurServ.getAll().get(i).getType().toString(),
        			 Integer.toString(fournisseurServ.getAll().get(i).getCompteBancaires().get(0).getNumRib()),
        			 fournisseurServ.getAll().get(i).getCompteBancaires().get(0).getNameBanque(),
        			 fournisseurServ.getAll().get(i).getCompteBancaires().get(0).getAgence(),
        			 fournisseurServ.getAll().get(i).getRaisonSocial().getSexe(),
        			 fournisseurServ.getAll().get(i).getWebSite(),
        			 fournisseurServ.getAll().get(i).getDescription(),
        			 Integer.toString(fournisseurServ.getAll().get(i).getId()),
        			 Integer.toString(fournisseurServ.getAll().get(i).getCompteBancaires().get(0).getId()),
        			 Integer.toString(fournisseurServ.getAll().get(i).getRaisonSocial().getId()),
        			 Integer.toString(fournisseurServ.getAll().get(i).getAdresse().getId()),
        			 Boolean.toString(fournisseurServ.getAll().get(i).isTva_assuj())
        			 
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
		
		addFournisseurtbtn = new JButton("");
		addFournisseurtbtn.setIcon(new ImageIcon(FournisseurUi.class.getResource("/Gambar/add-user.png")));
		buttonPanel.add(addFournisseurtbtn);
		addFournisseurtbtn.addActionListener(this);
		
		modifyFournisseurBtn = new JButton("");
		modifyFournisseurBtn.setIcon(new ImageIcon(FournisseurUi.class.getResource("/Gambar/files.png")));
		modifyFournisseurBtn.addActionListener(this);
		buttonPanel.add(modifyFournisseurBtn);
		
		deleteDeleteBtn = new JButton("");
		deleteDeleteBtn.setIcon(new ImageIcon(FournisseurUi.class.getResource("/Gambar/delete.png")));
		deleteDeleteBtn.addActionListener(this);
		buttonPanel.add(deleteDeleteBtn);
		
		btnBackToDash = new JButton("");
		btnBackToDash.setIcon(new ImageIcon(FournisseurUi.class.getResource("/Gambar/reply-message.png")));
		buttonPanel.add(btnBackToDash);
		btnBackToDash.addActionListener(this);
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(1,2));
		JPanel panel_3 = new JPanel();
		panelCenter.add(vuePanel);
		panelCenter.add(panel_3);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblFournisseur = new JLabel("Supplier");
		lblFournisseur.setFont(new Font("DejaVu Serif", Font.BOLD, 13));
		panel_3.add(lblFournisseur, BorderLayout.NORTH);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane_1.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel labelMatricule = new JLabel("Matricule :");
		GridBagConstraints gbc_labelMatricule = new GridBagConstraints();
		gbc_labelMatricule.insets = new Insets(0, 0, 5, 5);
		gbc_labelMatricule.gridx = 1;
		gbc_labelMatricule.gridy = 1;
		panel.add(labelMatricule, gbc_labelMatricule);
		
		fieldMatricule = new JTextField(UIUtils.MATRICULE_FISCALE);
		fieldMatricule.addFocusListener(this);
		GridBagConstraints gbc_fieldMatricule = new GridBagConstraints();
		gbc_fieldMatricule.gridwidth = 3;
		gbc_fieldMatricule.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldMatricule.insets = new Insets(0, 0, 5, 5);
		gbc_fieldMatricule.gridx = 2;
		gbc_fieldMatricule.gridy = 1;
		panel.add(fieldMatricule, gbc_fieldMatricule);
		fieldMatricule.setColumns(10);
		
		JLabel labelName = new JLabel("Nom : ");
		GridBagConstraints gbc_labelName = new GridBagConstraints();
		gbc_labelName.anchor = GridBagConstraints.WEST;
		gbc_labelName.insets = new Insets(0, 0, 5, 5);
		gbc_labelName.gridx = 6;
		gbc_labelName.gridy = 1;
		panel.add(labelName, gbc_labelName);
		
		fieldLastName = new JTextField();
		fieldLastName.addCaretListener(this);
		GridBagConstraints gbc_fieldLastName = new GridBagConstraints();
		gbc_fieldLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldLastName.insets = new Insets(0, 0, 5, 5);
		gbc_fieldLastName.gridx = 7;
		gbc_fieldLastName.gridy = 1;
		panel.add(fieldLastName, gbc_fieldLastName);
		fieldLastName.setColumns(10);
		
		JLabel labelTel = new JLabel("Tel : ");
		GridBagConstraints gbc_labelTel = new GridBagConstraints();
		gbc_labelTel.insets = new Insets(0, 0, 5, 5);
		gbc_labelTel.gridx = 1;
		gbc_labelTel.gridy = 2;
		panel.add(labelTel, gbc_labelTel);
		
		fielTel = new JTextField();
		fielTel.addCaretListener(this);
		GridBagConstraints gbc_fielTel = new GridBagConstraints();
		gbc_fielTel.gridwidth = 3;
		gbc_fielTel.insets = new Insets(0, 0, 5, 5);
		gbc_fielTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_fielTel.gridx = 2;
		gbc_fielTel.gridy = 2;
		panel.add(fielTel, gbc_fielTel);
		fielTel.setColumns(10);
		
		JLabel labelLastName = new JLabel("Prenom : ");
		GridBagConstraints gbc_labelLastName = new GridBagConstraints();
		gbc_labelLastName.anchor = GridBagConstraints.WEST;
		gbc_labelLastName.insets = new Insets(0, 0, 5, 5);
		gbc_labelLastName.gridx = 6;
		gbc_labelLastName.gridy = 2;
		panel.add(labelLastName, gbc_labelLastName);
		
		fieldName = new JTextField();
		fieldName.addCaretListener(this);
		GridBagConstraints gbc_fieldName = new GridBagConstraints();
		gbc_fieldName.fill = GridBagConstraints.BOTH;
		gbc_fieldName.insets = new Insets(0, 0, 5, 5);
		gbc_fieldName.gridx = 7;
		gbc_fieldName.gridy = 2;
		panel.add(fieldName, gbc_fieldName);
		fieldName.setColumns(10);
		
		JLabel labelMobile = new JLabel("Mobile :");
		GridBagConstraints gbc_labelMobile = new GridBagConstraints();
		gbc_labelMobile.insets = new Insets(0, 0, 5, 5);
		gbc_labelMobile.gridx = 1;
		gbc_labelMobile.gridy = 3;
		panel.add(labelMobile, gbc_labelMobile);
		
		fieldMobile = new JTextField();
		fieldMobile.addCaretListener(this);
		GridBagConstraints gbc_fieldMobile = new GridBagConstraints();
		gbc_fieldMobile.gridwidth = 3;
		gbc_fieldMobile.insets = new Insets(0, 0, 5, 5);
		gbc_fieldMobile.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldMobile.gridx = 2;
		gbc_fieldMobile.gridy = 3;
		panel.add(fieldMobile, gbc_fieldMobile);
		fieldMobile.setColumns(10);
		
		JLabel labelSexe = new JLabel("Sexe : ");
		GridBagConstraints gbc_labelSexe = new GridBagConstraints();
		gbc_labelSexe.anchor = GridBagConstraints.WEST;
		gbc_labelSexe.insets = new Insets(0, 0, 5, 5);
		gbc_labelSexe.gridx = 6;
		gbc_labelSexe.gridy = 3;
		panel.add(labelSexe, gbc_labelSexe);
		
		JRadioButton radioFemme = new JRadioButton("femme");
		GridBagConstraints gbc_radioFemme = new GridBagConstraints();
		gbc_radioFemme.insets = new Insets(0, 0, 5, 5);
		gbc_radioFemme.gridx = 7;
		gbc_radioFemme.gridy = 3;
		panel.add(radioFemme, gbc_radioFemme);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("homme");
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 9;
		gbc_rdbtnNewRadioButton_1.gridy = 3;
		panel.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		
		JLabel labelEmail = new JLabel("Email :");
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 1;
		gbc_labelEmail.gridy = 4;
		panel.add(labelEmail, gbc_labelEmail);
		
		fieldEmail = new JTextField();
		fieldEmail.addCaretListener(this);
		GridBagConstraints gbc_fieldEmail = new GridBagConstraints();
		gbc_fieldEmail.gridwidth = 3;
		gbc_fieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_fieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldEmail.gridx = 2;
		gbc_fieldEmail.gridy = 4;
		panel.add(fieldEmail, gbc_fieldEmail);
		fieldEmail.setColumns(10);
		
		JLabel labelTva = new JLabel("TVA :");
		GridBagConstraints gbc_labelTva = new GridBagConstraints();
		gbc_labelTva.anchor = GridBagConstraints.NORTHWEST;
		gbc_labelTva.insets = new Insets(0, 0, 5, 5);
		gbc_labelTva.gridx = 6;
		gbc_labelTva.gridy = 4;
		panel.add(labelTva, gbc_labelTva);
		
		checkBoxTva = new JCheckBox("tva_ajjusti");
		GridBagConstraints gbc_checkBoxTva = new GridBagConstraints();
		gbc_checkBoxTva.insets = new Insets(0, 0, 5, 5);
		gbc_checkBoxTva.gridx = 7;
		gbc_checkBoxTva.gridy = 4;
		panel.add(checkBoxTva, gbc_checkBoxTva);
		
		JLabel labelWebSite = new JLabel("WebSite :");
		GridBagConstraints gbc_labelWebSite = new GridBagConstraints();
		gbc_labelWebSite.insets = new Insets(0, 0, 5, 5);
		gbc_labelWebSite.gridx = 1;
		gbc_labelWebSite.gridy = 5;
		panel.add(labelWebSite, gbc_labelWebSite);
		
		fieldWebSIte = new JTextField();
		fieldWebSIte.addCaretListener(this);
		GridBagConstraints gbc_fieldWebSIte = new GridBagConstraints();
		gbc_fieldWebSIte.gridwidth = 3;
		gbc_fieldWebSIte.insets = new Insets(0, 0, 5, 5);
		gbc_fieldWebSIte.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldWebSIte.gridx = 2;
		gbc_fieldWebSIte.gridy = 5;
		panel.add(fieldWebSIte, gbc_fieldWebSIte);
		fieldWebSIte.setColumns(10);
		
		JLabel LabelCompte = new JLabel("CompteBancaire");
		GridBagConstraints gbc_LabelCompte = new GridBagConstraints();
		gbc_LabelCompte.insets = new Insets(0, 0, 5, 5);
		gbc_LabelCompte.gridx = 6;
		gbc_LabelCompte.gridy = 5;
		panel.add(LabelCompte, gbc_LabelCompte);
		
		JLabel labelType = new JLabel("Type :");
		GridBagConstraints gbc_labelType = new GridBagConstraints();
		gbc_labelType.insets = new Insets(0, 0, 5, 5);
		gbc_labelType.gridx = 1;
		gbc_labelType.gridy = 6;
		panel.add(labelType, gbc_labelType);
	
		comboBoxType = new JComboBox();
	
		comboBoxType.setModel(new DefaultComboBoxModel(TypeEntreprise.values()));
		GridBagConstraints gbc_comboBoxType = new GridBagConstraints();
		gbc_comboBoxType.gridwidth = 3;
		gbc_comboBoxType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxType.gridx = 2;
		gbc_comboBoxType.gridy = 6;
		panel.add(comboBoxType, gbc_comboBoxType);
		
		JLabel labelNomBanque = new JLabel("Nom banque :");
		GridBagConstraints gbc_labelNomBanque = new GridBagConstraints();
		gbc_labelNomBanque.anchor = GridBagConstraints.EAST;
		gbc_labelNomBanque.insets = new Insets(0, 0, 5, 5);
		gbc_labelNomBanque.gridx = 6;
		gbc_labelNomBanque.gridy = 6;
		panel.add(labelNomBanque, gbc_labelNomBanque);
		
		fieldNameBanque = new JTextField();
		fieldNameBanque.addCaretListener(this);
		GridBagConstraints gbc_fieldNameBanque = new GridBagConstraints();
		gbc_fieldNameBanque.insets = new Insets(0, 0, 5, 5);
		gbc_fieldNameBanque.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldNameBanque.gridx = 7;
		gbc_fieldNameBanque.gridy = 6;
		panel.add(fieldNameBanque, gbc_fieldNameBanque);
		fieldNameBanque.setColumns(10);
		
		JLabel labelDescription = new JLabel("Description : ");
		GridBagConstraints gbc_labelDescription = new GridBagConstraints();
		gbc_labelDescription.anchor = GridBagConstraints.NORTH;
		gbc_labelDescription.insets = new Insets(0, 0, 5, 5);
		gbc_labelDescription.gridx = 1;
		gbc_labelDescription.gridy = 7;
		panel.add(labelDescription, gbc_labelDescription);
		
		editorPaneDescription = new JEditorPane();
		GridBagConstraints gbc_editorPaneDescription = new GridBagConstraints();
		gbc_editorPaneDescription.gridheight = 2;
		gbc_editorPaneDescription.gridwidth = 3;
		gbc_editorPaneDescription.insets = new Insets(0, 0, 5, 5);
		gbc_editorPaneDescription.fill = GridBagConstraints.BOTH;
		gbc_editorPaneDescription.gridx = 2;
		gbc_editorPaneDescription.gridy = 7;
		panel.add(editorPaneDescription, gbc_editorPaneDescription);
		
		JLabel labelNomAgence = new JLabel("Nom agance :");
		GridBagConstraints gbc_labelNomAgence = new GridBagConstraints();
		gbc_labelNomAgence.anchor = GridBagConstraints.EAST;
		gbc_labelNomAgence.insets = new Insets(0, 0, 5, 5);
		gbc_labelNomAgence.gridx = 6;
		gbc_labelNomAgence.gridy = 7;
		panel.add(labelNomAgence, gbc_labelNomAgence);
		
		fieldNameAgence = new JTextField();
		fieldNameAgence.addCaretListener(this);
		GridBagConstraints gbc_fieldNameAgence = new GridBagConstraints();
		gbc_fieldNameAgence.insets = new Insets(0, 0, 5, 5);
		gbc_fieldNameAgence.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldNameAgence.gridx = 7;
		gbc_fieldNameAgence.gridy = 7;
		panel.add(fieldNameAgence, gbc_fieldNameAgence);
		fieldNameAgence.setColumns(10);
		
		JLabel labelRib = new JLabel("RIB : ");
		GridBagConstraints gbc_labelRib = new GridBagConstraints();
		gbc_labelRib.anchor = GridBagConstraints.EAST;
		gbc_labelRib.insets = new Insets(0, 0, 5, 5);
		gbc_labelRib.gridx = 6;
		gbc_labelRib.gridy = 8;
		panel.add(labelRib, gbc_labelRib);
		
		fieldRib = new JTextField();
		fieldRib.addCaretListener(this);
		GridBagConstraints gbc_fieldRib = new GridBagConstraints();
		gbc_fieldRib.insets = new Insets(0, 0, 5, 5);
		gbc_fieldRib.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldRib.gridx = 7;
		gbc_fieldRib.gridy = 8;
		panel.add(fieldRib, gbc_fieldRib);
		fieldRib.setColumns(10);
		
		JLabel lebelAdress = new JLabel("Addresse");
		GridBagConstraints gbc_lebelAdress = new GridBagConstraints();
		gbc_lebelAdress.insets = new Insets(0, 0, 5, 5);
		gbc_lebelAdress.gridx = 1;
		gbc_lebelAdress.gridy = 9;
		panel.add(lebelAdress, gbc_lebelAdress);
		
		JLabel LabelRue = new JLabel("Rue : ");
		GridBagConstraints gbc_LabelRue = new GridBagConstraints();
		gbc_LabelRue.insets = new Insets(0, 0, 5, 5);
		gbc_LabelRue.anchor = GridBagConstraints.EAST;
		gbc_LabelRue.gridx = 1;
		gbc_LabelRue.gridy = 10;
		panel.add(LabelRue, gbc_LabelRue);
		
		fieldRue = new JTextField();
		fieldRue.addCaretListener(this);
		GridBagConstraints gbc_fieldRue = new GridBagConstraints();
		gbc_fieldRue.gridwidth = 3;
		gbc_fieldRue.insets = new Insets(0, 0, 5, 5);
		gbc_fieldRue.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldRue.gridx = 2;
		gbc_fieldRue.gridy = 10;
		panel.add(fieldRue, gbc_fieldRue);
		fieldRue.setColumns(10);
		
		JLabel labelNumRue = new JLabel("numero rue :");
		GridBagConstraints gbc_labelNumRue = new GridBagConstraints();
		gbc_labelNumRue.anchor = GridBagConstraints.EAST;
		gbc_labelNumRue.insets = new Insets(0, 0, 5, 5);
		gbc_labelNumRue.gridx = 6;
		gbc_labelNumRue.gridy = 10;
		panel.add(labelNumRue, gbc_labelNumRue);
		
		fieldNumRue = new JTextField();
		GridBagConstraints gbc_fieldNumRue = new GridBagConstraints();
		gbc_fieldNumRue.insets = new Insets(0, 0, 5, 5);
		gbc_fieldNumRue.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldNumRue.gridx = 7;
		gbc_fieldNumRue.gridy = 10;
		panel.add(fieldNumRue, gbc_fieldNumRue);
		fieldNumRue.setColumns(10);
		
		JLabel labelVille = new JLabel("Ville : ");
		GridBagConstraints gbc_labelVille = new GridBagConstraints();
		gbc_labelVille.anchor = GridBagConstraints.EAST;
		gbc_labelVille.insets = new Insets(0, 0, 5, 5);
		gbc_labelVille.gridx = 1;
		gbc_labelVille.gridy = 11;
		panel.add(labelVille, gbc_labelVille);
		
		comboVille = new JComboBox();
		comboVille.setModel(new DefaultComboBoxModel(Ville.values()));
		GridBagConstraints gbc_comboVille = new GridBagConstraints();
		gbc_comboVille.gridwidth = 3;
		gbc_comboVille.insets = new Insets(0, 0, 5, 5);
		gbc_comboVille.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboVille.gridx = 2;
		gbc_comboVille.gridy = 11;
		panel.add(comboVille, gbc_comboVille);
		
		JLabel labelCodePostal = new JLabel("Code postal :");
		GridBagConstraints gbc_labelCodePostal = new GridBagConstraints();
		gbc_labelCodePostal.anchor = GridBagConstraints.EAST;
		gbc_labelCodePostal.insets = new Insets(0, 0, 5, 5);
		gbc_labelCodePostal.gridx = 6;
		gbc_labelCodePostal.gridy = 11;
		panel.add(labelCodePostal, gbc_labelCodePostal);
		
		fieldCode = new JTextField();
		GridBagConstraints gbc_fieldCode = new GridBagConstraints();
		gbc_fieldCode.insets = new Insets(0, 0, 5, 5);
		gbc_fieldCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldCode.gridx = 7;
		gbc_fieldCode.gridy = 11;
		panel.add(fieldCode, gbc_fieldCode);
		fieldCode.setColumns(10);
		
		JLabel labelGouvernat = new JLabel("Gouvernat : ");
		GridBagConstraints gbc_labelGouvernat = new GridBagConstraints();
		gbc_labelGouvernat.anchor = GridBagConstraints.EAST;
		gbc_labelGouvernat.insets = new Insets(0, 0, 5, 5);
		gbc_labelGouvernat.gridx = 1;
		gbc_labelGouvernat.gridy = 12;
		panel.add(labelGouvernat, gbc_labelGouvernat);
		
		fieldGouvernat = new JTextField();
		fieldGouvernat.addCaretListener(this);
		GridBagConstraints gbc_fieldGouvernat = new GridBagConstraints();
		gbc_fieldGouvernat.gridwidth = 3;
		gbc_fieldGouvernat.insets = new Insets(0, 0, 5, 5);
		gbc_fieldGouvernat.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldGouvernat.gridx = 2;
		gbc_fieldGouvernat.gridy = 12;
		panel.add(fieldGouvernat, gbc_fieldGouvernat);
		fieldGouvernat.setColumns(10);
		
		fieldIdBanque = new JTextField();
		GridBagConstraints gbc_fieldIdBanque = new GridBagConstraints();
		gbc_fieldIdBanque.insets = new Insets(0, 0, 5, 5);
		gbc_fieldIdBanque.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldIdBanque.gridx = 6;
		gbc_fieldIdBanque.gridy = 12;
		panel.add(fieldIdBanque, gbc_fieldIdBanque);
		fieldIdBanque.setColumns(10);
		fieldIdBanque.setVisible(false);
		
		fieldIdAdress = new JTextField();
		GridBagConstraints gbc_fieldIdAdress = new GridBagConstraints();
		gbc_fieldIdAdress.insets = new Insets(0, 0, 5, 5);
		gbc_fieldIdAdress.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldIdAdress.gridx = 7;
		gbc_fieldIdAdress.gridy = 12;
		panel.add(fieldIdAdress, gbc_fieldIdAdress);
		fieldIdAdress.setColumns(10);
		fieldIdAdress.setVisible(false);
		
		JLabel labelPays = new JLabel("Pays :");
		GridBagConstraints gbc_labelPays = new GridBagConstraints();
		gbc_labelPays.anchor = GridBagConstraints.EAST;
		gbc_labelPays.insets = new Insets(0, 0, 5, 5);
		gbc_labelPays.gridx = 1;
		gbc_labelPays.gridy = 13;
		panel.add(labelPays, gbc_labelPays);
		
		comboPays = new JComboBox();
		comboPays.setModel(new DefaultComboBoxModel(new String[] {"Tunisie"}));
		GridBagConstraints gbc_comboPays = new GridBagConstraints();
		gbc_comboPays.gridwidth = 3;
		gbc_comboPays.insets = new Insets(0, 0, 5, 5);
		gbc_comboPays.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboPays.gridx = 2;
		gbc_comboPays.gridy = 13;
		panel.add(comboPays, gbc_comboPays);
		
		fieldIdSocial = new JTextField();
		GridBagConstraints gbc_fieldIdSocial = new GridBagConstraints();
		gbc_fieldIdSocial.insets = new Insets(0, 0, 5, 5);
		gbc_fieldIdSocial.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldIdSocial.gridx = 6;
		gbc_fieldIdSocial.gridy = 13;
		panel.add(fieldIdSocial, gbc_fieldIdSocial);
		fieldIdSocial.setColumns(10);
		fieldIdSocial.setVisible(false);
		
		fieldIdClient = new JTextField();
		GridBagConstraints gbc_fieldIdClient = new GridBagConstraints();
		gbc_fieldIdClient.insets = new Insets(0, 0, 5, 5);
		gbc_fieldIdClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldIdClient.gridx = 7;
		gbc_fieldIdClient.gridy = 13;
		panel.add(fieldIdClient, gbc_fieldIdClient);
		fieldIdClient.setColumns(10);
		fieldIdClient.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(addFournisseurtbtn)) {
			if(a && b && c && d && ee && f && g && h && j && k) {
			
		
			FournisseurServiceImpl fournisseurImpl = new FournisseurServiceImpl();
			Fournisseur fournisseur = new Fournisseur();
			Adress address = new Adress();
			CompteBancaire compte = new CompteBancaire();
			RaisonSocial social = new RaisonSocial();
			ArrayList<CompteBancaire> listCompte = new ArrayList();
			listCompte.add(compte);
			
			// -----------Address info
			address.setNumRue(Integer.parseInt(fieldNumRue.getText()));
			address.setLibelleRue( fieldRue.getText());
			address.setNomVille(comboVille.getSelectedItem().toString());
			address.setCodePostale(Integer.parseInt(fieldCode.getText()));
			address.setGouvernat(fieldGouvernat.getText());
			address.setPays(comboPays.getSelectedItem().toString());
			// -----------------------------------------
			// --------------------------CompteBancaire info 
			
			compte.setNameBanque(fieldNameBanque.getText());
			compte.setAgence(fieldNameAgence.getText());
			compte.setNumRib(Integer.parseInt(fieldRib.getText()));
			
			/// --------------------------------------------------
			// ------------------info Social
			//-----------------------------------------------
			social.setNom(fieldLastName.getText());
			social.setPrenom(fieldName.getText());
			social.setSexe("homme");
			
			//
			fournisseur.setMatricule(fieldMatricule.getText());
			if(comboBoxType.getSelectedItem().toString()==TypeEntreprise.PHYSIQUE.toString()) {
				fournisseur.setType(TypeEntreprise.PHYSIQUE);
			}
			else {
				fournisseur.setType(TypeEntreprise.MORALE);
			}
	
			fournisseur.setDescription(editorPaneDescription.getText());
			fournisseur.setTelFix(Integer.parseInt(fielTel.getText()));
			fournisseur.setTelMobile(Integer.parseInt(fieldMobile.getText()));
			fournisseur.setEmail(fieldEmail.getText());
			fournisseur.setWebSite(fieldWebSIte.getText());
			fournisseur.setAdresse(address);
			fournisseur.setCompteBancaires(listCompte);
			fournisseur.setRaisonSocial(social);
			fournisseur.setTva_assuj(checkBoxTva.isSelected());
			
			
			
			fournisseurImpl.save(fournisseur);
			((DefaultTableModel)table.getModel()).setNumRows(0);
			 FournisseurServiceImpl fournisseurServ = new FournisseurServiceImpl();
		        for(int i =0 ; i<fournisseurServ.getAll().size(); i++) {
		        	 model.addRow(new String[] {
		        			 fournisseurServ.getAll().get(i).getRaisonSocial().getNom(),
		        			 fournisseurServ.getAll().get(i).getRaisonSocial().getPrenom(),
		        			 fournisseurServ.getAll().get(i).getMatricule(),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getTelMobile()),
		        			 fournisseurServ.getAll().get(i).getEmail(),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getTelFix()),
		        			 fournisseurServ.getAll().get(i).getAdresse().getPays(),
		        			 fournisseurServ.getAll().get(i).getAdresse().getLibelleRue(),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getAdresse().getNumRue()),
		        			 fournisseurServ.getAll().get(i).getAdresse().getNomVille(),
		        			 fournisseurServ.getAll().get(i).getAdresse().getGouvernat(),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getAdresse().getCodePostale()),
		        			 fournisseurServ.getAll().get(i).getType().toString(),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getCompteBancaires().get(0).getNumRib()),
		        			 fournisseurServ.getAll().get(i).getCompteBancaires().get(0).getNameBanque(),
		        			 fournisseurServ.getAll().get(i).getCompteBancaires().get(0).getAgence(),
		        			 fournisseurServ.getAll().get(i).getRaisonSocial().getSexe(),
		        			 fournisseurServ.getAll().get(i).getWebSite(),
		        			 fournisseurServ.getAll().get(i).getDescription(),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getId()),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getCompteBancaires().get(0).getId()),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getRaisonSocial().getId()),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getAdresse().getId()),
		        			 Boolean.toString(fournisseurServ.getAll().get(i).isTva_assuj())
		        			 
		        			 });
		        }
			
		        model.fireTableDataChanged();
				toaster.success("inormation Fournisseur Ajouté");
			
			}
			else {
				toaster.error("verifier les cases rouge");
			}
		
		}
		
		if(e.getSource().equals(modifyFournisseurBtn)){
			if(a && b && c && d && ee && f && g && h && j && k) {
			FournisseurServiceImpl fournisseurImpl = new FournisseurServiceImpl();
			Fournisseur fournisseur = new Fournisseur();
			Adress address = new Adress();
			CompteBancaire compte = new CompteBancaire();
			RaisonSocial social = new RaisonSocial();
			ArrayList<CompteBancaire> listCompte = new ArrayList();
			listCompte.add(compte);
			
			// -----------Address info
			address.setId(Integer.parseInt(fieldIdAdress.getText()));
			address.setNumRue(Integer.parseInt(fieldNumRue.getText()));
			address.setLibelleRue( fieldRue.getText());
			address.setNomVille(comboVille.getSelectedItem().toString());
			address.setCodePostale(Integer.parseInt(fieldCode.getText()));
			address.setGouvernat(fieldGouvernat.getText());
			address.setPays(comboPays.getSelectedItem().toString());
			// -----------------------------------------
			// --------------------------CompteBancaire info 
			
			compte.setId(Integer.parseInt(fieldIdBanque.getText()));
			compte.setNameBanque(fieldNameBanque.getText());
			compte.setAgence(fieldNameAgence.getText());
			compte.setNumRib(Integer.parseInt(fieldRib.getText()));
			
			/// --------------------------------------------------
			// ------------------info Social
			//-----------------------------------------------
			social.setId(Integer.parseInt(fieldIdSocial.getText()));
			social.setNom(fieldLastName.getText());
			social.setPrenom(fieldName.getText());
			social.setSexe("homme");
			
			//
			fournisseur.setMatricule(fieldMatricule.getText());
			if(comboBoxType.getSelectedItem().toString()=="PHYSIQUE") {
				fournisseur.setType(TypeEntreprise.PHYSIQUE);
			}
			else {
				fournisseur.setType(TypeEntreprise.MORALE);
			}
	
			fournisseur.setDescription(editorPaneDescription.getText());
			fournisseur.setTelFix(Integer.parseInt(fielTel.getText()));
			fournisseur.setTelMobile(Integer.parseInt(fieldMobile.getText()));
			fournisseur.setEmail(fieldEmail.getText());
			fournisseur.setWebSite(fieldWebSIte.getText());
			fournisseur.setAdresse(address);
			fournisseur.setCompteBancaires(listCompte);
			fournisseur.setRaisonSocial(social);
			fournisseur.setId(Integer.parseInt(fieldIdClient.getText()));
			fournisseur.setTva_assuj(checkBoxTva.isSelected());
			fournisseurImpl.update(fournisseur);
			((DefaultTableModel)table.getModel()).setNumRows(0);
			 FournisseurServiceImpl fournisseurServ = new FournisseurServiceImpl();
		        for(int i =0 ; i<fournisseurServ.getAll().size(); i++) {
		        	 model.addRow(new String[] {
		        			 fournisseurServ.getAll().get(i).getRaisonSocial().getNom(),
		        			 fournisseurServ.getAll().get(i).getRaisonSocial().getPrenom(),
		        			 fournisseurServ.getAll().get(i).getMatricule(),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getTelMobile()),
		        			 fournisseurServ.getAll().get(i).getEmail(),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getTelFix()),
		        			 fournisseurServ.getAll().get(i).getAdresse().getPays(),
		        			 fournisseurServ.getAll().get(i).getAdresse().getLibelleRue(),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getAdresse().getNumRue()),
		        			 fournisseurServ.getAll().get(i).getAdresse().getNomVille(),
		        			 fournisseurServ.getAll().get(i).getAdresse().getGouvernat(),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getAdresse().getCodePostale()),
		        			 fournisseurServ.getAll().get(i).getType().toString(),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getCompteBancaires().get(0).getNumRib()),
		        			 fournisseurServ.getAll().get(i).getCompteBancaires().get(0).getNameBanque(),
		        			 fournisseurServ.getAll().get(i).getCompteBancaires().get(0).getAgence(),
		        			 fournisseurServ.getAll().get(i).getRaisonSocial().getSexe(),
		        			 fournisseurServ.getAll().get(i).getWebSite(),
		        			 fournisseurServ.getAll().get(i).getDescription(),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getId()),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getCompteBancaires().get(0).getId()),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getRaisonSocial().getId()),
		        			 Integer.toString(fournisseurServ.getAll().get(i).getAdresse().getId()),
		        			 Boolean.toString(fournisseurServ.getAll().get(i).isTva_assuj())
		        			 
		        			 });
		        }
			
		        model.fireTableDataChanged();
				toaster.success("inormation Fournisseur Ajouté");
			
			}
			else {
				toaster.error("verifier les cases rouge");
			}
		}
		if(e.getSource().equals(deleteDeleteBtn)) {
			FournisseurServiceImpl fournisseurImpl = new FournisseurServiceImpl();
			Fournisseur fournisseur = new Fournisseur();
			Adress address = new Adress();
			CompteBancaire compte = new CompteBancaire();
			RaisonSocial social = new RaisonSocial();
			ArrayList<CompteBancaire> listCompte = new ArrayList();
			listCompte.add(compte);
			
			address.setId(Integer.parseInt(fieldIdAdress.getText()));
			compte.setId(Integer.parseInt(fieldIdBanque.getText()));
			social.setId(Integer.parseInt(fieldIdSocial.getText()));
			fournisseur.setId(Integer.parseInt(fieldIdClient.getText()));
			fournisseur.setAdresse(address);
			fournisseur.setCompteBancaires(listCompte);
			fournisseur.setRaisonSocial(social);
			
			JOptionPane confirmPan = new JOptionPane();
			int a=confirmPan.showConfirmDialog(this,"Are you sure deleting this supplier?");
			if(a==confirmPan.YES_OPTION){  
				fournisseurImpl.delete(fournisseur);
				toaster.error("fournisseur deleted");
			} 
			modifyFournisseurBtn.setEnabled(false);
			deleteDeleteBtn.setEnabled(false);
			
		
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
        
        fieldLastName.setText((String) val[0]);
        fieldName.setText((String) val[1]);
        fieldMatricule.setText((String) val[2]);
        fieldMobile.setText((String) val[3]);
        fieldEmail.setText((String) val[4]);
        fielTel.setText((String) val[5]);
        fieldRue.setText((String) val[7]);
        fieldNumRue.setText((String) val[8]);
        fieldGouvernat.setText((String) val[10]);
        fieldCode.setText((String) val[11]);
        fieldRib.setText((String) val[13]);
        fieldNameBanque.setText((String) val[14]);
        fieldNameAgence.setText((String) val[15]);
        
        fieldWebSIte.setText((String) val[17]);
        editorPaneDescription.setText((String) val[18]);
        
        fieldIdClient.setText((String) val[19]);
        fieldIdBanque.setText((String) val[20]);
        fieldIdSocial.setText((String) val[21]);
        fieldIdAdress.setText((String) val[22]);
        if(((String) val[23]) == "true") {
        	checkBoxTva.setSelected(true);
        }
        else
        {
        	checkBoxTva.setSelected(false);
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
		if(e.getSource().equals(fieldEmail)){
			String regex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(fieldEmail.getText());
			if(matcher.matches()) {
				fieldEmail.setBorder(new LineBorder(Color.green,1));
				a = true;
			}
			else
			{
				fieldEmail.setBorder(new LineBorder(Color.red,1));
				a = false;
			}
		}
		if(e.getSource().equals(fieldMobile)){
			String regex = "((?:\\+|00)[17](?: |\\-)?|(?:\\+|00)[1-9]\\d{0,2}(?: |\\-)?|(?:\\+|00)1\\-\\d{3}(?: |\\-)?)?(0\\d|\\([0-9]{3}\\)|[1-9]{0,3})(?:((?: |\\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\\-)[0-9]{3}(?: |\\-)[0-9]{4})|([0-9]{7}))";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(fieldMobile.getText());
			if(matcher.matches()) {
				fieldMobile.setBorder(new LineBorder(Color.green,1));
				b = true;
			}
			else
			{
				fieldMobile.setBorder( new LineBorder(Color.red,1));
				b=false;
			}
		}
		if(e.getSource().equals(fielTel)){
			String regex = "((?:\\+|00)[17](?: |\\-)?|(?:\\+|00)[1-9]\\d{0,2}(?: |\\-)?|(?:\\+|00)1\\-\\d{3}(?: |\\-)?)?(0\\d|\\([0-9]{3}\\)|[1-9]{0,3})(?:((?: |\\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\\-)[0-9]{3}(?: |\\-)[0-9]{4})|([0-9]{7}))";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(fielTel.getText());
			if(matcher.matches()) {
				fielTel.setBorder(new LineBorder(Color.green,1));
				c = true;
			}
			else
			{
				fielTel.setBorder(new LineBorder(Color.red,1));
				c=false;
			}
		}
		if(e.getSource().equals(fieldWebSIte)){
			String regex = "(https?:\\/\\/)?(www\\.)[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)|(https?:\\/\\/)?(www\\.)?(?!ww)[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(fieldWebSIte.getText());
			if(matcher.matches()) {
				fieldWebSIte.setBorder(new LineBorder(Color.green,1));
				d  = true;
			}
			else
			{
				fieldWebSIte.setBorder(new LineBorder(Color.red,1));
				d = false;
			}
		}
		
		if(e.getSource().equals(fieldName)){
			String regex = "([a-zA-Z]{3,20}\s*)+";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(fieldName.getText());
			if(matcher.matches()) {
				fieldName.setBorder(new LineBorder(Color.green,1));
				ee =  true;
			}
			else
			{
				fieldName.setBorder(new LineBorder(Color.red,1));
				ee = false;
			}
		}
		
		if(e.getSource().equals(fieldLastName)){
			String regex = "([a-zA-Z]{3,20}\s*)+";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(fieldLastName.getText());
			if(matcher.matches()) {
				fieldLastName.setBorder(new LineBorder(Color.green,1));
				f = true;
			}
			else
			{
				fieldLastName.setBorder(new LineBorder(Color.red,1));
				f = false;
			}
		}
		
		if(e.getSource().equals(fieldNameBanque)){
			String regex = "([a-zA-Z]{3,20}\s*)+";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(fieldNameBanque.getText());
			if(matcher.matches()) {
				fieldNameBanque.setBorder(new LineBorder(Color.green,1));
				g = true;
			}
			else
			{
				fieldNameBanque.setBorder(new LineBorder(Color.red,1));
				g  = false;
			}
		}
		
		if(e.getSource().equals(fieldNameAgence)){
			String regex = "([a-zA-Z]{3,20}\s*)+";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(fieldNameAgence.getText());
			if(matcher.matches()) {
				fieldNameAgence.setBorder(new LineBorder(Color.green,1));
				h = true;
			}
			else
			{
				fieldNameAgence.setBorder(new LineBorder(Color.red,1));
				h 	 = false;
			}
		}
		if(e.getSource().equals(fieldRue)){
			String regex = "([a-zA-Z]{3,20}\s*)+";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(fieldRue.getText());
			if(matcher.matches()) {
				fieldRue.setBorder(new LineBorder(Color.green,1));
				j = true;
			}
			else
			{
				fieldMatricule.setBorder(new LineBorder(Color.red,1));
				j = false;
			}
		}
		
		if(e.getSource().equals(fieldRib)){
			String regex = "^(0[1-9]|[1-8])([0-9]{9}\s*)$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(fieldRib.getText());
			if(matcher.matches()) {
				fieldRib.setBorder(new LineBorder(Color.green,1));
				k = true;
			}
			else
			{
				fieldRib.setBorder(new LineBorder(Color.red,1));
				k = false;
			}
		}
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource().equals(fieldMatricule)) {
			if (fieldMatricule.getText().equals(UIUtils.MATRICULE_FISCALE)) {
	            fieldMatricule.setText("");
	        }

	
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource().equals(fieldMatricule)) {
			
			String regex = "[0-9]{8}[/]{1}[A-Z]{1}[/]{1}[A-Z]{1}[/]{1}[A-Z]{1}[/]{1}[0-9]{3}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(fieldMatricule.getText());
			if (matcher.matches()) {
				fieldMatricule.setBorder(new LineBorder(Color.green,1));
				mm = true;
	        }
			else {
				fieldMatricule.setBorder(new LineBorder(Color.red,1));
				mm = false;
			}

			
		}
			
      }
}
