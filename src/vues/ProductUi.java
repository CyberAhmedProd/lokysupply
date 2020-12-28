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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class ProductUi extends JFrame implements ActionListener,MouseListener {

	private JPanel contentPane;
	private JTable table;
	private JTextField fieldMatricule;
	private JTextField fieldLastName;
	private JTextField fieldName;
	private JTextField fieldTva;
	private JTextField fieldRue;
	private JComboBox comboVille;
	private JButton addFournisseurtbtn,modifyFournisseurBtn,deleteDeleteBtn;
	private JTextField fieldIdClient;
	private JTextField fieldIdSocial;

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
	public ProductUi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1036, 639);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(204, 0, 102));
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JLabel lblFournisseurManagement = new JLabel("Product Management");
		lblFournisseurManagement.setIcon(new ImageIcon(ClientUi.class.getResource("/Gambar/file-viewer.png")));
		lblFournisseurManagement.setFont(new Font("DejaVu Math TeX Gyre", Font.BOLD, 17));
		topPanel.add(lblFournisseurManagement);
		
		JPanel vuePanel = new JPanel();
		contentPane.add(vuePanel, BorderLayout.WEST);
		vuePanel.setLayout(new BoxLayout(vuePanel, BoxLayout.Y_AXIS));
		
		JLabel labelFournisseurList = new JLabel("Product's List");
		labelFournisseurList.setFont(new Font("DejaVu Serif", Font.BOLD, 12));
		vuePanel.add(labelFournisseurList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		vuePanel.add(scrollPane);
		DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ref");
        model.addColumn("designation");
        model.addColumn("");
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
        			 Integer.toString(fournisseurServ.getAll().get(i).getAdresse().getId())
        			 
        			 });
        }
       
 
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(model);
		table.addMouseListener(this);
			/*new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"nom", "prenom", "matricule", "mobile", "email", "tel", "adress"
			}
		));*/
		scrollPane.setViewportView(table);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		addFournisseurtbtn = new JButton("add");
		buttonPanel.add(addFournisseurtbtn);
		addFournisseurtbtn.addActionListener(this);
		
		modifyFournisseurBtn = new JButton("update");
		modifyFournisseurBtn.addActionListener(this);
		buttonPanel.add(modifyFournisseurBtn);
		
		deleteDeleteBtn = new JButton("delete");
		deleteDeleteBtn.addActionListener(this);
		buttonPanel.add(deleteDeleteBtn);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblFournisseur = new JLabel("Product");
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
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel labelRef = new JLabel("Ref  :");
		GridBagConstraints gbc_labelRef = new GridBagConstraints();
		gbc_labelRef.insets = new Insets(0, 0, 5, 5);
		gbc_labelRef.gridx = 1;
		gbc_labelRef.gridy = 1;
		panel.add(labelRef, gbc_labelRef);
		
		fieldMatricule = new JTextField();
		GridBagConstraints gbc_fieldMatricule = new GridBagConstraints();
		gbc_fieldMatricule.gridwidth = 3;
		gbc_fieldMatricule.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldMatricule.insets = new Insets(0, 0, 5, 5);
		gbc_fieldMatricule.gridx = 2;
		gbc_fieldMatricule.gridy = 1;
		panel.add(fieldMatricule, gbc_fieldMatricule);
		fieldMatricule.setColumns(10);
		
		JLabel labelDesignation = new JLabel("Designation :");
		GridBagConstraints gbc_labelDesignation = new GridBagConstraints();
		gbc_labelDesignation.anchor = GridBagConstraints.WEST;
		gbc_labelDesignation.insets = new Insets(0, 0, 5, 5);
		gbc_labelDesignation.gridx = 6;
		gbc_labelDesignation.gridy = 1;
		panel.add(labelDesignation, gbc_labelDesignation);
		
		fieldLastName = new JTextField();
		GridBagConstraints gbc_fieldLastName = new GridBagConstraints();
		gbc_fieldLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldLastName.insets = new Insets(0, 0, 5, 5);
		gbc_fieldLastName.gridx = 7;
		gbc_fieldLastName.gridy = 1;
		panel.add(fieldLastName, gbc_fieldLastName);
		fieldLastName.setColumns(10);
		
		JLabel labelUnit = new JLabel("Unit :");
		GridBagConstraints gbc_labelUnit = new GridBagConstraints();
		gbc_labelUnit.insets = new Insets(0, 0, 5, 5);
		gbc_labelUnit.gridx = 1;
		gbc_labelUnit.gridy = 3;
		panel.add(labelUnit, gbc_labelUnit);
		
		JComboBox comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"PYHSIUE", "MORALE"}));
		GridBagConstraints gbc_comboBoxType = new GridBagConstraints();
		gbc_comboBoxType.gridwidth = 3;
		gbc_comboBoxType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxType.gridx = 2;
		gbc_comboBoxType.gridy = 3;
		panel.add(comboBoxType, gbc_comboBoxType);
		
		JLabel labelMinStock = new JLabel("Min Stock : ");
		GridBagConstraints gbc_labelMinStock = new GridBagConstraints();
		gbc_labelMinStock.anchor = GridBagConstraints.WEST;
		gbc_labelMinStock.insets = new Insets(0, 0, 5, 5);
		gbc_labelMinStock.gridx = 6;
		gbc_labelMinStock.gridy = 3;
		panel.add(labelMinStock, gbc_labelMinStock);
		
		fieldName = new JTextField();
		GridBagConstraints gbc_fieldName = new GridBagConstraints();
		gbc_fieldName.fill = GridBagConstraints.BOTH;
		gbc_fieldName.insets = new Insets(0, 0, 5, 5);
		gbc_fieldName.gridx = 7;
		gbc_fieldName.gridy = 3;
		panel.add(fieldName, gbc_fieldName);
		fieldName.setColumns(10);
		
		JLabel labelFournisseur = new JLabel("Fournisseur  : ");
		GridBagConstraints gbc_labelFournisseur = new GridBagConstraints();
		gbc_labelFournisseur.anchor = GridBagConstraints.EAST;
		gbc_labelFournisseur.insets = new Insets(0, 0, 5, 5);
		gbc_labelFournisseur.gridx = 1;
		gbc_labelFournisseur.gridy = 5;
		panel.add(labelFournisseur, gbc_labelFournisseur);
		
		comboVille = new JComboBox();
		comboVille.setModel(new DefaultComboBoxModel(new String[] {"Monastir"}));
		GridBagConstraints gbc_comboVille = new GridBagConstraints();
		gbc_comboVille.gridwidth = 3;
		gbc_comboVille.insets = new Insets(0, 0, 5, 5);
		gbc_comboVille.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboVille.gridx = 2;
		gbc_comboVille.gridy = 5;
		panel.add(comboVille, gbc_comboVille);
		
		JLabel labelFamille = new JLabel("Family : ");
		GridBagConstraints gbc_labelFamille = new GridBagConstraints();
		gbc_labelFamille.anchor = GridBagConstraints.WEST;
		gbc_labelFamille.insets = new Insets(0, 0, 5, 5);
		gbc_labelFamille.gridx = 6;
		gbc_labelFamille.gridy = 5;
		panel.add(labelFamille, gbc_labelFamille);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 7;
		gbc_comboBox.gridy = 5;
		panel.add(comboBox, gbc_comboBox);
		
		JLabel labelPrice = new JLabel("Price  : ");
		GridBagConstraints gbc_labelPrice = new GridBagConstraints();
		gbc_labelPrice.anchor = GridBagConstraints.EAST;
		gbc_labelPrice.insets = new Insets(0, 0, 5, 5);
		gbc_labelPrice.gridx = 1;
		gbc_labelPrice.gridy = 7;
		panel.add(labelPrice, gbc_labelPrice);
		
		fieldRue = new JTextField();
		GridBagConstraints gbc_fieldRue = new GridBagConstraints();
		gbc_fieldRue.gridwidth = 3;
		gbc_fieldRue.insets = new Insets(0, 0, 5, 5);
		gbc_fieldRue.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldRue.gridx = 2;
		gbc_fieldRue.gridy = 7;
		panel.add(fieldRue, gbc_fieldRue);
		fieldRue.setColumns(10);
		
		JLabel labelTva = new JLabel("TVA  :");
		GridBagConstraints gbc_labelTva = new GridBagConstraints();
		gbc_labelTva.anchor = GridBagConstraints.WEST;
		gbc_labelTva.insets = new Insets(0, 0, 5, 5);
		gbc_labelTva.gridx = 6;
		gbc_labelTva.gridy = 7;
		panel.add(labelTva, gbc_labelTva);
		
		fieldTva = new JTextField();
		fieldTva.setEnabled(false);
		GridBagConstraints gbc_fieldTva = new GridBagConstraints();
		gbc_fieldTva.insets = new Insets(0, 0, 5, 5);
		gbc_fieldTva.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldTva.gridx = 7;
		gbc_fieldTva.gridy = 7;
		panel.add(fieldTva, gbc_fieldTva);
		fieldTva.setColumns(10);
		
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
			compte.setAgence(fieldTva.getText());
			compte.setNumRib(Integer.parseInt(fieldRib.getText()));
			
			/// --------------------------------------------------
			// ------------------info Social
			//-----------------------------------------------
			social.setNom(fieldLastName.getText());
			social.setPrenom(fieldName.getText());
			social.setSexe("homme");
			
			//
			fournisseur.setMatricule(fieldMatricule.getText());
			if(comboVille.getSelectedItem().toString()=="PYHSIUE") {
				fournisseur.setType(TypeEntreprise.PYHSIUE);
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
			
			
			
			fournisseurImpl.save(fournisseur);
		
		
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
			compte.setAgence(fieldTva.getText());
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
			if(comboVille.getSelectedItem().toString()=="PHYSIQUE") {
				fournisseur.setType(TypeEntreprise.PYHSIUE);
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
			fournisseurImpl.update(fournisseur);
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
			fournisseurImpl.delete(fournisseur);
		
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
        fieldTva.setText((String) val[15]);
        
        fieldWebSIte.setText((String) val[17]);
        editorPaneDescription.setText((String) val[18]);
        
        fieldIdClient.setText((String) val[19]);
        fieldIdBanque.setText((String) val[20]);
        fieldIdSocial.setText((String) val[21]);
        fieldIdAdress.setText((String) val[22]);
       
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
