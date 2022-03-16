package client;

import coder.Coder;
import coder.CoderHelper;
import coder.Decoder;
import coder.DecoderHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            label:
            {
                while (true) {
                    System.out.println("Input a string pls (press enter to exit)");
                    String str = scanner.nextLine();
                    if (str.equals("")) System.exit(0);

                    switch (menu()) {
                        case 1:
                            Coder coder = CoderHelper.narrow(ncRef.resolve_str("ABC"));
                            coder.code(str);
                            break;
                        case 2:
                            Decoder decoder = DecoderHelper.narrow(ncRef.resolve_str("ABC"));
                            decoder.decode(str);
                            break;
                        case 3:
                        break label;
                    }

                    final byte[] bytes = Files.readAllBytes(Paths.get("file.txt"));
                    System.out.println("Coded string: " + new String(bytes));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int menu() {
        System.out.println("1 - encode string");
        System.out.println("2 - decode string");
        System.out.println("3 - exit");
        System.out.println();

        int res;

        while (true) {
            System.out.print("Your choice: ");
            if (!scanner.hasNext("[123]")) {
                scanner.next();
                System.out.println("Reenter value pls");
            } else {
                res = scanner.nextInt();
                scanner.nextLine();
                break;
            }
        }

        return res;
    }
}
