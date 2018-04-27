package coextractingopinion;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
public class PSWAMModel extends javax.swing.JFrame {

    
    ArrayList opiniontargetcandidate=new ArrayList();
    ArrayList opinionwordscandidate=new ArrayList();
    public static ArrayList norepeat=new ArrayList();
    
    public PSWAMModel() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Candidate Extraction");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Partially Supervised Word Alignment");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {WordAlignment wa=new WordAlignment();
        wa.setResizable(false);
        wa.setTitle("Word Alignment");
        wa.setVisible(true);
        
        
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String dataset=POSTagging.jTextArea1.getText().trim();
        String d1[]=dataset.split("\n");                                
        
        try
        {                                    
            int cou=0;
            for(int i=0;i<d1.length;i++)
            {
                String sentence=d1[i].trim();
                if(sentence.contains(","))
                {
                    System.out.println(i+"-->"+sentence);
                    String sen1[]=sentence.split(",");
                    System.out.println("sen1.length is: "+sen1.length);
                    for(int l=0;l<sen1.length;l++)
                    {
                        cou++;
                        String ret=sen1[l];		
				
                        if(ret.contains(" "))
                        {
                            String s1[]=ret.trim().split(" ");
                            for(int j=0;j<s1.length;j++)
                            {
                                if(s1[j].contains("/NNPS"))
                                {
                                    String noun=s1[j].replaceAll("/NNPS","");
                                    opiniontargetcandidate.add(cou+"@"+noun);
                                }
                                else if(s1[j].contains("/NNP"))
                                {
                                    String noun=s1[j].replaceAll("/NNP","");
                                    opiniontargetcandidate.add(cou+"@"+noun);
                                }
                                else if(s1[j].contains("/NNS"))
                                {
                                    String noun=s1[j].replaceAll("/NNS","");
                                    opiniontargetcandidate.add(cou+"@"+noun);
                                }
                                else if(s1[j].contains("/NN"))
                                {
                                    String noun=s1[j].replaceAll("/NN","");
                                    opiniontargetcandidate.add(cou+"@"+noun);
                                }                        
                                else if(s1[j].contains("/JJS"))
                                {
                                    String adjective=s1[j].replaceAll("/JJS","");
                                    opinionwordscandidate.add(cou+"@"+adjective);
                                }
                                else if(s1[j].contains("/JJR"))
                                {
                                    String adjective=s1[j].replaceAll("/JJR","");
                                    opinionwordscandidate.add(cou+"@"+adjective);
                                }
                                else if(s1[j].contains("/JJ"))
                                {
                                    String adjective=s1[j].replaceAll("/JJ","");
                                    opinionwordscandidate.add(cou+"@"+adjective);
                                }
                                else if(s1[j].contains("/VBZ"))
                                {
                                    String verb=s1[j].replaceAll("/VBZ","");
                                    opinionwordscandidate.add(cou+"@"+verb);
                                }
                                else if(s1[j].contains("/VBP"))
                                {
                                    String verb=s1[j].replaceAll("/VBP","");
                                    opinionwordscandidate.add(cou+"@"+verb);
                                }
                                else if(s1[j].contains("/VBN"))
                                {
                                    String verb=s1[j].replaceAll("/VBN","");
                                    opinionwordscandidate.add(cou+"@"+verb);
                                }
                                else if(s1[j].contains("/VBG"))
                                {
                                    String verb=s1[j].replaceAll("/VBG","");
                                    opinionwordscandidate.add(cou+"@"+verb);
                                }
                                else if(s1[j].contains("/VBD"))
                                {
                                    String verb=s1[j].replaceAll("/VBD","");
                                    opinionwordscandidate.add(cou+"@"+verb);
                                }
                                else if(s1[j].contains("/VB"))
                                {
                                    String verb=s1[j].replaceAll("/VB","");
                                    opinionwordscandidate.add(cou+"@"+verb);
                                }
                            }
                        }
                        else
                        {
                            if(ret.contains("/NNPS"))
                            {
                                String noun=ret.replaceAll("/NNPS","");
                                opiniontargetcandidate.add(cou+"@"+noun);
                            }
                            else if(ret.contains("/NNP"))
                            {
                                String noun=ret.replaceAll("/NNP","");
                                opiniontargetcandidate.add(cou+"@"+noun);
                            }
                            else if(ret.contains("/NNS"))
                            {
                                String noun=ret.replaceAll("/NNS","");
                                opiniontargetcandidate.add(cou+"@"+noun);
                            }
                            else if(ret.contains("/NN"))
                            {
                                String noun=ret.replaceAll("/NN","");
                                opiniontargetcandidate.add(cou+"@"+noun);
                            }                        
                            else if(ret.contains("/JJS"))
                            {
                                String adjective=ret.replaceAll("/JJS","");
                                opinionwordscandidate.add(cou+"@"+adjective);
                            }
                            else if(ret.contains("/JJR"))
                            {
                                String adjective=ret.replaceAll("/JJR","");
                                opinionwordscandidate.add(cou+"@"+adjective);
                            }
                            else if(ret.contains("/JJ"))
                            {
                                String adjective=ret.replaceAll("/JJ","");
                                opinionwordscandidate.add(cou+"@"+adjective);
                            }
                            else if(ret.contains("/VBZ"))
                            {
                                String verb=ret.replaceAll("/VBZ","");
                                opinionwordscandidate.add(cou+"@"+verb);
                            }
                            else if(ret.contains("/VBP"))
                            {
                                String verb=ret.replaceAll("/VBP","");
                                opinionwordscandidate.add(cou+"@"+verb);
                            }
                            else if(ret.contains("/VBN"))
                            {
                                String verb=ret.replaceAll("/VBN","");
                                opinionwordscandidate.add(cou+"@"+verb);
                            }
                            else if(ret.contains("/VBG"))
                            {
                                String verb=ret.replaceAll("/VBG","");
                                opinionwordscandidate.add(cou+"@"+verb);
                            }
                            else if(ret.contains("/VBD"))
                            {
                                String verb=ret.replaceAll("/VBD","");
                                opinionwordscandidate.add(cou+"@"+verb);
                            }
                            else if(ret.contains("/VB"))
                            {
                                String verb=ret.replaceAll("/VB","");
                                opinionwordscandidate.add(cou+"@"+verb);
                            }
                        }
                    }
                }
                else
                {
                    String ret=sentence;		
                    cou++;
                    if(ret.contains(" "))
                    {
                        String s1[]=ret.trim().split(" ");
                        for(int j=0;j<s1.length;j++)
                        {
                            if(s1[j].contains("/NNPS"))
                            {
                                String noun=s1[j].replaceAll("/NNPS","");
                                opiniontargetcandidate.add(cou+"@"+noun);
                            }
                            else if(s1[j].contains("/NNP"))
                            {
                                String noun=s1[j].replaceAll("/NNP","");
                                opiniontargetcandidate.add(cou+"@"+noun);
                            }
                            else if(s1[j].contains("/NNS"))
                            {
                                String noun=s1[j].replaceAll("/NNS","");
                                opiniontargetcandidate.add(cou+"@"+noun);
                            }
                            else if(s1[j].contains("/NN"))
                            {
                                String noun=s1[j].replaceAll("/NN","");
                                opiniontargetcandidate.add(cou+"@"+noun);
                            }                        
                            else if(s1[j].contains("/JJS"))
                            {
                                String adjective=s1[j].replaceAll("/JJS","");
                                opinionwordscandidate.add(cou+"@"+adjective);
                            }
                            else if(s1[j].contains("/JJR"))
                            {
                                String adjective=s1[j].replaceAll("/JJR","");
                                opinionwordscandidate.add(cou+"@"+adjective);
                            }
                            else if(s1[j].contains("/JJ"))
                            {
                                String adjective=s1[j].replaceAll("/JJ","");
                                opinionwordscandidate.add(cou+"@"+adjective);
                            }
                            else if(s1[j].contains("/VBZ"))
                            {
                                String verb=s1[j].replaceAll("/VBZ","");
                                opinionwordscandidate.add(cou+"@"+verb);
                            }
                            else if(s1[j].contains("/VBP"))
                            {
                                String verb=s1[j].replaceAll("/VBP","");
                                opinionwordscandidate.add(cou+"@"+verb);
                            }
                            else if(s1[j].contains("/VBN"))
                            {
                                String verb=s1[j].replaceAll("/VBN","");
                                opinionwordscandidate.add(cou+"@"+verb);
                            }
                            else if(s1[j].contains("/VBG"))
                            {
                                String verb=s1[j].replaceAll("/VBG","");
                                opinionwordscandidate.add(cou+"@"+verb);
                            }
                            else if(s1[j].contains("/VBD"))
                            {
                                String verb=s1[j].replaceAll("/VBD","");
                                opinionwordscandidate.add(cou+"@"+verb);
                            }
                            else if(s1[j].contains("/VB"))
                            {
                                String verb=s1[j].replaceAll("/VB","");
                                opinionwordscandidate.add(cou+"@"+verb);
                            }
                        }
                    }
                    else
                    {
                        if(ret.contains("/NNPS"))
                        {
                            String noun=ret.replaceAll("/NNPS","");
                            opiniontargetcandidate.add(cou+"@"+noun);
                        }
                        else if(ret.contains("/NNP"))
                        {
                            String noun=ret.replaceAll("/NNP","");
                            opiniontargetcandidate.add(cou+"@"+noun);
                        }
                        else if(ret.contains("/NNS"))
                        {
                            String noun=ret.replaceAll("/NNS","");
                            opiniontargetcandidate.add(cou+"@"+noun);
                        }
                        else if(ret.contains("/NN"))
                        {
                            String noun=ret.replaceAll("/NN","");
                            opiniontargetcandidate.add(cou+"@"+noun);
                        }                        
                        else if(ret.contains("/JJS"))
                        {
                            String adjective=ret.replaceAll("/JJS","");
                            opinionwordscandidate.add(cou+"@"+adjective);
                        }
                        else if(ret.contains("/JJR"))
                        {
                            String adjective=ret.replaceAll("/JJR","");
                            opinionwordscandidate.add(cou+"@"+adjective);
                        }
                        else if(ret.contains("/JJ"))
                        {
                            String adjective=ret.replaceAll("/JJ","");
                            opinionwordscandidate.add(cou+"@"+adjective);
                        }
                        else if(ret.contains("/VBZ"))
                        {
                            String verb=ret.replaceAll("/VBZ","");
                            opinionwordscandidate.add(cou+"@"+verb);
                        }
                        else if(ret.contains("/VBP"))
                        {
                            String verb=ret.replaceAll("/VBP","");
                            opinionwordscandidate.add(cou+"@"+verb);
                        }
                        else if(ret.contains("/VBN"))
                        {
                            String verb=ret.replaceAll("/VBN","");
                            opinionwordscandidate.add(cou+"@"+verb);
                        }
                        else if(ret.contains("/VBG"))
                        {
                            String verb=ret.replaceAll("/VBG","");
                            opinionwordscandidate.add(cou+"@"+verb);
                        }
                        else if(ret.contains("/VBD"))
                        {
                            String verb=ret.replaceAll("/VBD","");
                            opinionwordscandidate.add(cou+"@"+verb);
                        }
                        else if(ret.contains("/VB"))
                        {
                            String verb=ret.replaceAll("/VB","");
                            opinionwordscandidate.add(cou+"@"+verb);
                        }
                    }
                }                                                
            }
            norepeat=new ArrayList();
            for(int i=0;i<cou;i++)
            {                
                int count=0;
                String tar="";
                for(int j=0;j<opiniontargetcandidate.size();j++)
                {
                    String s=opiniontargetcandidate.get(j).toString().trim();
                    String s1[]=s.split("@");
                    if(s1[0].trim().equals(""+i))
                    {
                        tar=tar+s1[1].trim()+","; 
                        count=1;
                    }
                }
                String target="";
                if(count!=0)
                {
                    target=tar.substring(0,tar.lastIndexOf(','));                                    
                }                
                count=0;
                String wor="";
                for(int j=0;j<opinionwordscandidate.size();j++)
                {
                    String s=opinionwordscandidate.get(j).toString().trim();
                    String s1[]=s.split("@");
                    if(s1[0].trim().equals(""+i))
                    {
                        wor=wor+s1[1].trim()+","; 
                        count=1;
                    }
                }
                String word="";
                if(count!=0)
                {
                    word=wor.substring(0,wor.lastIndexOf(','));                                    
                }                             
                if((!(target.trim().equals("")))&&((!(word.trim().equals("")))))
                {
                    if(!(norepeat.contains(target.trim()+"@"+word.trim())))
                    {
                        norepeat.add(target.trim()+"@"+word.trim());
                        DefaultTableModel dm=(DefaultTableModel)jTable1.getModel();
                        Vector v=new Vector();
                        v.add(target.trim());
                        v.add(word.trim());
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
   
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PSWAMModel().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
   }
