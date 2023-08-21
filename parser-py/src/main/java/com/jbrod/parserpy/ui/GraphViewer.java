/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jbrod.parserpy.ui;

import com.jbrod.parserpy.app.analizer.MainClass;
import com.jbrod.parserpy.app.analizer.lexicon.Token;
import com.jbrod.parserpy.app.analizer.lexicon.TokenPlotter;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class GraphViewer extends javax.swing.JPanel {

    private MainClass mainClass; 
    
    /**
     * Creates new form GraphViewer
     */
    public GraphViewer() {
        initComponents();
       pnlito.setVisible(true);
       
        for (int i = 0; i < 10; i++) {
           // pnlito.add(new JButton());
            
        }
      edpnGraph.setContentType("text/html");
      
      edpnGraph.setText("<!DOCTYPE html>\n" +
"<html lang=\"\">\n" +
"  <head>\n" +
"    <meta charset=\"utf-8\">\n" +
"    <title></title>\n" +
"  </head>\n" +
"  <body>\n" +
"    <div style=\"border: 2px solid black;margin:4px; text-align: center;\">\n" +
"      <h1>asu</h1>\n" +
"      <div style=\"background-color:purple; border: 2px solid black; margin: 2px; border-radius: 100%; width: auto; display: inline-block; padding: 10px;\"><div style=\"border: 2px solid black; margin: 2px; border-radius: 20px ; width: auto; display: inline-block; padding: 10px\">a</div></div>\n" +
"      ->\n" +
"      <div style=\"border: 2px solid black; margin: 2px; border-radius: 20px ; width: auto; display: inline-block;\">a</div>\n" +
"    </div>\n" +
"    <div style=\"border: 2px solid black; margin: 2px; border-radius: 100%; width: auto; display: inline-block; padding: 10px;\"><div style=\"border: 2px solid black; margin: 2px; border-radius: 20px ; width: auto; display: inline-block; padding: 10px\">a</div></div>\n" +
"    <div style=\"border: 2px solid black; margin: 2px; border-radius: 20px ; width: auto; display: inline-block;\">a</div>\n" +
"    <div>b</div>\n" +
"    <div>c</div>\n" +
"  </body>\n" +
"</html>");
    }

    public void setMainClass(MainClass mainClass) {
        this.mainClass = mainClass;
    }
    
    public void addGraphs(Token[] arr){
        pnlito.removeAll();
        pnlito.repaint();
        pnlito.updateUI();
        pnlito.repaint();
        
        TokenPlotter tp = new TokenPlotter();
        for (Token token : arr) {
            try {
                tp.plot(token, token.getTokenType());
                
                File f = new File(token.getTokenType()+".png");
                boolean exist = false; 
                while(!exist){
                    Thread.sleep(100);
                    exist = f.exists();
                    if(exist){
                        ImageIcon ic = new ImageIcon(token.getTokenType()+".png");
                        JButton graph = new JButton("", ic);
                        //JLabel graph = new JLabel(ic);
                        pnlito.add(graph);
                        System.out.println("grafo aniadido");
                        f.delete();
                    }
                }
                /*
                ImageIcon ic = new ImageIcon(token.getTokenType()+".png");
                JButton graph = new JButton("", ic);
                //JLabel graph = new JLabel(ic);
                pnlito.add(graph);
                pnlito.updateUI();
                pnlito.repaint();
                */
                
            } catch (InterruptedException ex) {
                Logger.getLogger(GraphViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void addGraphsHtml(String text){
        edpnGraph.setText(text);
    }
    public void clearGraphs(){
        //scrl.removeAll();

        
    }
    public void addGraph(String graphPath){
        /*
        JPanel pnl = new JPanel(); 
        scrl.add(pnl);
        pnl.setVisible(true);
        scrl.setViewportView(pnl);
        scrl.updateUI();
        
        
        ImageIcon ic = new ImageIcon(graphPath);
        JLabel graph = new JLabel(ic);
        pnl.add(graph);
        pnl.add(new JButton());
        
        pnl.updateUI();
        pnl.repaint();
        */
        //pnlito.removeAll();
        pnlito.repaint();
        
        ImageIcon ic = new ImageIcon(graphPath);
        JButton graph = new JButton("", ic);
        //JLabel graph = new JLabel(ic);
        pnlito.add(graph);
        pnlito.updateUI();
        pnlito.repaint();
        
        System.out.println("grafo aniadido");
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrl = new javax.swing.JScrollPane();
        pnlito = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        edpnGraph = new javax.swing.JEditorPane();

        pnlito.setLayout(new java.awt.GridLayout(0, 4));
        scrl.setViewportView(pnlito);

        jScrollPane1.setViewportView(edpnGraph);
        edpnGraph.getAccessibleContext().setAccessibleDescription("text/html");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrl, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(scrl, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane edpnGraph;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlito;
    private javax.swing.JScrollPane scrl;
    // End of variables declaration//GEN-END:variables
}
