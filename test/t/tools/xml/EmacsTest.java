package t.tools.xml;

public class EmacsTest {

    public static void main(String[] args) {
        // 310 355 274 376 260 374
        String input = "310 355 274 376 260 374";
        String[] octStrArr = input.split(" ");
        byte[] bs = new byte[octStrArr.length];

        for (int i = 0; i < bs.length; i++) {
            //bs[i] = Byte.parseByte(octStrArr[i],8);
            System.out.println("byte: " + Integer.parseInt(octStrArr[i],8));
	}
	try {
            System.out.println("input is: " + (new String(bs,"utf-8")));
	} catch (Exception e) {
            e.printStackTrace();
	}
        String cs = "oh!";
        System.out.println("why你好" + cs);
        byte[] bs2 = cs.getBytes();
        for (int i = 0; i < bs2.length; i++) {
            System.out.println("cs byte: " + bs2[i]);
	}

    }

}
