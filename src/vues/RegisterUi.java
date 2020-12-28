package vues;

import Toaster.Toaster;
import Utils.*;
import models.User;
import services.UserServiceImpl;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.*;

public class RegisterUi extends JFrame{
	private final Toaster toaster;
	JPanel loginPanel;
	private TextFieldPassword passwordField;
	private TextFieldUsername usernameField,userLastNameField,emailField;

    public RegisterUi(JPanel loginPanel) throws IOException {
    	this.loginPanel = loginPanel;
        JPanel mainJPanel = getMainJPanel();

        addLogo(mainJPanel);

        addSeparator(mainJPanel);

        addUsernameTextField(mainJPanel);

        addPasswordTextField(mainJPanel);
        
        addNameTextField(mainJPanel);
        
        addLastNameTextField(mainJPanel);

        addLoginButton(mainJPanel);
        
        addReturnLoginButton(mainJPanel);
        //addForgotPasswordButton(mainJPanel);

        addRegisterButton(mainJPanel);

        this.add(mainJPanel);
        this.pack();
        this.setVisible(true);
        this.toFront();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        toaster = new Toaster(mainJPanel);
    }

    private JPanel getMainJPanel() {
        this.setUndecorated(true);

        Dimension size = new Dimension(800, 400);

        JPanel panel1 = new JPanel();
        panel1.setSize(size);
        panel1.setPreferredSize(size);
        panel1.setBackground(UIUtils.COLOR_BACKGROUND);
        panel1.setLayout(null);

        MouseAdapter ma = new MouseAdapter() {
            int lastX, lastY;

            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getXOnScreen();
                lastY = e.getYOnScreen();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(getLocationOnScreen().x + x - lastX, getLocationOnScreen().y + y - lastY);
                lastX = x;
                lastY = y;
            }
        };

        panel1.addMouseListener(ma);
        panel1.addMouseMotionListener(ma);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        return panel1;
    }

    private void addSeparator(JPanel panel1) {
        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.VERTICAL);
        separator1.setForeground(UIUtils.COLOR_OUTLINE);
        panel1.add(separator1);
        separator1.setBounds(310, 20, 1, 340);
    }

    private void addLogo(JPanel panel1) throws IOException {
        JLabel label1 = new JLabel();
        label1.setFocusable(false);
        this.resizee("hobbit.png","hobbitre.png",200,200);
        label1.setIcon(new ImageIcon("hobbitre.png"));
        panel1.add(label1);
        label1.setBounds(55, 146, 200, 110);
    }
    private void resizee(String inputImagePath,
            String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
   
    private void addUsernameTextField(JPanel panel1) {
        emailField = new TextFieldUsername();
        emailField.setText(UIUtils.PLACEHOLDER_TEXT_USERNAME);
        emailField.setForeground(UIUtils.COLOR_OUTLINE);
        emailField.setBorderColor(UIUtils.COLOR_OUTLINE);

        emailField.setBounds(423, 30, 250, 34);
        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailField.getText().equals(UIUtils.PLACEHOLDER_TEXT_USERNAME)) {
                	emailField.setText("");
                }
                emailField.setForeground(Color.white);
                emailField.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailField.getText().isEmpty()) {
                	emailField.setText(UIUtils.PLACEHOLDER_TEXT_USERNAME);
                }
                emailField.setForeground(UIUtils.COLOR_OUTLINE);
                emailField.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });

        panel1.add(emailField);
    }

    private void addPasswordTextField(JPanel panel1) {
        passwordField = new TextFieldPassword();
        passwordField.setText("Password");
        passwordField.setForeground(UIUtils.COLOR_OUTLINE);
        passwordField.setBorderColor(UIUtils.COLOR_OUTLINE);
        passwordField.setBounds(423, 80, 250, 34);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.setForeground(Color.white);
                passwordField.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                passwordField.setForeground(UIUtils.COLOR_OUTLINE);
                passwordField.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER)
                    RegisterEventHandler();
            }
        });

        panel1.add(passwordField);
    }
    private void addNameTextField(JPanel panel1) {
        usernameField = new TextFieldUsername();
        usernameField.setText(UIUtils.PLACEHOLDER_TEXT_NAME);
        usernameField.setForeground(UIUtils.COLOR_OUTLINE);
        usernameField.setBorderColor(UIUtils.COLOR_OUTLINE);

        usernameField.setBounds(423, 130, 250, 34);
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals(UIUtils.PLACEHOLDER_TEXT_NAME)) {
                    usernameField.setText("");
                }
                usernameField.setForeground(Color.white);
                usernameField.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText(UIUtils.PLACEHOLDER_TEXT_NAME);
                }
                usernameField.setForeground(UIUtils.COLOR_OUTLINE);
                usernameField.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });

        panel1.add(usernameField);
    }
    
    private void addLastNameTextField(JPanel panel1) {
        userLastNameField = new TextFieldUsername();
        userLastNameField.setText(UIUtils.PLACEHOLDER_TEXT_LAST_NAME);
        userLastNameField.setForeground(UIUtils.COLOR_OUTLINE);
        userLastNameField.setBorderColor(UIUtils.COLOR_OUTLINE);
        userLastNameField.setBounds(423, 180, 250, 34);
        userLastNameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userLastNameField.getText().equals(UIUtils.PLACEHOLDER_TEXT_LAST_NAME)) {
                	userLastNameField.setText("");
                }
                userLastNameField.setForeground(Color.white);
                userLastNameField.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userLastNameField.getText().isEmpty()) {
                	userLastNameField.setText(UIUtils.PLACEHOLDER_TEXT_LAST_NAME);
                }
                userLastNameField.setForeground(UIUtils.COLOR_OUTLINE);
                userLastNameField.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });

        panel1.add(userLastNameField);
    }

    private void addLoginButton(JPanel panel1) {
        final Color[] loginButtonColors = {UIUtils.COLOR_INTERACTIVE, Color.white};

        JLabel loginButton = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = UIUtils.get2dGraphics(g);
                super.paintComponent(g2);

                Insets insets = getInsets();
                int w = getWidth() - insets.left - insets.right;
                int h = getHeight() - insets.top - insets.bottom;
                g2.setColor(loginButtonColors[0]);
                g2.fillRoundRect(insets.left, insets.top, w, h, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);

                FontMetrics metrics = g2.getFontMetrics(UIUtils.FONT_GENERAL_UI);
                int x2 = (getWidth() - metrics.stringWidth(UIUtils.BUTTON_TEXT_REGISTER)) / 2;
                int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.setFont(UIUtils.FONT_GENERAL_UI);
                g2.setColor(loginButtonColors[1]);
                g2.drawString(UIUtils.BUTTON_TEXT_REGISTER, x2, y2);
            }
        };

        loginButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                RegisterEventHandler();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                loginButtonColors[0] = UIUtils.COLOR_INTERACTIVE_DARKER;
                loginButtonColors[1] = UIUtils.OFFWHITE;
                loginButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButtonColors[0] = UIUtils.COLOR_INTERACTIVE;
                loginButtonColors[1] = Color.white;
                loginButton.repaint();
            }
        });

        loginButton.setBackground(UIUtils.COLOR_BACKGROUND);
        loginButton.setBounds(423, 247, 250, 44);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(loginButton);
    }

  
    private void addReturnLoginButton(JPanel panel1) {
        panel1.add(new HyperlinkText(UIUtils.BUTTON_TEXT_RETURN_LOGIN, 423, 300, () -> {
            this.loginPanel.setVisible(true);
            this.dispose();
        }));
    }

    private void addRegisterButton(JPanel panel1) {
        panel1.add(new HyperlinkText(UIUtils.BUTTON_TEXT_REGISTER, 631, 300, () -> {
            toaster.success("Register event");
        }));
    }
    private void addDisposeButton(JPanel panel1) {
        panel1.add(new HyperlinkText(UIUtils.BUTTON_TEXT_DISPOSE, 631, 300, () -> {
            this.dispose();
        }));
    }

    private void RegisterEventHandler() {
        toaster.warn("please wait for the register task");
        try        
        {
            UserServiceImpl userServices = new UserServiceImpl();
            User user = new User();
            user.setNom(this.usernameField.getText());
            user.setPrenom(this.userLastNameField.getText());
            user.setPassword(this.passwordField.getText());
            user.setLogin(this.emailField.getText());
            
            userServices.register(user);
        	Thread.sleep(1000);
            
        } 
        catch(InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }

    }
}
