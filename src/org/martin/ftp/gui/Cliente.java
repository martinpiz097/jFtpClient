/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.gui;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import org.apache.commons.net.ftp.FTPFile;
import org.martin.ftp.model.TCRFiles;
import org.martin.ftp.model.TMFiles;
import org.martin.ftp.net.Accesador;

/**
 *
 * @author martin
 */
public class Cliente extends javax.swing.JFrame {

    Accesador accesador;
    String directorioActual;
    JFileChooser fileChoos;
    
    public Cliente() {
        initComponents();
        gestion.setDefaultCloseOperation(EXIT_ON_CLOSE);
        directorioActual = "/";
        setLocationRelativeTo(null);
        setResizable(false);
        fileChoos = new JFileChooser();
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File f) {
                return true;
            }

            @Override
            public String getDescription() {
                return "Escoja el archivo";
            }
        };
        
        fileChoos.addChoosableFileFilter(filter);
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtServer = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnConnect = new javax.swing.JButton();

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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblFilesKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblFiles);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        btnUploadFile.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnUploadFile.setText("Subir Archivo");
        btnUploadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadFileActionPerformed(evt);
            }
        });

        btnUpdateDirectory.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnUpdateDirectory.setText("Actualizar");
        btnUpdateDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateDirectoryActionPerformed(evt);
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
                        .addGroup(gestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gestionLayout.createSequentialGroup()
                                .addComponent(btnUpdateDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUploadFile, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))))
                .addContainerGap())
        );
        gestionLayout.setVerticalGroup(
            gestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUploadFile)
                    .addComponent(btnUpdateDirectory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );

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
        btnConnect.setText("Conectar");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtServer)
                            .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(btnConnect))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed

        String server = txtServer.getText();
        String user = txtUser.getText();
        String pass = txtPassword.getText();
        try {

            accesador = new Accesador(server, user, pass);
            accesador.setToParentDirectory();
            updateDirectory();
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        setVisible(false);
        gestion.setSize(gestion.getPreferredSize());
        gestion.setLocationRelativeTo(null);
        gestion.setVisible(true);
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

    }//GEN-LAST:event_tblFilesKeyReleased

    private void tblFilesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFilesMouseReleased

        if (evt.getClickCount() == 2) {
            
            TMFiles model = (TMFiles) tblFiles.getModel();
            FTPFile selected = model.getFile(tblFiles.getSelectedRow());
            
            if (selected.isDirectory()) {
                
                try {
                    accesador.setWorkingDirectory(directorioActual + selected.getName());
                    updateDirectory();
                    
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            else{
                
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
                    
                }
                
            }
            
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

        fileChoos.showOpenDialog(this);
        
        if (fileChoos.getSelectedFile() != null) {
            
            try {
                accesador.uploadFile(fileChoos.getSelectedFile(), directorioActual);
                 updateDirectory();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    private void updateDirectory() throws IOException{
        
        LinkedList<FTPFile> files = accesador.getOrderedFiles();
        directorioActual = accesador.getWorkingDirectory();
        txtNewDir.setText(directorioActual);

        if (tblFiles.getRowHeight() != 25) tblFiles.setRowHeight(25);
        tblFiles.setModel(new TMFiles(files));
        tblFiles.setDefaultRenderer(Object.class, new TCRFiles(files));
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
            new Cliente().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnUpdateDirectory;
    private javax.swing.JButton btnUploadFile;
    private javax.swing.JFrame gestion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblFiles;
    private javax.swing.JTextField txtNewDir;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtServer;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
