import javax.swing.*;

public class Area {

    private String provinces; //Space separated list of provinces in the area
    private String name;      //Unformatted name of area

    public Area(String provinces, String name) {
        this.name = name;
        this.provinces = provinces;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getProvinces() {
        return provinces;
    }

    void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    /**
     * formattedName:
     * Method formats the Area name to match the formatting in area.txt and region.txt
     *
     * @return formatted area name
     */
    String formattedName() {
        String formatted = name.toLowerCase();
        formatted = String.join("_", formatted.split(" "));
        return formatted + (formatted.endsWith("_area") ? "" : "_area");
    }

    /**
     * generateAreaText:
     * Method generates the formatted text block as in area.txt for a particular area
     *
     * @return returns the formatted text block
     */
    String generateAreaText() {
        return "\n" + formattedName() + " = {\n" + "\t" + provinces + "\n}\n";
    }

    /**
     * provinceArraySize:
     * Method returns the number of provinces in the area
     *
     * @return number of provinces in the area
     */
    int provinceArraySize() {
        return provinces.split(" ").length;
    }

    /**
     * provinceArray:
     * Method returns an integer array of the provinces in the area
     *
     * @return integer array of provinces
     */
    int[] provinceArray() {
        try {
            String[] strProvinces = this.provinces.split(" ");
            int[] ret = new int[strProvinces.length];
            for (int i = 0; i < strProvinces.length; i++) {
                ret[i] = Integer.parseInt(strProvinces[i]);
            }
            return ret;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Writing Files", "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new NumberFormatException();
        }
    }
}
