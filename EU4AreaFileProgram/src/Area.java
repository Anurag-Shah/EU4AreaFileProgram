public class Area {

    private String provinces; //Space separated list of provinces in the area
    private String name;      //Unformatted name of area

    public Area(String provinces, String name) {
        this.name = name;
        this.provinces = provinces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    /**
     * formattedName:
     * Method formats the Area name to match the formatting in area.txt and region.txt
     *
     * @return formatted area name
     */
    public String formattedName() {
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
    public String generateAreaText() {
        return "\n" + formattedName() + " = {\n" + "\t" + provinces + "\n}\n";
    }

    /**
     * provinceArraySize:
     * Method returns the number of provinces in the area
     *
     * @return number of provinces in the area
     */
    public int provinceArraySize() {
        return provinces.split(" ").length;
    }

    /**
     * provinceArray:
     * Method returns an integer array of the provinces in the area
     *
     * @return integer array of provinces
     */
    public int[] provinceArray() {
        try {
            String[] strProvinces = this.provinces.split(" ");
            int[] ret = new int[strProvinces.length];
            for (int i = 0; i < strProvinces.length; i++) {
                ret[i] = Integer.parseInt(strProvinces[i]);
            }
            return ret;
        } catch (Exception e) {
            //TODO show a prompt saying that "AREA NAME" has provinces entered incorrectly, then abort the file writing.
            throw new NumberFormatException();
        }
    }
}
