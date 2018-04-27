
package coextractingopinion;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CandidateConfidence extends javax.swing.JFrame {

    /**
     * Creates new form CoRankingProcess
     */
    DecimalFormat df=new DecimalFormat("#.####");
    ArrayList fortarthrs=new ArrayList();
    ArrayList forwordthrs=new ArrayList();
    public static int noofcandidates=0;
    
    public CandidateConfidence() {
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
        jLabel1.setText("Co-Extracting Opinion Targets and Opinion Words");

        jButton1.setText("Compute Candidate Confidence");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Opinion Target Candidates", "Opinion Target Confidence", "Opinion Words Candidates", "Opinion Word Confidence"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Find Opinion Targets & Opinion Words");
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Sentiwordnet swn=new Sentiwordnet("SentiWordNet_3.0.0.txt");
        double initialtargconf=Math.random();
        double initialwordgconf=Math.random();                
        
        for(int i=0;i<OpinionAssociation.jTable1.getRowCount();i++)
        {
            String tar=OpinionAssociation.jTable1.getValueAt(i,0).toString().trim();
            String wor=OpinionAssociation.jTable1.getValueAt(i,1).toString().trim();
            String oa=OpinionAssociation.jTable1.getValueAt(i,4).toString().trim();
            
            int mu=(int)(Math.random()*2);
            System.out.println("mu is "+mu);
            
                    /*prior knowledge*/
            double it=(double)Collections.frequency(OpinionAssociation.fortarget, tar);           System.out.println(it);
            double io=0;
            try
            {
                io=swn.extract(wor,"a"); 
                System.out.println(io);
            }
            catch(Exception e)
            {
                try
                {       
                    io=swn.extract(wor,"v");
                }
                catch(Exception e1)
                {
                    io=Math.random();
                }
            }
            
                    /*opinion relevance*/            
            double opirel=Double.parseDouble(oa.trim());
            
                    /*Confidence Value*/
            double opitargetconf=(double)((1-mu)*opirel*initialwordgconf)+(double)(mu*it);
            double opiwordconf=(double)((1-mu)*opirel*initialtargconf)+(double)(mu*io);
            
            DefaultTableModel dm=(DefaultTableModel)jTable1.getModel();
            Vector v=new Vector();
            v.add(tar);
            fortarthrs.add(df.format(opitargetconf));
            v.add(df.format(opitargetconf));
            v.add(wor);     
            forwordthrs.add(df.format(opiwordconf));
            v.add(df.format(opiwordconf));            
            dm.addRow(v);
        }
        noofcandidates=jTable1.getRowCount();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        FinalResult fr=new FinalResult();
        fr.setResizable(false);
        fr.setTitle("Final Result");
        fr.setVisible(true);
        
        Collections.sort(fortarthrs);
        Collections.sort(forwordthrs);
        int ran=fortarthrs.size()/2;
        int ran1=forwordthrs.size()/2;
        //int ran=(int)(Math.random()*fortarthrs.size());
        //int ran1=(int)(Math.random()*forwordthrs.size());
        
        double tarthres=Double.parseDouble(fortarthrs.get(ran).toString().trim());
        double wordthres=Double.parseDouble(forwordthrs.get(ran1).toString().trim());
        
        FinalResult.jTextField1.setText(fortarthrs.get(ran).toString().trim());
        FinalResult.jTextField2.setText(forwordthrs.get(ran1).toString().trim());
        
        for(int i=0;i<jTable1.getRowCount();i++)
        {
            String tar=jTable1.getValueAt(i,0).toString().trim();
            String tc=jTable1.getValueAt(i,1).toString().trim();
            String wor=jTable1.getValueAt(i,2).toString().trim();
            String wc=jTable1.getValueAt(i,3).toString().trim();
            
            double tarconf=Double.parseDouble(tc.trim());
            double worconf=Double.parseDouble(wc.trim());
            
            if((tarthres<=tarconf)&&(wordthres<=worconf))
            {
                DefaultTableModel dm=(DefaultTableModel)FinalResult.jTable1.getModel();
                Vector v=new Vector();
                v.add(tar);            
                v.add(tc);
                v.add(wor);                 
                v.add(wc);            
                dm.addRow(v);
            }
        }
    }    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CandidateConfidence.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CandidateConfidence.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CandidateConfidence.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CandidateConfidence.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CandidateConfidence().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
 }
