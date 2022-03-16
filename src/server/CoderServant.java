package server;

import coder.CoderPOA;
import org.omg.CORBA.ORB;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class CoderServant extends CoderPOA {
    private ORB orb;

    @Override
    public String code() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void code(String newCode) {
        byte[] buffer = Base64.getEncoder().encode(newCode.getBytes());
        try {
            Files.write(Paths.get("file.txt"), buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOrb(ORB orb) {
        this.orb = orb;
    }
}
