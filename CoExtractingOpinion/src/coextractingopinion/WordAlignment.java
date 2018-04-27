
package coextractingopinion;

import java.io.File;
import java.io.FileInputStream;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class WordAlignment extends javax.swing.JFrame {

   
    ArrayList norep=new ArrayList();
    
    public WordAlignment() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Algerian", 0, 24)); // NOI18N
        jLabel1.setText("Word Alignment");

        jButton1.setText("Word Alignment");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Opinion Target Candidates", "Opinion Words Candidates"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Calculating the Opinion Associations Among Words");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try
        {
            ArrayList stop1=new ArrayList();
            File fe2=new File("stopwords1.txt");
            FileInputStream fis2=new FileInputStream(fe2);
            byte data2[]=new byte[fis2.available()];
            fis2.read(data2);
            fis2.close();                
            String sg2[]=new String(data2).split("\n");               
            for(int i=0;i<sg2.length;i++)
                stop1.add(sg2[i].trim());
        
            for(int i=0;i<PSWAMModel.norepeat.size();i++)
            {
                String s=PSWAMModel.norepeat.get(i).toString().trim();
                String s1[]=s.split("@");
                String news1of1="";
                if(s1[1].trim().contains(","))
                {
                    String s2[]=s1[1].trim().split(",");
                    String fornews1="";
                    for(int j=0;j<s2.length;j++)
                    {
                        if(!(stop1.contains(s2[j].trim())))
                        {
                            fornews1=fornews1+s2[j].trim()+",";
                            System.out.println(fornews1);
                        }
                    }
                    if(!(fornews1.equals("")))
                    {
                        news1of1=fornews1.substring(0,fornews1.lastIndexOf(','));
                        System.out.println(news1of1);
                    }
                    else
                    {
                        news1of1=fornews1;
                    }
                }
                else
                {
                    if(!(stop1.contains(s1[1].trim())))
                    {
                        news1of1=s1[1].trim();
                    }
                }
                if((!(s1[0].trim().equals("")))&&((!(news1of1.trim().equals("")))))
                {
                    norep.add(s1[0].trim()+"@"+news1of1.trim());
                    
                }
            }
            
            for(int i=0;i<norep.size();i++)
            {
                String s=norep.get(i).toString().trim();
                System.out.println("printing s");
                System.out.println(s);
                String s1[]=s.split("@");
                System.out.println("s --> "+s);
                if(s1[0].trim().contains(","))
                {
                    String s2[]=s1[0].trim().split(",");                    
                    if(s1[1].trim().contains(","))
                    {
                        String s3[]=s1[1].trim().split(",");
                        
                        for(int j=0;j<s2.length;j++)
                        {
                            for(int k=0;k<s3.length;k++)
                            {
                                DefaultTableModel dm=(DefaultTableModel)jTable1.getModel();
                                Vector v=new Vector();
                                v.add(s2[j].trim());
                                v.add(s3[k].trim());
                                dm.addRow(v);
                            }
                        }
                    }
                    else
                    {
                        for(int j=0;j<s2.length;j++)
                        {
                            DefaultTableModel dm=(DefaultTableModel)jTable1.getModel();
                            Vector v=new Vector();
                            v.add(s2[j].trim());
                            v.add(s1[1].trim());
                            dm.addRow(v);
                        }
                    }
                }
                else
                {
                    if(s1[1].trim().contains(","))
                    {
                        String s3[]=s1[1].trim().split(",");
                        for(int k=0;k<s3.length;k++)
                        {
                            DefaultTableModel dm=(DefaultTableModel)jTable1.getModel();
                            Vector v=new Vector();
                            v.add(s1[0].trim());
                            v.add(s3[k].trim());
                            dm.addRow(v);
                        }
                    }
                    else
                    {
                        DefaultTableModel dm=(DefaultTableModel)jTable1.getModel();
                        Vector v=new Vector();
                        v.add(s1[0].trim());
                        v.add(s1[1].trim());
                        dm.addRow(v);
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        OpinionAssociation oa=new OpinionAssociation();
        oa.setResizable(false);
        oa.setTitle("Opinion Association");
        oa.setVisible(true);
    }
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WordAlignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WordAlignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WordAlignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WordAlignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WordAlignment().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    }
