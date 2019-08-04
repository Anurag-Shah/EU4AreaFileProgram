import javax.swing.*;
import java.awt.*;

public class EU4AreaFileProgram {

    /** TESTER
    public static void main(String[] args) {
        Area area1 = new Area("10 11 12", "hello world");
        Area area2 = new Area("9 8 7", "First woods");
        Area area3 = new Area("6 5 2", "last_area");
        Region region1 = new Region("infinite");
        Region region2 = new Region("Dark Woods");
        Region region3 = new Region("free_real_estate_region");

        region1.addArea(area1);
        region2.addArea(area2);
        region3.addArea(area3);

        Superregion superregion = new Superregion("Valenwood", "Tamriel");

        superregion.addRegion(region1);
        superregion.addRegion(region2);
        superregion.addRegion(region3);

        superregion.writeSuperregion("spr");
    }
     **/

    static String superregionName = "Superregion";
    static String continentName = "Continent";
    public Superregion sr = new Superregion(superregionName, continentName);

    public static void main(String[] args) {
        //mainScreen();

    }

    static void mainScreen() {
        //Basic Frame
        JFrame frame = new JFrame("EU4 Area File Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        //Components

        //test
        String[] options = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        JLabel superregionLabel = new JLabel("Superregion: " + superregionName);
        JLabel continentLabel = new JLabel("Continent: " + continentName);
        JList regionList = new JList();
        regionList.setSize(200,150);
        JList areaList = new JList();
        areaList.setListData(options);
        regionList.setListData(options);
        JButton addRegion = new JButton("Add Region");
        JButton addArea = new JButton("Add Area");
        JButton editRegion = new JButton("Edit Region");
        JButton editArea = new JButton("Edit Area");
        JButton editSuperregion = new JButton("Edit Superregion");
        JButton clear = new JButton("Clear");
        JButton finalize = new JButton("Finalize");


        //Component Properties
        areaList.setSize(200,150);
        JScrollPane regionPane = new JScrollPane(regionList);
        regionPane.setSize(200,150);
        JScrollPane areaPane = new JScrollPane(areaList);
        areaPane.setSize(200,150);

        //Layout
        BoxLayout leftLayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
        BoxLayout rightLayout = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);

        leftPanel.setLayout(leftLayout);
        rightPanel.setLayout(rightLayout);

        leftPanel.setSize(200,600);
        rightPanel.setSize(200,600);

        leftPanel.add(superregionLabel);
        leftPanel.add(regionPane);
        leftPanel.add(addRegion);
        leftPanel.add(editRegion);
        leftPanel.add(editSuperregion);
        leftPanel.add(finalize);

        rightPanel.add(continentLabel);
        rightPanel.add(areaPane);
        rightPanel.add(addArea);
        rightPanel.add(editArea);
        rightPanel.add(clear);

        frame.add(leftPanel);
        frame.add(rightPanel);


        //Finalize
        //frame.pack();
        frame.setVisible(true);
    }
}