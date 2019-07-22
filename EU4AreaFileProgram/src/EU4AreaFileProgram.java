public class EU4AreaFileProgram {

    public static void main(String[] args) {
        Area area = new Area("12 12 12", "hello world");
        Region region1 = new Region("hello reeegion");
        Region region2 = new Region("hello reeegion");
        Region region3 = new Region("hello reeegion");

        region1.addArea(area);
        region1.addArea(area);
        region2.addArea(area);
        region2.addArea(area);
        region3.addArea(area);
        region3.addArea(area);

        Superregion superregion = new Superregion("hello subcontinent", "continent");

        superregion.addRegion(region1);
        superregion.addRegion(region2);
        superregion.addRegion(region3);

        superregion.writeSuperregion("spr");
    }
}
