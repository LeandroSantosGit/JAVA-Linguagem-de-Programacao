
package faculdade.editortexto;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Projeto feito ao final do curso, aprimorando os conhecimentos aprendidos.
 * 
 * Criar novo projeto java Editor de Texto, usando Swing Application Framework. O Editor de Texto
 * terá um menu com as opções de sair, abrir e salvar.
 * 
 * Date 14/12/2019
 * @author Leandro
 */
public class EditorTexto extends javax.swing.JFrame {
    private String nomeArquivo;

    /** Creates new form EditorTexto */
    public EditorTexto() {
        super("Editor de Texto");
        setLayout(new FlowLayout());
        
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txt.setColumns(20);
        txt.setRows(5);
        jScrollPane1.setViewportView(txt);

        jMenu1.setText("Arquivo");

        jMenuItem1.setText("Abrir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Salvar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sair");

        jMenuItem3.setText("Sair");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        if (javax.swing.JOptionPane.showConfirmDialog(null, "Deseja sair ?", "Atenção",
                javax.swing.JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_sairActionPerformed

    private void abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirActionPerformed

        JFileChooser file = new JFileChooser();
        file.setDialogTitle("Abrir");
        int opcao = file.showOpenDialog(this);
        if (opcao != JFileChooser.APPROVE_OPTION) {
            return;
        }
        String filename = file.getSelectedFile().getAbsolutePath();
        BufferedReader in = null;
        // Este código assume que um arquivo não tem mais do que 64K.
        try {
            in = new BufferedReader(new FileReader(filename));
            StringBuffer buf = new StringBuffer(10000);
            String aux;
            while ((aux = in.readLine()) != null) {
                buf.append(aux);
                buf.append("\n");
            }
            // Se já leu adiciona-se o texto e guarda-se o nome do arquivo
            txt.setText(buf.toString());
            nomeArquivo = filename;
        } catch (FileNotFoundException e) {
            mensagemErro("Abrir", "Erro de abertura: " + filename);
        } catch (IOException e) {
            mensagemErro("Abrir", "Erro de leitura: " + filename);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ignore) {
            }
        }
    }//GEN-LAST:event_abrirActionPerformed

    private void salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarActionPerformed

        String filename;
        // É a primeira vez que está salvando?
        if (nomeArquivo == null) {
            JFileChooser file = new JFileChooser();
            file.setDialogTitle("Salvar");
            int opcao = file.showSaveDialog(this);
            if (opcao != JFileChooser.APPROVE_OPTION) {
                return;
            }
            filename = file.getSelectedFile().getAbsolutePath();
            File f = new File(filename);
            // O arquivo já existe então pede confirmação
            if (f.exists()) {
                int confirmacao = JOptionPane.showConfirmDialog(
                        this, "Este arquivo já existe. Deseja sobrescrever?",
                        "Salvar", JOptionPane.YES_NO_OPTION);
                if (confirmacao == JOptionPane.NO_OPTION) {
                    return;
                }
            }
        } else {
            filename = nomeArquivo;
        }
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(filename));
            // Vamos escrever linha a linha a fim de utilizar o separador adequado
            StringTokenizer linhas = new StringTokenizer(txt.getText(), "\n");
            while (linhas.hasMoreTokens()) {
                out.write(linhas.nextToken());
                out.newLine();
            }
            nomeArquivo = filename;
        } catch (IOException e) {
            mensagemErro("Salvar", "Erro de escrita: " + filename);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }//GEN-LAST:event_salvarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditorTexto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditorTexto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditorTexto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditorTexto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditorTexto().setVisible(true);
            }
        });
    }
    
    private void mensagemErro(String titulo, String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, titulo,
                JOptionPane.ERROR_MESSAGE);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txt;
    // End of variables declaration//GEN-END:variables

}