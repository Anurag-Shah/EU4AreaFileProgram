import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiForm {

    private JPanel contentPane;                         //Main panel of frame
    private JList regionList;                           //List of regions (updated through methods)
    private JList areaList;                             //List of areas (updated through listener on regionList)
    private JButton addRegionButton;                    //Button to add region
    private JButton editSelectedRegionButton;           //Button to edit region selected in regionList
    private JButton editSuperregionAndContinentButton;  //Button to edit superregion and continent names
    private JButton finalizeButton;                     //Button to finalize and write files
    private JButton addAreaButton;                      //Button to add area to selected region in regionList
    private JButton editSelectedAreaButton;             //Button to edit selected area
    private JButton clearButton;                        //Button to clear all regions and areas
    private JLabel superregionLabel;                    //Label for superregion name
    private JLabel continentLabel;                      //Label for continent name
    private JLabel provincesLabel;

    private Superregion superregion;

    public static void main(String[] args) {

        //Start the program up by asking for fields to build Superregion object
        String superregionName = JOptionPane.showInputDialog(null, "Enter the Superregion Name");
        String continentName = JOptionPane.showInputDialog(null, "Enter the Continent Name");

        //Form main
        JFrame frame = new JFrame("EU4 File Area Program");
        GuiForm form = new GuiForm();    //Single form object to use methods from static context
        frame.setContentPane(form.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //Create Superregion object
        form.superregion = new Superregion(superregionName, continentName);

        //Set text in labels, add action listeners to list and buttons, then Frame can be used
        form.resetContinentSuperregionLabelNames(superregionName, continentName);
        form.addActionListeners();
    }

    /**
     * resetContinentSuperregionLabelNames:
     * Method resets the label text after starting the frame up or editing the names
     *
     * @param superregionName Name to edit superregionLabel text to
     * @param continentName Name to edit continentLabel text to
     */
    private void resetContinentSuperregionLabelNames(String superregionName, String continentName) {
        this.superregionLabel.setText("Superregion: " + superregionName);
        this.continentLabel.setText("Continent: " + continentName);
    }

    /**
     * addActionListeners:
     * Method adds action listeners to all buttons and regionList
     */
    private void addActionListeners() {
        addRegionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRegion();
            }
        });

        addAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addArea();
            }
        });

        editSelectedRegionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editRegion();
            }
        });

        editSelectedAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editArea();
            }
        });

        editSuperregionAndContinentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSuperregion();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        finalizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizeForm();
            }
        });

        regionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                refreshAreas();
            }
        });

        areaList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                refreshProvinceLabel();
            }
        });
    }

    /**
     * addArea:
     * Method brings up JOptionPane prompts to add area name and provinces. Does not fire if no region is selected, and
     * is aborted if cancel is selected at any point or any value is entered as empty string.
     */
    private void addArea() {
        if (regionList.getSelectedIndex() != -1) {
            if (superregion.getRegions() == null) {
                JOptionPane.showMessageDialog(null, "Please add a Region first");
                return;
            }
            int index = regionList.getSelectedIndex();
            String name = JOptionPane.showInputDialog("Enter the Area Name:");
            if (name == null || name.equals("")) {
                return;
            }
            String provinces = JOptionPane.showInputDialog("Enter the Area's Provinces (space separated list of numbers):");
            if (provinces == null || provinces.equals("")) {
                return;
            }
            superregion.getRegions()[index].addArea(new Area(provinces, name));
            refreshList();
        }
    }

    /**
     * addRegion:
     * Method brings up JOptionPane prompts to add region name. Is aborted if cancel is selected or value is entered as
     * empty string.
     */
    private void addRegion() {
        String name = JOptionPane.showInputDialog("Enter the Region Name");
        if (name == null || name.equals("")) {
            return;
        }
        superregion.addRegion(new Region(name, superregion.getName()));
        refreshList();
    }

    /**
     * editArea:
     * Method brings up JOptionPane prompts to edit area name and provinces. Does not fire if no region is selected, and
     * is aborted if cancel is selected at any point or any value is entered as empty string.
     */
    private void editArea() {
        if (regionList.getSelectedIndex() != -1 && areaList.getSelectedIndex() != -1) {
            int regindex = regionList.getSelectedIndex();
            int areaindex = areaList.getSelectedIndex();

            String name = JOptionPane.showInputDialog(null, "Edit the Name:",
                    superregion.getRegions()[regindex].getAreas()[areaindex].getName());
            if (name == null || name.equals("")) {
                return;
            }
            String provinces = JOptionPane.showInputDialog(null,
                    "Edit the Area's Provinces (space separated list of numbers):",
                    superregion.getRegions()[regindex].getAreas()[areaindex].getProvinces());
            if (provinces == null || provinces.equals("")) {
                return;
            }

            superregion.getRegions()[regindex].getAreas()[areaindex].setName(name);
            superregion.getRegions()[regindex].getAreas()[areaindex].setProvinces(provinces);

            refreshList();
        }
    }

    /**
     * editRegion:
     * Method brings up JOptionPane prompts to edit region name. Does not fire if no region is selected, and
     * is aborted if cancel is selected or value is entered as empty string.
     */
    private void editRegion() {
        if (regionList.getSelectedIndex() != -1) {
            int regindex = regionList.getSelectedIndex();

            String name = JOptionPane.showInputDialog(null, "Edit the Name:",
                    superregion.getRegions()[regindex].getName());
            if (name == null || name.equals("")) {
                return;
            }

            int removeAreas = JOptionPane.showConfirmDialog(null,
                    "Clear the areas associated with this region?", "Remove Areas", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (removeAreas == 0) { //Remove Areas
                superregion.getRegions()[regindex] = new Region(name);
            } else { //Don't Remove Areas
                superregion.getRegions()[regindex].setName(name);
            }

            refreshList();
        }
    }

    /**
     * editSuperregion:
     * Method brings up JOptionPane prompts to edit superregion and continent names. Aborted if cancel is selected at
     * any point or either value is entered as an empty string
     */
    private void editSuperregion() {
        String superregionName = JOptionPane.showInputDialog(null,
                "Edit the Superregion Name", superregion.getName());
        if (superregionName == null || superregionName.equals("")) {
            return;
        }

        String continent = JOptionPane.showInputDialog(null,
                "Edit the Continent Name", superregion.getName());
        if (continent == null || continent.equals("")) {
            return;
        }

        superregion.changeName(superregionName);
        superregion.setContinent(continent);

        resetContinentSuperregionLabelNames(superregionName, continent);

        refreshList();
    }

    /**
     * clearForm:
     * Method deletes all regions and areas in the superregion, with a confirmation prompt before deleting
     */
    private void clearForm() {

        int proceed = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to clear the Superregion? This decision cannot be undone",
                "Proceed?", JOptionPane.YES_NO_OPTION);


        if (proceed == 0) {
            superregion.clearSuperregion();
            refreshList();
        }
    }

    /**
     * finalizeForm:
     * Method finalizes all regions and areas, and calls the writeSuperregion method. Is aborted if declined during
     * confirmation dialog or cancel is hit during the input dialog for file header.
     */
    private void finalizeForm() {

        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to finalize and write the files?", "Finalize?",
                JOptionPane.YES_NO_OPTION);
        if (confirm == 1) {
            return;
        }

        String header = JOptionPane.showInputDialog(null, "Enter the file header:");

        if (header == null || header.equals("")) {
            return;
        }

        superregion.writeSuperregion(header);

        JOptionPane.showMessageDialog(null, "Files have written successfully. You can now" +
                "safely close the main window of the program, or make any corrections needed (if you enter the same" +
                "file header for your correction, the files will be overwritten)");
    }

    /**
     * refreshList:
     * Method refreshes the regionList, removing its selection and updating values, and clears the areaList
     */
    private void refreshList() {
        regionList.clearSelection();
        areaList.clearSelection();
        areaList.setListData(new Object[1]);
        if (superregion.getRegions() != null) {
            regionList.setListData(superregion.regionNames());
        }
        provincesLabel.setText("Provinces: ");
    }

    /**
     * refreshAreas:
     * Method refreshes the areaList, changing values as per the selected index in regionList
     */
    private void refreshAreas() {
        if (superregion.getRegions() != null && regionList.getSelectedIndex() != -1 &&
                superregion.getRegions()[regionList.getSelectedIndex()].getAreas() != null) {
            areaList.setListData(superregion.getRegions()[regionList.getSelectedIndex()].areaNames());

        } else if (superregion.getRegions() != null && regionList.getSelectedIndex() != -1 &&
                superregion.getRegions()[regionList.getSelectedIndex()].getAreas() == null) {
            areaList.setListData(new Object[1]);
        }
    }


    /**
     * refreshProvinceLabel:
     * Method refreshes the provinceLabel JLabel to accurately display the provinces in selected area
     */
    private void refreshProvinceLabel() {
        if (regionList.getSelectedIndex() != -1 && areaList.getSelectedIndex() != -1) {
            provincesLabel.setText("Provinces: " + superregion.getRegions()[regionList.getSelectedIndex()].
                    getAreas()[areaList.getSelectedIndex()].getProvinces());
        } else {
            provincesLabel.setText("Provinces: ");
        }
    }
}
