package com.green.rukh.gui.panels;

import selenium.automation.treeelements.events.IConfigurable;
import selenium.automation.treeelements.events.SendKeysEvent;
import selenium.automation.treeelements.finders.ByClassnameFinder;
import selenium.automation.treeelements.finders.ByIdFinder;
import selenium.automation.treeelements.finders.ByLinktextFinder;
import selenium.automation.treeelements.finders.ByNameFinder;
import selenium.automation.treeelements.finders.ByXpathFinder;
import selenium.automation.treeelements.finders.IFinder;

/**
 *
 * @author Rajan Prasad Upadhyay
 */
public class SendKeysPropertyPanel extends AbstractPropertyPanel {

    //page type object iterator
    private IConfigurable pageObject = null;

    public void setUpTestElement(IConfigurable pageObject) {
        //update the textboxes with the properties of the Page element
        this.pageObject = pageObject;

        this.nameTextField.setText(this.pageObject.getProperty("name"));
        this.textToInputTextField.setText(this.pageObject.getProperty("keys"));
        
        if(this.pageObject instanceof SendKeysEvent){
            SendKeysEvent event = (SendKeysEvent) this.pageObject;

            IFinder finder = event.getOneFinder();
            setComboBox(finder);
        }
    }

    private void setComboBox(IFinder finder) {
        IConfigurable cfinder = (IConfigurable) finder;
        
        if (finder != null) {
            //{ "ByClassName", "ById", "ByName", "ByLinkText", "ByXPath" }
            if (finder instanceof ByClassnameFinder) {
                System.out.println("The combo box should be class Name");
                comboBoxFinderTypes.setSelectedIndex(0);
                this.finderTypePropertyTextField.setText(cfinder.getProperty(ByClassnameFinder.KEYWORD));
            } else if (finder instanceof ByIdFinder) {
                System.out.println("The combo box should be Id");
                comboBoxFinderTypes.setSelectedIndex(1);
                this.finderTypePropertyTextField.setText(cfinder.getProperty(ByIdFinder.KEYWORD));
            } else if (finder instanceof ByNameFinder) {
                System.out.println("The combo box should be name");
                comboBoxFinderTypes.setSelectedIndex(2);
                this.finderTypePropertyTextField.setText(cfinder.getProperty(ByNameFinder.KEYWORD));
            } else if (finder instanceof ByLinktextFinder) {
                System.out.println("The combo box should be linkText");
                comboBoxFinderTypes.setSelectedIndex(3);
                this.finderTypePropertyTextField.setText(cfinder.getProperty(ByLinktextFinder.KEYWORD));
            } else if (finder instanceof ByXpathFinder) {
                System.out.println("The combo box should be xpath");
                comboBoxFinderTypes.setSelectedIndex(4);
                this.finderTypePropertyTextField.setText(cfinder.getProperty(ByXpathFinder.KEYWORD));
            }
        }//if
    }

    public SendKeysPropertyPanel(BasePanel parent) {
        this.bPanel = parent;
        initComponents();
        setKeyName();
    }

    protected void setKeyName() {
        this.key = "keys";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        textToSendLabel = new javax.swing.JLabel();
        textToInputTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        finderTypeLabel = new javax.swing.JLabel();
        comboBoxFinderTypes = new javax.swing.JComboBox();
        finderTypePropertyTextField = new javax.swing.JTextField();
        finderValueLabel = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SEndKeys Properties", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), java.awt.Color.red)); // NOI18N

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        nameLabel.setText("Name:");

        nameTextField.setText("SendKeys");
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });

        textToSendLabel.setText("Text to Send:");

        textToInputTextField.setText("Text ....");
        textToInputTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textToInputTextFieldActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Finder Properties", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 0, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        finderTypeLabel.setText("Finder Type:");

        comboBoxFinderTypes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ByClassName", "ById", "ByName", "ByLinkText", "ByXPath" }));
        comboBoxFinderTypes.setMinimumSize(new java.awt.Dimension(100, 20));

        finderTypePropertyTextField.setText("value");
        finderTypePropertyTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finderTypePropertyTextFieldActionPerformed(evt);
            }
        });

        finderValueLabel.setText("Finder Value:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(finderTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxFinderTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(finderTypePropertyTextField)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(finderValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(finderTypeLabel)
                    .addComponent(finderValueLabel))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(finderTypePropertyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxFinderTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 150, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(textToSendLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textToInputTextField))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(textToSendLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textToInputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(68, 68, 68)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(59, 59, 59)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void finderTypePropertyTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finderTypePropertyTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_finderTypePropertyTextFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (this.pageObject == null) {
            System.out.println("The page object is null. snapshotPropertyPanel.java 115");
        }
        this.pageObject.setProperty(this.key, this.textToInputTextField.getText());
        this.pageObject.setProperty("name", this.nameTextField.getText());
        
        //remove the old one and add the new finder element based on the combo box and the text field
        if (this.pageObject instanceof SendKeysEvent) {
            SendKeysEvent event = (SendKeysEvent) this.pageObject;
            //{ "ByClassName", "ById", "ByName", "ByLinkText", "ByXPath" }
            Integer selectedItemIndex = comboBoxFinderTypes.getSelectedIndex();
            String  finderValue = finderTypePropertyTextField.getText();
            System.out.println(selectedItemIndex + ", " + finderValue);
            
            if(selectedItemIndex == 0){
                event.addFinder(new ByClassnameFinder(finderValue));
            }else if(selectedItemIndex == 1){
                event.addFinder(new ByIdFinder(finderValue));
            }else if(selectedItemIndex == 2){
                event.addFinder(new ByNameFinder(finderValue));
            }else if(selectedItemIndex == 3){
                event.addFinder(new ByLinktextFinder(finderValue));
            }else if(selectedItemIndex == 4){
                event.addFinder(new ByXpathFinder(finderValue));
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextFieldActionPerformed

    private void textToInputTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textToInputTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textToInputTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboBoxFinderTypes;
    private javax.swing.JLabel finderTypeLabel;
    private javax.swing.JTextField finderTypePropertyTextField;
    private javax.swing.JLabel finderValueLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField textToInputTextField;
    private javax.swing.JLabel textToSendLabel;
    // End of variables declaration//GEN-END:variables
}
