package tv;
public class TV {
    public static void main(String[] x) {
        Television tv1 = new Television();
        System.out.println(tv1);
        tv1.cambiarCanal(12);
        tv1.cambiarCanal(40);
        tv1.bajarVolumen();
        tv1.bajarVolumen(15);
        tv1.cambiarCanal(false);
        tv1.encender();
        System.out.println(tv1);
    }
}