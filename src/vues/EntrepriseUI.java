package vues;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.FieldView;

import Toaster.Toaster;
import models.Adress;
import models.Client;
import models.CompteBancaire;
import models.Entreprise;
import models.Fournisseur;
import models.RaisonSocial;
import models.TypeEntreprise;
import services.ClientServiceImpl;
import services.EntrepriseServiceImpl;
import services.FournisseurServiceImpl;

import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;

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
import java.awt.Toolkit;

public class EntrepriseUI extends JFrame implements ActionListener {

	private JPanel contentPane;
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
	private JButton addEntreprisebtn,modifyFournisseurBtn,cancelBtn;
	private JTextField fieldIdClient;
	private JTextField fieldIdSocial;
	private JTextField fieldIdAdress;
	private JTextField fieldIdBanque;
	JComboBox comboBoxType;
	Dashboard singletonDashboard = null ;
	private final Toaster toaster;

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

	JFrame parentFrame;
	
	public EntrepriseUI(Dashboard dash) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EntrepriseUI.class.getResource("/Gambar/dragon.png")));
		setTitle("LokyErp - devis");
		
		singletonDashboard = dash;
		this.parentFrame = parentFrame;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1036, 639);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		toaster = new Toaster(contentPane);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 204, 255));
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JLabel lblFournisseurManagement = new JLabel("Entreprise Informations");
		lblFournisseurManagement.setIcon(new ImageIcon(ClientUi.class.getResource("/Gambar/file-viewer.png")));
		lblFournisseurManagement.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 17));
		topPanel.add(lblFournisseurManagement);
		DefaultTableModel model = new DefaultTableModel();
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
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		addEntreprisebtn = new JButton("add");
		buttonPanel.add(addEntreprisebtn);
		addEntreprisebtn.addActionListener(this);
		
		modifyFournisseurBtn = new JButton("update");
		modifyFournisseurBtn.addActionListener(this);
		buttonPanel.add(modifyFournisseurBtn);
		
		cancelBtn = new JButton("cancel");
		cancelBtn.addActionListener(this);
		buttonPanel.add(cancelBtn);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
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
		
		fieldMatricule = new JTextField();
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
		if(e.getSource().equals(addEntreprisebtn)) {
			
		
			EntrepriseServiceImpl entrepriseImpl = new EntrepriseServiceImpl();
			Entreprise entreprise = new Entreprise();
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
			entreprise.setMatricule(fieldMatricule.getText());
			if(comboBoxType.getSelectedItem().toString()==TypeEntreprise.PHYSIQUE.toString()) {
				entreprise.setType(TypeEntreprise.PHYSIQUE);
			}
			else {
				entreprise.setType(TypeEntreprise.MORALE);
			}
	
			entreprise.setDescription(editorPaneDescription.getText());
			entreprise.setTelFix(Integer.parseInt(fielTel.getText()));
			entreprise.setTelMobile(Integer.parseInt(fieldMobile.getText()));
			entreprise.setEmail(fieldEmail.getText());
			entreprise.setWebSite(fieldWebSIte.getText());
			entreprise.setAdresse(address);
			entreprise.setCompteBancaires(listCompte);
			entreprise.setRaisonSocial(social);
			entreprise.setTva_assuj(checkBoxTva.isSelected());
			
			
			
			Boolean done = entrepriseImpl.save(entreprise);
			
			if(done) {
				this.singletonDashboard.setVisible(true);
				this.dispose();
			}
				
			else
			{
				toaster.error("Wrong information");
			}
		
		
		}
		
		if(e.getSource().equals(modifyFournisseurBtn)){
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
		}
		if(e.getSource().equals(cancelBtn)) {
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
			fournisseurImpl.delete(fournisseur);
		
		}
		
		
	}

	

}
