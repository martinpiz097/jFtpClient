/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.gui;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.net.ftp.FTPFile;
import org.martin.ftp.model.TCRFiles;
import org.martin.ftp.model.TMFiles;
import org.martin.ftp.net.Accesador;
import org.martin.ftp.net.Tester;
import static org.martin.ftp.config.Encryptor.encrypt;
import static org.martin.ftp.config.Encryptor.decrypt;
import org.martin.ftp.config.Setting;

/**
 *
 * @author martin
 */
public class Cliente extends javax.swing.JFrame {

    Accesador accesador;
    String directorioActual;
    JFileChooser fileChoos;
    boolean hasInternetConnection;
    boolean isConnectedToHost;
    // Setting settings;
    Setting settings;
    
    public Cliente() {
       
        initComponents();
        gestion.setDefaultCloseOperation(EXIT_ON_CLOSE);
        directorioActual = "/";
        setLocationRelativeTo(null);
        fileChoos = new JFileChooser();
        fileChoos.addChoosableFileFilter(getAllFileFilter());
        fileChoos.addChoosableFileFilter(getDirectoryFilter());
        setResizable(false);
        cuadroNewFolder.setSize(cuadroNewFolder.getPreferredSize());
    
        try {
            settings = new Setting();
            if (settings.isAutoLogin()) 
                connect(settings.getHost(), settings.getUser(), settings.getPassword());
            else
                show();
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private FileFilter getDirectoryFilter(){
        
        return new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory();
            }
            @Override
            public String getDescription() {
                return "Solo directorios";
            }
        };
    }
    
    private FileFilter getAllFileFilter(){
        return new FileFilter() {

            @Override
            public boolean accept(File f) {
                return true;
            }

            @Override
            public String getDescription() {
                return "Escoja el archivo";
            }
        };
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gestion = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtNewDir = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFiles = new javax.swing.JTable();
        btnUploadFile = new javax.swing.JButton();
        btnUpdateDirectory = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnAddFolder = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        cuadroNewFolder = new javax.swing.JDialog();
        panelDialog = new javax.swing.JPanel();
        txtNewFolder2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtServer = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnConnect = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        chkAuto = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        gestion.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                gestionWindowClosing(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cambiar Directorio"));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Directorio: ");

        txtNewDir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNewDirKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNewDir))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNewDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Archivos y carpetas"));
        jPanel3.setLayout(new java.awt.BorderLayout());

        tblFiles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblFilesMouseReleased(evt);
            }
        });
        tblFiles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblFilesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblFilesKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblFiles);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        btnUploadFile.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnUploadFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/upload.png"))); // NOI18N
        btnUploadFile.setToolTipText("Subir Archivo");
        btnUploadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadFileActionPerformed(evt);
            }
        });

        btnUpdateDirectory.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnUpdateDirectory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/update.png"))); // NOI18N
        btnUpdateDirectory.setToolTipText("Actualizar");
        btnUpdateDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateDirectoryActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/goBack.png"))); // NOI18N
        btnBack.setToolTipText("Volver Atras");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnAddFolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/newFolder.png"))); // NOI18N
        btnAddFolder.setToolTipText("Nueva Carpeta");
        btnAddFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFolderActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/exit.png"))); // NOI18N
        jButton1.setToolTipText("Cerrar Sesion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gestionLayout = new javax.swing.GroupLayout(gestion.getContentPane());
        gestion.getContentPane().setLayout(gestionLayout);
        gestionLayout.setHorizontalGroup(
            gestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestionLayout.createSequentialGroup()
                .addGroup(gestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gestionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(gestionLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
                    .addGroup(gestionLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUploadFile, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addContainerGap())
        );
        gestionLayout.setVerticalGroup(
            gestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(gestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnUploadFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdateDirectory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBack))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelDialog.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 102), null));
        panelDialog.setPreferredSize(new java.awt.Dimension(404, 65));

        txtNewFolder2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNewFolder2KeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel6.setText("Nombre de la carpeta:");

        javax.swing.GroupLayout panelDialogLayout = new javax.swing.GroupLayout(panelDialog);
        panelDialog.setLayout(panelDialogLayout);
        panelDialogLayout.setHorizontalGroup(
            panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNewFolder2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDialogLayout.setVerticalGroup(
            panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNewFolder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        cuadroNewFolder.getContentPane().add(panelDialog, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Conectar a"));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Servidor:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Usuario:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Contraseña:");

        txtServer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtServerKeyReleased(evt);
            }
        });

        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserKeyReleased(evt);
            }
        });

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });

        btnConnect.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/connect.png"))); // NOI18N
        btnConnect.setText("Conectar");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/folderMain.png"))); // NOI18N

        chkAuto.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        chkAuto.setText("Iniciar Sesion automaticamante");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkAuto)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnConnect)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(16, 16, 16)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtServer)
                                .addComponent(txtUser)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkAuto))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConnect)
                .addGap(30, 30, 30))
        );

        jMenu1.setText("File");

        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem1.setText("Reestablecer configuraciones");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed

        String server = txtServer.getText();
        String user = txtUser.getText();
        String pass = txtPassword.getText();

        if (server == null) server = "";
        if (user == null) user = "";
        
        try {
            if (server.isEmpty() || user.isEmpty()) 
                JOptionPane.showMessageDialog(
                        this, 
                        "Por Favor rellene todos los campos", 
                        "Error", 
                        JOptionPane.WARNING_MESSAGE);
            
            else connect(server, user, pass);

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConnectActionPerformed

    private void txtServerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServerKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER){ 
            txtUser.requestFocus();
            if (!txtUser.getText().isEmpty()) 
                txtUser.selectAll();
            
        }
    }//GEN-LAST:event_txtServerKeyReleased

    private void txtUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER){ 
            txtPassword.requestFocus();
            if (!txtPassword.getText().isEmpty()) 
                txtPassword.selectAll();
            
        }
    }//GEN-LAST:event_txtUserKeyReleased

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) 
            btnConnect.doClick();
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void tblFilesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblFilesKeyReleased
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) try {
            setWorkingDirectory();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_tblFilesKeyReleased

    private void tblFilesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFilesMouseReleased

        if (evt.getClickCount() == 2) try {
            setWorkingDirectory();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_tblFilesMouseReleased

    private void txtNewDirKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewDirKeyReleased

        String dir = txtNewDir.getText().trim();
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) 
            if (!dir.isEmpty()) {
            try {
                accesador.setWorkingDirectory(dir);
                updateDirectory();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
    }//GEN-LAST:event_txtNewDirKeyReleased

    private void btnUploadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadFileActionPerformed

        fileChoos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChoos.showOpenDialog(this);
        File selected = fileChoos.getSelectedFile();
        File[] files = fileChoos.getSelectedFiles();
        
        if (selected != null || files != null) {
            
            new Thread(() -> {
                try {
                    if (files.length > 0)
                        for (File file : files)
                            accesador.uploadFile(file, directorioActual);
                    
                    else accesador.uploadFile(selected, directorioActual);
                    
                    updateDirectory();
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
                
        }
        
    }//GEN-LAST:event_btnUploadFileActionPerformed

    private void gestionWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_gestionWindowClosing

        try {
            accesador.logout();
            accesador.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_gestionWindowClosing

    private void btnUpdateDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateDirectoryActionPerformed

        try {
            updateDirectory();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateDirectoryActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            accesador.goToBeforeDirectory();
            updateDirectory();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void tblFilesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblFilesKeyPressed

    }//GEN-LAST:event_tblFilesKeyPressed

    private void txtNewFolder2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewFolder2KeyReleased

        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            
            String nameFolder = txtNewFolder2.getText().trim();
            
            if(!nameFolder.isEmpty()){
                try {
                    File newDir = new File(nameFolder + "/");
                    newDir.createNewFile();
                    FileInputStream fis = new FileInputStream(newDir);
                    accesador.createDirectory(directorioActual, nameFolder);
                    updateDirectory();
                    newDir.delete();
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            txtNewFolder2.setText(null);
            cuadroNewFolder.hide();
        }
    }//GEN-LAST:event_txtNewFolder2KeyReleased

    private void btnAddFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFolderActionPerformed

        cuadroNewFolder.show();
        cuadroNewFolder.setLocationRelativeTo(null);
        if (!txtNewFolder2.isFocusable()) txtNewFolder2.requestFocus();
        
    }//GEN-LAST:event_btnAddFolderActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        try {
            settings.deleteSettings();
            JOptionPane.showMessageDialog(this, "Configuraciones reestablecidas");
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            tblFiles.setModel(new DefaultTableModel());
            txtNewDir.setText(null);
            accesador.logout();
            accesador = null;
            gestion.setVisible(false);
            setVisible(true);
            txtServer.requestFocus();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void updateDirectory() throws IOException{
        
        LinkedList<FTPFile> files = accesador.getOrderedFiles();
        directorioActual = accesador.getWorkingDirectory();
        txtNewDir.setText(directorioActual);

        if (tblFiles.getRowHeight() != 25) tblFiles.setRowHeight(25);
        tblFiles.setModel(new TMFiles(files));
        tblFiles.setDefaultRenderer(Object.class, new TCRFiles(files));
    }
    
    private void setWorkingDirectory() throws IOException{
        TMFiles model = (TMFiles) tblFiles.getModel();
        FTPFile selected = model.getFile(tblFiles.getSelectedRow());

        if (selected.isDirectory()) {
            accesador.setWorkingDirectory(directorioActual + "/" + selected.getName());
            updateDirectory();
        }
        else {

            int tipo = JOptionPane.INFORMATION_MESSAGE;
            int tipoOp = JOptionPane.YES_NO_OPTION;
            int opcionSi = JOptionPane.YES_OPTION;
            int opcion = JOptionPane.showConfirmDialog(
                    gestion,
                    "¿Desea descargar el archivo?",
                    "Confirmacion de Descarga",
                    tipoOp,
                    tipo);

            if (opcion == opcionSi) {
                try {
                    
                    fileChoos.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    fileChoos.showOpenDialog(this);
                    File directory = fileChoos.getSelectedFile();
                    
                    if (directory != null)
                        accesador.downloadFile(selected.getName(), directory.getPath());
                    
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void connect(String server, String user, String pass) throws IOException{
        isConnectedToHost = Tester.isConnectedToHost(server);
        
        if (isConnectedToHost) {
            
            accesador = new Accesador(server, user, pass);
            
            if (accesador.isConnected()) {

                if (chkAuto.isSelected()) 
                    settings.setSettings(server, user, pass);
                    
                accesador.setToParentDirectory();
                updateDirectory();
                hide();
                gestion.setSize(gestion.getPreferredSize());
                gestion.setLocationRelativeTo(null);
                gestion.setVisible(true);
                clearComponents(txtServer, txtUser, txtPassword);
            }
            
            else {
                JOptionPane.showMessageDialog(this,
                        "Error de conexion, uno o mas datos invalidos\nCodigo de error: " +
                                accesador.getReplyCode(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                txtServer.selectAll();
                txtServer.requestFocus();
            }
        }
        
        else {
            JOptionPane.showMessageDialog(this,
                    "Error de conexion, servidor desconocido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtServer.selectAll();
            txtServer.requestFocus();
        }
    }
    
    private void clearComponents(JComponent... componentes){

        for (JComponent componente : componentes) 
            if (componente instanceof JTextField) 
                ((JTextField)componente).setText(null);
            else if(componente instanceof JPasswordField)
                ((JPasswordField)componente).setText(null);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            /* Set the Nimbus look and feel */
            javax.swing.UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Cliente();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFolder;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnUpdateDirectory;
    private javax.swing.JButton btnUploadFile;
    private javax.swing.JCheckBox chkAuto;
    private javax.swing.JDialog cuadroNewFolder;
    private javax.swing.JFrame gestion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelDialog;
    private javax.swing.JTable tblFiles;
    private javax.swing.JTextField txtNewDir;
    private javax.swing.JTextField txtNewFolder2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtServer;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}

